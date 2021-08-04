package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;
import org.issaquahsoccerclub.util.ISCGotSportUtil;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ISCGotSoccerParserCallback implements IGotSportDivisionParserCallback {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // TODO: config-based date formatting
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Map<Date, List<Game>> gameMap = new HashMap<>();

    public ISCGotSoccerParserCallback() {
    }

    public void handleEvent(Game theGame) {
        Date gameDate = Game.BAD_DATE;
        try {
            gameDate = simpleDateFormat.parse(simpleDateFormat.format(theGame.getGameDate()));
        } catch (ParseException px) {
            logger.log(Level.SEVERE, "Unable to parse game date with time to date without time", px);
        }

        if (!gameMap.containsKey(gameDate)) {
            gameMap.put(gameDate, new LinkedList<>());
        }

        gameMap.get(gameDate).add(theGame);
    }

    // TODO: get rid of finalize method
    public void finalize(PrintStream theStream) {
        for (Date gameDate : gameMap.keySet()) {
            theStream.println(simpleDateFormat.format(gameDate));

            for (Game game : gameMap.get(gameDate)) {
                theStream.println(ISCGotSportUtil.gameToISCCommaDelimitedString(game));
            }
        }
    }
}
