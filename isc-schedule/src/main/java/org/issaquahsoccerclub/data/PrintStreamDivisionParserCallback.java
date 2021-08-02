package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class PrintStreamDivisionParserCallback implements IGotSportDivisionParserCallback {
    private PrintStream stream;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mmaa");

    public PrintStreamDivisionParserCallback(PrintStream theStream) {
        this.stream = theStream;
    }

    public void handleEvent(String theEvent, String theGender, String theAge,
                            String theDivision, String theTier, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation) {
        //if (theHomeTeam.contains("ISC") || theAwayTeam.contains("ISC")) {
            stream.println(theGameNum
                    + commaThenAttribute("\"" + theGameDate + "\"")
                    + commaThenAttribute(theGameTime)
                    + commaThenAttribute(theDivision)
                    + commaThenAttribute(theHomeTeam)
                    + commaThenAttribute(theAwayTeam)
                    + commaThenAttribute(theLocation));
        //}
    }

    public void handleEvent(Game theGame) {
        String homeTeam = theGame.getHomeTeamId();
        String awayTeam;

        if (homeTeam.equals(theGame.getTeam1Id())) {
            awayTeam = theGame.getTeam2Id();
        } else {
            awayTeam = theGame.getTeam1Id();
        }

        stream.println(theGame.getGameId()
                + commaThenAttribute(dateFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(timeFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(theGame.getDivision())
                + commaThenAttribute(homeTeam)
                + commaThenAttribute(awayTeam)
                + commaThenAttribute(theGame.getLocation()));
    }

    private String commaThenAttribute(String theAttribute) {
        if (theAttribute == null) {
            return ",";
        }

        return "," + theAttribute;
    }
}
