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
import java.util.logging.Level;
import java.util.logging.Logger;

public class GotSportEventParser {
    private GotSportEventDivisionPageParser gotSportEventDivisionPageParser = new GotSportEventDivisionPageParser();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void schedule(String theURL, IGotSportDivisionParserCallback theCallback) throws MalformedURLException, IOException {
        URL url = new URL(theURL);
        long t0 = System.currentTimeMillis();
        Document document = Jsoup.parse(url, 5000);
        long t1 = System.currentTimeMillis();
        logger.log(Level.INFO, "GotSportEventParser parsing for url = '" + theURL + "' took '" + (t1 - t0) + "' ms");

        List<Element> divisionSchedules = new LinkedList<>(document.getElementsByClass("schedule"));
        for (Element divisionSchedule : divisionSchedules) {
            String path = divisionSchedule.attributes().get("href");

            if (!path.contains("GroupID")) {
                String scheduleUrl = url.getProtocol() + "://" + url.getHost() + "/events/" + path;
                gotSportEventDivisionPageParser.schedule(scheduleUrl, theCallback);
            }

        }
    }
}
