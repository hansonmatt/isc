package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

public interface IGotSportDivisionParserCallback {

    @Deprecated
    public void handleEvent(String theEvent, String theGender, String theAge,
                            String theDivision, String theTier, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation);

    public void handleEvent(Game theGame);
}
