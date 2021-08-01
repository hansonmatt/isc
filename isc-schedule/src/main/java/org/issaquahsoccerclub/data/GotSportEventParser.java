package org.issaquahsoccerclub.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GotSportEventParser {
    private GotSportEventDivisionPageParser gotSportEventDivisionPageParser = new GotSportEventDivisionPageParser();

    public void schedule(String theURL, IGotSportDivisionParserCallback theCallback) throws MalformedURLException, IOException {
        URL url = new URL(theURL);
        Document document = Jsoup.parse(url, 5000);

        List<Element> divisionSchedules = new LinkedList<>(document.getElementsByClass("schedule"));
        for (Element divisionSchedule : divisionSchedules) {
            String path = divisionSchedule.attributes().get("href");

            if (!path.contains("GroupID")) {
                String scheduleUrl = url.getProtocol() + "://" + url.getHost() + "/events/" + path;
                String host = url.getHost() + "/events/" + scheduleUrl;
                gotSportEventDivisionPageParser.schedule(scheduleUrl, theCallback);
            }

        }
    }
}
