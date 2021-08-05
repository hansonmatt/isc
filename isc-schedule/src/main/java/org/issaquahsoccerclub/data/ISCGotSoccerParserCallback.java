package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;
import org.issaquahsoccerclub.model.Team;
import org.issaquahsoccerclub.util.ISCGotSportUtil;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ISCGotSoccerParserCallback implements IGotSportDivisionParserCallback {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // TODO: config-based date/time formatting
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mmaa");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Map<Date, Map<String, Set<Game>>> gameMap = new HashMap<>();

    public ISCGotSoccerParserCallback() {
    }

    public void handleEvent(Game theGame) {
        if (theGame.getTeam1().getTeamName().contains("ISC") || theGame.getTeam2().getTeamName().contains("ISC")) {
            if (theGame.getTeam1().getTeamName().contains("ISC")) {
                processISCGameAndTeam(theGame, theGame.getTeam1());
            }

            if (theGame.getTeam2().getTeamName().contains("ISC")) {
                processISCGameAndTeam(theGame, theGame.getTeam2());
            }
        }
        else
        {
            logger.log(Level.FINE, "ISCGotSoccerParserCallback skipping non-ISC game '" + theGame + "'");
        }

    }

    private void processISCGameAndTeam(Game theGame, Team theTeam) {
        Date gameDate = Game.BAD_DATE;
        try {
            gameDate = simpleDateFormat.parse(simpleDateFormat.format(theGame.getGameDate()));
        } catch (ParseException px) {
            logger.log(Level.SEVERE, "Unable to parse game date with time to date without time", px);
        }

        if (!gameMap.containsKey(gameDate)) {
            gameMap.put(gameDate, new HashMap<>());

        }

        if (!gameMap.get(gameDate).containsKey(theTeam.getCoach()))
        {
            // TODO : dependency injection on the comparator
            gameMap.get(gameDate).put(theTeam.getCoach(), new TreeSet<>(new ISCGotSoccerParserGameDateTimeComparator()));
        }

        gameMap.get(gameDate).get(theTeam.getCoach()).add(theGame);
    }

    // TODO: get rid of finalize method
    public void finalize(PrintStream theStream) {
        for (Date gameDate : gameMap.keySet()) {
            theStream.println(simpleDateFormat.format(gameDate));

            Map<String, Set<Game>> coachGameMap = gameMap.get(gameDate);
            for (String coach : coachGameMap.keySet()) {
                Set<Game> coachesGames = coachGameMap.get(coach);
                for (Game game : coachesGames) {
                    theStream.println(gameToISCCommaDelimitedString(coach, game));
                }
            }
        }
    }

    public String gameToISCCommaDelimitedString(String theCoach, Game theGame) {
        Team homeTeam;
        Team awayTeam;

        if (theGame.getTeam1().getType().equals(Team.TeamType.HOME)) {
            homeTeam = theGame.getTeam1();
            awayTeam = theGame.getTeam2();
        }
        else
        {
            awayTeam = theGame.getTeam1();
            homeTeam = theGame.getTeam2();
        }

        return theGame.getGameId()
                + commaThenAttribute(dateFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(timeFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(theGame.getDivision())
                + commaThenAttribute(homeTeam.getTeamName())
                + commaThenAttribute("vs.")
                + commaThenAttribute(awayTeam.getTeamName())
                + commaThenAttribute(theCoach)
                + commaThenAttribute(theGame.getLocation());
    }

    public String commaThenAttribute(String theAttribute) {
        if (theAttribute == null) {
            return ",";
        }

        return "," + theAttribute;
    }
}
