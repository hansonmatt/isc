package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GotSportEventDivisionPageParser {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private SimpleDateFormat format = new SimpleDateFormat("E, MMM d, yyyy h:mm aa");

    public void schedule(String theURL, IGotSportDivisionParserCallback theCallback) throws MalformedURLException, IOException {
        long t0 = System.currentTimeMillis();
        Document document = Jsoup.parse(new URL(theURL), 5000);
        long t1 = System.currentTimeMillis();
        logger.log(Level.INFO, "GotSportEventDivisionPageParser parsing for url = '" + theURL + "' took '" + (t1 - t0) + "' ms");

        Elements grid12 = document.getElementsByClass("grid_12");
        Element scheduleGrid = grid12.first();
        if (scheduleGrid != null) {

            Queue<Element> divisions = new LinkedList<>(scheduleGrid.getElementsByClass("PageHeading"));
            Queue<Element> brackets = new LinkedList<>(scheduleGrid.getElementsByClass("bracket"));
            Queue<Element> divisionBracketDateTables = new LinkedList<>(scheduleGrid.getElementsByClass("standings").select("table"));

            Element division = null;
            do {
                division = divisions.poll();
                int nextDivision = (divisions.isEmpty()) ? Integer.MAX_VALUE : divisions.peek().siblingIndex();

                Element bracket = null;
                do {
                    bracket = brackets.poll();
                    int nextBracket = (brackets.isEmpty()) ? Integer.MAX_VALUE : brackets.peek().siblingIndex();

                    Element gameDay = divisionBracketDateTables.peek();
                    while (gameDay != null && gameDay.siblingIndex() < nextBracket) {
                        divisionBracketDateTables.remove();

                        ArrayList<Element> rows = gameDay.getElementsByClass("GroupBoxHeading");
                        ArrayList<Element> gameNumbers = gameDay.getElementsByClass("GameNumber");
                        ArrayList<Element> matchTimes = gameDay.getElementsByClass("MatchTime");
                        ArrayList<Element> homeTeams = gameDay.getElementsByClass("homeTeam");
                        ArrayList<Element> awayTeams = gameDay.getElementsByClass("awayTeam");
                        ArrayList<Element> locations = gameDay.getElementsByClass("location").select("td");

                        for (int n = 0; n < gameNumbers.size(); ++n) {
                            String gameId = gameNumbers.get(n).ownText();
                            if (gameId.length() >= 1 && gameId.charAt(0) == '#') {
                                gameId = gameId.substring(1);
                            }

                            String homeTeam = safeTeamName(homeTeams.get(n));

                            Date gameDate = Game.BAD_DATE;
                            String gameDateString = rows.get(0).ownText() + " " + matchTimes.get(n).ownText();
                            try
                            {
                                gameDate = format.parse(gameDateString);
                            }
                            catch (ParseException px)
                            {
                                logger.log(Level.SEVERE, "Unable to parse game date '" + gameDateString + "'", px);
                            }

                            Game game = new Game(gameId, gameDate, safeElementText(division), homeTeam, safeTeamName(awayTeams.get(n)), homeTeam, locations.get(n).child(0).child(0).ownText());
                            theCallback.handleEvent(game);

//                            theCallback.handleEvent("EventName WIP", "Gender WIP", "Age WIP", safeElementText(division),
//                                    "Tier WIP", safeElementText(bracket), gameId,
//                                    rows.get(0).ownText(), matchTimes.get(n).ownText(), safeTeamName(homeTeams.get(n)), safeTeamName(awayTeams.get(n)), locations.get(n).child(0).child(0).ownText());
                        }

                        gameDay = divisionBracketDateTables.peek();
                    }

                    bracket = brackets.peek();
                } while (bracket != null && bracket.siblingIndex() < nextDivision);
            } while (!divisions.isEmpty());
        }
    }

    private String safeElementText(Element theElement) {
        if (theElement == null) {
            return "";
        }

        return theElement.ownText();
    }

    private String safeTeamName(Element theTeam) {
        if (theTeam == null) {
            return "";
        }

        if (theTeam.childrenSize() == 1) {
            return theTeam.child(0).ownText();
        } else if (theTeam.childrenSize() == 0) {
            return theTeam.ownText();
        }

        return "";
    }
}
