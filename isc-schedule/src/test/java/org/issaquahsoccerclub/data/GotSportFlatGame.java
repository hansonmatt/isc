package org.issaquahsoccerclub.data;

public class GotSportFlatGame {
    public String division;
    public String bracket;
    public String gameID;
    public String gameDate;
    public String gameTime;
    public String gameLocation;
    public String gameLocationURL;
    public String homeTeam;
    public String awayTeam;

    public GotSportFlatGame(String theDivision, String theBracket, String theGameNum,
                            String theGameDate, String theGameTime,
                            String theHomeTeam, String theAwayTeam, String theLocation) {
        this.division = theDivision;
        this.bracket = theBracket;
        this.gameID = theGameNum;
        this.gameDate = theGameDate;
        this.gameTime = theGameTime;
        this.gameLocation = theLocation;
        this.homeTeam = theHomeTeam;
        this.awayTeam = theAwayTeam;
    }
}
