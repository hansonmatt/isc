package org.issaquahsoccerclub.data;

import org.junit.Assert;
import org.junit.Test;

public class TestGotSportEventParser {
    private GotSportEventParser gotSportEventParser = new GotSportEventParser();

    private TestGotSportDivisionParserCallback callback = new TestGotSportDivisionParserCallback();

    private GotSportEventDivisionPageParser gotSportEventDivisionPageParser = new GotSportEventDivisionPageParser();

    // TODO: this is an integration test, dependency injection and mock objects
    @Test
    public void testEvent() throws Exception {
        gotSportEventParser.setGotSportEventDivisionPageParser(gotSportEventDivisionPageParser);

        String eventUrl = "https://events.gotsport.com/events/default.aspx?EventID=79629";
        gotSportEventParser.schedule(eventUrl, callback);
        Assert.assertTrue(!callback.gameMap.isEmpty());
    }
}
