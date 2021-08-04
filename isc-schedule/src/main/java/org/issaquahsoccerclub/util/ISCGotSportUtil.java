package org.issaquahsoccerclub.util;

import org.issaquahsoccerclub.model.Game;
import org.issaquahsoccerclub.model.Team;

import java.text.SimpleDateFormat;

public class ISCGotSportUtil {
    // TODO: config-based date/time formatting
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mmaa");

    public static final String GOT_SPORT_BASE_URL = "https://events.gotsport.com";

    // TODO: hardcore unit testing
    public static String gameToISCCommaDelimitedString(Game theGame) {
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

        // TODO: abstract away weak coach concatenation and hard-coded ISC filtering
        String iscCoach = "";
        if (homeTeam.getTeamName().contains("ISC"))
        {
            iscCoach = homeTeam.getCoach();
        }

        if (awayTeam.getTeamName().contains("ISC"))
        {
            if (!iscCoach.isEmpty()) {
                iscCoach = iscCoach.concat("|");
            }
            iscCoach = iscCoach.concat(awayTeam.getCoach());
        }

        return theGame.getGameId()
                + commaThenAttribute(dateFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(timeFormatter.format(theGame.getGameDate()))
                + commaThenAttribute(theGame.getDivision())
                + commaThenAttribute(homeTeam.getTeamName())
                + commaThenAttribute("vs.")
                + commaThenAttribute(awayTeam.getTeamName())
                + commaThenAttribute(iscCoach)
                + commaThenAttribute(theGame.getLocation());
    }

    private static String commaThenAttribute(String theAttribute) {
        if (theAttribute == null) {
            return ",";
        }

        return "," + theAttribute;
    }
}
