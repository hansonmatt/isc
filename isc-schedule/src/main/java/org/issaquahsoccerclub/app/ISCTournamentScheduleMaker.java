package org.issaquahsoccerclub.app;

import org.issaquahsoccerclub.data.GotSportEventParser;
import org.issaquahsoccerclub.data.ISCGotSoccerParserCallback;

import java.io.File;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ISCTournamentScheduleMaker {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ISCTournamentScheduleMaker.class.getName());

        if (args.length < 2) {
            System.out.println("usage: ISCTournamentScheduleMaker <tournament url> <output filename>");
            return;
        }

        String tournamentUrl = args[0];
        String fileName = args[1];
        try {
            ISCGotSoccerParserCallback callback = new ISCGotSoccerParserCallback(); //new PrintStreamDivisionParserCallback(stream);
            GotSportEventParser parser = new GotSportEventParser();
            long t0 = System.currentTimeMillis();
            parser.schedule(tournamentUrl, callback);
            long t1 = System.currentTimeMillis();
            logger.log(Level.INFO, "ISC tournament scheduling complete. Parsing runtime = '" + (t1 - t0) + "' ms");

            File f = new File(fileName);
            while (!f.createNewFile()) {
                f.renameTo(new File("." + fileName + "." + System.currentTimeMillis()));
            }

            PrintStream stream = new PrintStream(f);
            callback.finalize(stream);
        } catch (Exception x) {
            logger.log(Level.SEVERE, "Top-level exception caught", x);
        }
    }
}
