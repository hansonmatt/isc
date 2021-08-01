package org.issaquahsoccerclub.data;

import org.junit.Assert;
import org.junit.Test;

public class TestGotSportEventDivisionPageParser {
    private GotSportEventDivisionPageParser gotSportEventDivisionPageParser = new GotSportEventDivisionPageParser();

    @Test
    public void testSchedule() throws Exception {
        TestGotSportDivisionParserCallback callback = new TestGotSportDivisionParserCallback();

        String testScheduleURL = "https://events.gotsport.com/events/schedule.aspx?EventID=79629&GroupID=1057505&Gender=Boys&Age=14";
        gotSportEventDivisionPageParser.schedule(testScheduleURL, callback);
        Assert.assertEquals(10, callback.gotSportFlatGameMap.size());
        GotSportFlatGame game = callback.gotSportFlatGameMap.remove("#128");
        game = callback.gotSportFlatGameMap.remove("#124");
        game = callback.gotSportFlatGameMap.remove("#125");
        game = callback.gotSportFlatGameMap.remove("#126");
        game = callback.gotSportFlatGameMap.remove("#129");
        game = callback.gotSportFlatGameMap.remove("#127");
        game = callback.gotSportFlatGameMap.remove("#131");
        game = callback.gotSportFlatGameMap.remove("#132");
        game = callback.gotSportFlatGameMap.remove("#130");
        game = callback.gotSportFlatGameMap.remove("#475");
        Assert.assertTrue(callback.gotSportFlatGameMap.isEmpty());

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Girls&Age=19", callback);
        Assert.assertEquals(24, callback.gotSportFlatGameMap.size());
        game = callback.gotSportFlatGameMap.remove("#576");
        game = callback.gotSportFlatGameMap.remove("#577");
        game = callback.gotSportFlatGameMap.remove("#578");
        game = callback.gotSportFlatGameMap.remove("#579");
        game = callback.gotSportFlatGameMap.remove("#574");
        game = callback.gotSportFlatGameMap.remove("#575");
        game = callback.gotSportFlatGameMap.remove("#592");
        game = callback.gotSportFlatGameMap.remove("#580");
        game = callback.gotSportFlatGameMap.remove("#581");
        game = callback.gotSportFlatGameMap.remove("#585");
        game = callback.gotSportFlatGameMap.remove("#584");
        game = callback.gotSportFlatGameMap.remove("#582");
        game = callback.gotSportFlatGameMap.remove("#583");
        game = callback.gotSportFlatGameMap.remove("#593");
        game = callback.gotSportFlatGameMap.remove("#586");
        game = callback.gotSportFlatGameMap.remove("#587");
        game = callback.gotSportFlatGameMap.remove("#589");
        game = callback.gotSportFlatGameMap.remove("#591");
        game = callback.gotSportFlatGameMap.remove("#588");
        game = callback.gotSportFlatGameMap.remove("#590");
        game = callback.gotSportFlatGameMap.remove("#594");
        game = callback.gotSportFlatGameMap.remove("#595");
        game = callback.gotSportFlatGameMap.remove("#597");
        game = callback.gotSportFlatGameMap.remove("#596");
        Assert.assertTrue(callback.gotSportFlatGameMap.isEmpty());

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&GroupID=1036077&Gender=Girls&Age=14", callback);
        Assert.assertEquals(13, callback.gotSportFlatGameMap.size());
        callback.gotSportFlatGameMap.clear();

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Boys&Age=15", callback);
        Assert.assertEquals(20, callback.gotSportFlatGameMap.size());

        callback.gotSportFlatGameMap.clear();
        gotSportEventDivisionPageParser.schedule("https://events.gotsport.com/events/schedule.aspx?EventID=79629&Gender=Boys&Age=9", callback);
        Assert.assertEquals(21, callback.gotSportFlatGameMap.size());
    }
}
