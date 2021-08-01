package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.data.IGotSportEventParserCallback;

import java.io.PrintStream;

public class PrintStreamEventParserCallback implements IGotSportEventParserCallback {
    private PrintStream stream;

    public PrintStreamEventParserCallback(PrintStream theStream) {
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
