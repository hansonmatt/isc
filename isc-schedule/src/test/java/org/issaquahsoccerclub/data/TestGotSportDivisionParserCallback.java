package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestGotSportDivisionParserCallback implements IGotSportDivisionParserCallback {
    public Map<String, GotSportFlatGame> gotSportFlatGameMap = new ConcurrentHashMap<>();

    public void handleEvent(String theEvent, String theGender, String theAge,
                            String theDivision, String theTier, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation) {
        gotSportFlatGameMap.put(theGameNum, new GotSportFlatGame(theDivision, theBracket,
                theGameNum, theGameDate, theGameTime, theHomeTeam, theAwayTeam, theLocation));
    }

    public void handleEvent(Game theGame) {
        String homeTeam = theGame.getHomeTeamId();
        String awayTeam = (homeTeam.equals(theGame.getTeam1Id())) ? theGame.getTeam2Id() : theGame.getTeam1Id();
        gotSportFlatGameMap.put(theGame.getGameId(), new GotSportFlatGame(theGame.getDivision(), "Bracket", theGame.getGameId(), theGame.getGameDate().toString(), null, homeTeam, awayTeam, theGame.getLocation()));
    }
}
