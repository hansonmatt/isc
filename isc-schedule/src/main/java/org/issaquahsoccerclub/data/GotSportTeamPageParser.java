package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GotSportTeamPageParser {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private Map<String, Team> teamMap = new HashMap<>();

    public Team getTeam(String theURL) throws IOException {
        if (theURL == null) {
            throw new IllegalArgumentException("Team URL cannot be null.");
        }

        if (teamMap.containsKey(theURL)) {
            Team team = teamMap.get(theURL);
            logger.log(Level.INFO, "Returning cached team '" + team.getTeamId() + "' for URL = '" + theURL + "'");
            return teamMap.get(theURL);
        }

        long t0 = System.currentTimeMillis();
        Document document = Jsoup.parse(new URL(theURL), 5000);
        long t1 = System.currentTimeMillis();


        Element teamId = document.getElementById("MainBody_TeamIDNumber");
        Element coachName = document.getElementById("MainBody_CoachName");
        Element teamName = document.getElementById("MainBody_TeamName");
        Element teamState = document.getElementById("MainBody_TeamState");
        Element managerName = document.getElementById("MainBody_ManagerName");

        Team team = new Team(teamId.ownText(), teamName.ownText(), coachName.ownText(), managerName.ownText(), teamState.ownText(), theURL);
        teamMap.put(theURL, team);

        logger.log(Level.INFO, "GotSportTeamPageParser parsing for team '" + team.getTeamId() + "', name = '" + team.getTeamName() + "', url = '" + theURL + "' took '" + (t1 - t0) + "' ms");

        return team;
    }
}
