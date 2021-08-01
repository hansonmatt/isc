package org.issaquahsoccerclub.data;

import java.util.HashMap;
import java.util.Map;

public class TestGotSportDivisionParserCallback implements IGotSportDivisionParserCallback {
    public Map<String, GotSportFlatGame> gotSportFlatGameMap = new HashMap<>();

    public void handleEvent(String theEvent, String theGender, String theAge,
                            String theDivision, String theTier, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation) {
        gotSportFlatGameMap.put(theGameNum, new GotSportFlatGame(theDivision, theBracket,
                theGameNum, theGameDate, theGameTime, theHomeTeam, theAwayTeam, theLocation));
    }
}
