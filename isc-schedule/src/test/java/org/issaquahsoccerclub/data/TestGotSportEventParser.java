package org.issaquahsoccerclub.data;

import org.junit.Assert;
import org.junit.Test;

public class TestGotSportEventParser {
    private GotSportEventParser gotSportEventParser = new GotSportEventParser();

    @Test
    public void testEvent() throws Exception {
        TestGotSportDivisionParserCallback callback = new TestGotSportDivisionParserCallback();

        String eventUrl = "https://events.gotsport.com/events/default.aspx?EventID=79629";
        gotSportEventParser.schedule(eventUrl, callback);
        Assert.assertTrue(!callback.gotSportFlatGameMap.isEmpty());
    }
}
