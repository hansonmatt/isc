package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

import java.io.PrintStream;

public class PrintStreamDivisionParserCallback implements IGotSportDivisionParserCallback {
    private PrintStream stream;

    public PrintStreamDivisionParserCallback(PrintStream theStream) {
        this.stream = theStream;
    }

    public void handleEvent(Game theGame) {
        stream.println(theGame);
    }
}
