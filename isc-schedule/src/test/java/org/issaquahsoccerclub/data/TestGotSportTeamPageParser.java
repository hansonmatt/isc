package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Team;
import org.junit.Assert;
import org.junit.Test;

public class TestGotSportTeamPageParser {
    private GotSportTeamPageParser gotSportTeamPageParser = new GotSportTeamPageParser();

    // TODO: this is an integration test, dependency injection and mock objects
    @Test
    public void testGetSportTeamPageParser() throws Exception {
        String url = "https://events.gotsport.com/events/schedule.aspx?eventid=79629&FieldID=0&applicationID=6302380&action=Go";
        Team team = gotSportTeamPageParser.getTeam(url);
        Assert.assertNotNull(team);
    }
}
