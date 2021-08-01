package org.issaquahsoccerclub.app;

import org.issaquahsoccerclub.data.GotSportEventParser;
import org.issaquahsoccerclub.data.IGotSportDivisionParserCallback;
import org.issaquahsoccerclub.data.PrintStreamDivisionParserCallback;

import java.io.File;
import java.io.PrintStream;

/**
 * Hello world!
 *
 */
public class ISCTournamentScheduleMaker
{
    public static void main( String[] args )
    {
        if (args.length < 2) {
            System.out.println("usage: ISCTournamentScheduleMaker <tournament url> <output filename>");
            return;
        }

        String tournamentUrl = args[0];
        String fileName = args[1];
        try {
            File f = new File(fileName);
            while (!f.createNewFile()) {
                f.renameTo(new File("." + fileName + "." + System.currentTimeMillis()));
            }

            PrintStream stream = new PrintStream(f);
            IGotSportDivisionParserCallback callback = new PrintStreamDivisionParserCallback(stream);
            GotSportEventParser parser = new GotSportEventParser();
            long t0 = System.currentTimeMillis();
            parser.schedule(tournamentUrl, callback);
            long t1 = System.currentTimeMillis();
            System.out.println("ISC tournament scheduling complete. Parsing runtime = '" + (t1 - t0) + "' ms");
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }
}
