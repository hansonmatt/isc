package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;
import org.junit.Assert;
import org.junit.Test;

public class TestGotSportEventDivisionPageParser {
    private GotSportEventDivisionPageParser gotSportEventDivisionPageParser = new GotSportEventDivisionPageParser();

    // TODO: this is an integration test, dependency injection and mock objects
    @Test
    public void testSchedule() throws Exception {
        TestGotSportDivisionParserCallback callback = new TestGotSportDivisionParserCallback();

        // found a tournament page that does not conform to expected gotsport spec...
        // https://events.gotsport.com/events/?EventID=83479

        String testScheduleURL = "https://events.gotsport.com/events/schedule.aspx?EventID=79629&GroupID=1057505&Gender=Boys&Age=14";
        gotSportEventDivisionPageParser.schedule(testScheduleURL, callback);
        Assert.assertEquals(10, callback.gameMap.size());
        Game game = callback.gameMap.remove("128");
        game = callback.gameMap.remove("124");
        game = callback.gameMap.remove("125");
        game = callback.gameMap.remove("126");
        game = callback.gameMap.remove("129");
        game = callback.gameMap.remove("127");
        game = callback.gameMap.remove("131");
        game = callback.gameMap.remove("132");
        game = callback.gameMap.remove("130");
        game = callback.gameMap.remove("475");
        Assert.assertTrue(callback.gameMap.isEmpty());

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Girls&Age=19", callback);
        Assert.assertEquals(24, callback.gameMap.size());
        game = callback.gameMap.remove("576");
        game = callback.gameMap.remove("577");
        game = callback.gameMap.remove("578");
        game = callback.gameMap.remove("579");
        game = callback.gameMap.remove("574");
        game = callback.gameMap.remove("575");
        game = callback.gameMap.remove("592");
        game = callback.gameMap.remove("580");
        game = callback.gameMap.remove("581");
        game = callback.gameMap.remove("585");
        game = callback.gameMap.remove("584");
        game = callback.gameMap.remove("582");
        game = callback.gameMap.remove("583");
        game = callback.gameMap.remove("593");
        game = callback.gameMap.remove("586");
        game = callback.gameMap.remove("587");
        game = callback.gameMap.remove("589");
        game = callback.gameMap.remove("591");
        game = callback.gameMap.remove("588");
        game = callback.gameMap.remove("590");
        game = callback.gameMap.remove("594");
        game = callback.gameMap.remove("595");
        game = callback.gameMap.remove("597");
        game = callback.gameMap.remove("596");
        Assert.assertTrue(callback.gameMap.isEmpty());

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&GroupID=1036077&Gender=Girls&Age=14", callback);
        Assert.assertEquals(13, callback.gameMap.size());
        callback.gameMap.clear();

        gotSportEventDivisionPageParser.schedule("http://events.gotsport.com/events/schedule.aspx?EventID=83800&Gender=Boys&Age=15", callback);
        Assert.assertEquals(20, callback.gameMap.size());

        callback.gameMap.clear();
        gotSportEventDivisionPageParser.schedule("https://events.gotsport.com/events/schedule.aspx?EventID=79629&Gender=Boys&Age=9", callback);
        Assert.assertEquals(21, callback.gameMap.size());
    }
}
