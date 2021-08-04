package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestGotSportDivisionParserCallback implements IGotSportDivisionParserCallback {
    public Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public void handleEvent(Game theGame) {
        gameMap.put(theGame.getGameId(), theGame);
    }
}
