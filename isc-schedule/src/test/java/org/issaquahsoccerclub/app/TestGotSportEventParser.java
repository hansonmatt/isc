package org.issaquahsoccerclub.app;

import org.issaquahsoccerclub.data.GotSportEventParser;
import org.issaquahsoccerclub.data.IGotSportEventParserCallback;
import org.junit.Test;

public class TestGotSportEventParser {
    private GotSportEventParser gotSportEventParser = new GotSportEventParser();

    @Test
    public void testSchedule() throws Exception {
        IGotSportEventParserCallback callback = new PrintStreamEventParserCallback(System.out);

        String testScheduleURL = "https://events.gotsport.com/events/schedule.aspx?EventID=79629&GroupID=1057505&Gender=Boys&Age=14";
                //"http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Boys&Age=15";
        gotSportEventParser.schedule(testScheduleURL, callback);
        gotSportEventParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Girls&Age=19", callback);
        //gotSportEventParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&GroupID=1036077&Gender=Girls&Age=14", callback);

    }
}
