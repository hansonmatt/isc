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
        if (theHomeTeam.contains("ISC") || theAwayTeam.contains("ISC")) {
            stream.println(theGameNum
                    + commaThenAttribute("\"" + theGameDate + "\"")
                    + commaThenAttribute(theGameTime)
                    + commaThenAttribute(theDivision)
                    + commaThenAttribute(theHomeTeam)
                    + commaThenAttribute(theAwayTeam)
                    + commaThenAttribute(theLocation));
        }
    }

    private String commaThenAttribute(String theAttribute) {
        if (theAttribute == null) {
            return ",";
        }

        return "," + theAttribute;
    }
}
