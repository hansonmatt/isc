package org.issaquahsoccerclub.model;

public class Team {
    public enum TeamType {
        HOME,
        AWAY,
        NOT_SET
    }

    private String teamId;
    private String teamName;
    private String coach;
    private String manager;
    private String state;
    private String teamUrl;
    private TeamType type = TeamType.NOT_SET;

    public TeamType getType() {
        return type;
    }

    public void setType(TeamType type) {
        this.type = type;
    }

    public Team(String teamId, String teamName, String coach, String manager, String state, String teamUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.coach = coach;
        this.manager = manager;
        this.state = state;
        this.teamUrl = teamUrl;
    }

    public String getTeamUrl() {
        return teamUrl;
    }

    public void setTeamUrl(String teamUrl) {
        this.teamUrl = teamUrl;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Team(String teamId, String teamName, String coach, String manager, String state) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.coach = coach;
        this.manager = manager;
        this.state = state;
    }

    public Team(String teamId, String teamName, String coach) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.coach = coach;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", coach='" + coach + '\'' +
                ", manager='" + manager + '\'' +
                ", state='" + state + '\'' +
                ", teamUrl='" + teamUrl + '\'' +
                ", type=" + type +
                '}';
    }
}
