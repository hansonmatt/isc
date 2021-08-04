package org.issaquahsoccerclub.model;

import java.util.Calendar;
import java.util.Date;

public class Game {
    public static final Date BAD_DATE = new Calendar.Builder().setDate(2000, 01, 01).build().getTime();

    private String gameId;
    private Date gameDate;
    private String division;
    private String location;
    private String locationUrl;
    private Team team1;
    private Team team2;

    public Game(String gameId, Date gameDate, String division, String location, Team team1, Team team2) {
        this.gameId = gameId;
        this.gameDate = gameDate;
        this.division = division;
        this.location = location;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", gameDate=" + gameDate +
                ", division='" + division + '\'' +
                ", location='" + location + '\'' +
                ", locationUrl='" + locationUrl + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
