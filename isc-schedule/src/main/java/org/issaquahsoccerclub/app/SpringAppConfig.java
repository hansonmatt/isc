package org.issaquahsoccerclub.app;

import org.issaquahsoccerclub.data.GotSportEventDivisionPageParser;
import org.issaquahsoccerclub.data.GotSportEventParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {

    @Bean
    public GotSportEventDivisionPageParser gotSportEventDivisionPageParser() {
        return new GotSportEventDivisionPageParser();
    }

    @Bean
    public GotSportEventParser gotSportEventParser() {
        GotSportEventParser parser = new GotSportEventParser();
        parser.setGotSportEventDivisionPageParser(gotSportEventDivisionPageParser());
        return parser;
    }
}
