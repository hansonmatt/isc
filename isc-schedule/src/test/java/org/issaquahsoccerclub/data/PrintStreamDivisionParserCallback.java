package org.issaquahsoccerclub.data;

import java.io.PrintStream;

public class PrintStreamDivisionParserCallback implements IGotSportDivisionParserCallback {
    private PrintStream stream;

    public PrintStreamDivisionParserCallback(PrintStream theStream) {
        this.stream = theStream;
    }

    public void handleEvent(String theEvent, String theGender, String theAge,
                            String theDivision, String theTier, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation) {
        stream.println(theEvent + "," + theGender + "," + theAge + ","
                + theDivision + "," + theTier + "," + theBracket + ","
                + theGameNum + "," + theGameDate + "," + theGameTime
                + "," + theHomeTeam + "," + theAwayTeam + "," + theLocation);
    }
}
