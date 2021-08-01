package org.issaquahsoccerclub.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GotSportEventParser {
    public void schedule(String theURL, IGotSportEventParserCallback theCallback) throws MalformedURLException, IOException {
        Document document = Jsoup.parse(new URL(theURL), 5000);

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
                            theCallback.handleEvent("EventName WIP", "Gender WIP", "Age WIP", safeElementText(division),
                                    "Tier WIP", safeElementText(bracket), gameNumbers.get(n).ownText(),
                                    rows.get(0).ownText(), matchTimes.get(n).ownText(), safeTeamName(homeTeams.get(n)), safeTeamName(awayTeams.get(n)), locations.get(n).child(0).child(0).ownText());
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
