package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;
import org.issaquahsoccerclub.model.Team;
import org.issaquahsoccerclub.util.ISCGotSportUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GotSportEventDivisionPageParser {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    // TODO: config-based formatting
    private SimpleDateFormat format = new SimpleDateFormat("E, MMM d, yyyy h:mm aa");

    private Map<String, Team> teamMap = new HashMap<>();
    // TODO: dependency injection
    private GotSportTeamPageParser gotSportTeamPageParser = new GotSportTeamPageParser();

    public void schedule(String theURL, IGotSportDivisionParserCallback theCallback) throws MalformedURLException, IOException {
        long t0 = System.currentTimeMillis();
        // TODO: timeout config
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
                            // TODO: defensive null checks, validation
                            String gameId = gameNumbers.get(n).ownText();
                            if (gameId.length() >= 1 && gameId.charAt(0) == '#') {
                                gameId = gameId.substring(1);
                            }

                            Date gameDate = Game.BAD_DATE;
                            // TODO: defensive null checks, validation
                            String gameDateString = rows.get(0).ownText() + " " + matchTimes.get(n).ownText();
                            try
                            {
                                gameDate = format.parse(gameDateString);
                            }
                            catch (ParseException px)
                            {
                                logger.log(Level.SEVERE, "Unable to parse game date '" + gameDateString + "'", px);
                            }

                            // TODO: defensive null checks, validation
                            String homeTeamName = safeTeamName(homeTeams.get(n));
                            // TODO: defensive null checks, validation
                            String teamUrl = getTeamUrl(homeTeams.get(n));
                            Team homeTeam = new Team(homeTeamName);
                            try {
                                // TODO: url validator
                                if (teamUrl.length() > 0) {
                                    String url = ISCGotSportUtil.GOT_SPORT_BASE_URL + teamUrl;
                                    homeTeam = gotSportTeamPageParser.getTeam(url);
                                }
                            }
                            catch(IOException x) {
                                logger.log(Level.SEVERE, "Unable to get team from URL = '" + teamUrl + "'", x);
                            }

                            // TODO: defensive null checks, validation
                            String awayTeamName = safeTeamName(awayTeams.get(n));
                            // TODO: defensive null checks, validation
                            teamUrl = getTeamUrl(awayTeams.get(n));
                            Team awayTeam = new Team(awayTeamName);
                            try {
                                if (teamUrl.length() > 0) {
                                    String url = ISCGotSportUtil.GOT_SPORT_BASE_URL + teamUrl;
                                    awayTeam = gotSportTeamPageParser.getTeam(url);
                                }
                            }
                            catch(IOException x) {
                                logger.log(Level.SEVERE, "Unable to get team from URL = '" + teamUrl + "'", x);
                            }

                            homeTeam.setType(Team.TeamType.HOME);
                            awayTeam.setType(Team.TeamType.AWAY);

                            Game game = new Game(gameId, gameDate, safeElementText(division), locations.get(n).child(0).child(0).ownText(), homeTeam, awayTeam);
                            theCallback.handleEvent(game);
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

    // TODO: better scheme for URL handling - URLs needed for fetching team data
    private String getTeamUrl(Element theTeam) {
        if (theTeam == null) {
            return "";
        }

        if (theTeam.childrenSize() == 1) {
            return theTeam.child(0).attr("href");
        }

        return "";
    }
}
