package org.issaquahsoccerclub.model;

import java.util.Date;

public class Game {
    public static final Date BAD_DATE = new Date();

    private String gameId;
    private Date gameDate;
    private String division;
    private String team1Id;
    private String team2Id;
    private String homeTeamId;
    private String location;

    public String getGameId() {
        return gameId;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public String getDivision() {
        return division;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public String getLocation() {
        return location;
    }

    public Game(String theGameId, Date theGameDate, String theDivision, String theTeam1, String theTeam2, String theHomeTeamId, String theLocation) {
        this.gameId = theGameId;
        this.gameDate = theGameDate;
        this.division = theDivision;
        this.team1Id = theTeam1;
        this.team2Id = theTeam2;
        this.location = theLocation;
        this.homeTeamId = theHomeTeamId;
    }
}
