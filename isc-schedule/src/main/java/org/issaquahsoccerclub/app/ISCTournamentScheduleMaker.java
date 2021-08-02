package org.issaquahsoccerclub.app;

import org.issaquahsoccerclub.data.GotSportEventParser;
import org.issaquahsoccerclub.data.IGotSportDivisionParserCallback;
import org.issaquahsoccerclub.data.PrintStreamDivisionParserCallback;

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
            logger.log(Level.INFO, "ISC tournament scheduling complete. Parsing runtime = '" + (t1 - t0) + "' ms");
        } catch (Exception x) {
            logger.log(Level.SEVERE, "Top-level exception caught", x);
        }
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
////            String[] beanNames = ctx.getBeanDefinitionNames();
////            Arrays.sort(beanNames);
////            for (String beanName : beanNames) {
////                System.out.println(beanName);
////            }
//
//                    Logger logger = Logger.getLogger(ISCTournamentScheduleMaker.class.getName());
//                    logger.log(Level.INFO, "ISCTournamentScheduleMaker begin");
//
//        if (args.length < 2) {
//            System.out.println("usage: ISCTournamentScheduleMaker <tournament url> <output filename>");
//            return;
//        }
//
//        String tournamentUrl = args[0];
//        String fileName = args[1];
//        try {
//            File f = new File(fileName);
//            while (!f.createNewFile()) {
//                f.renameTo(new File("." + fileName + "." + System.currentTimeMillis()));
//            }
//
//            PrintStream stream = new PrintStream(f);
//            IGotSportDivisionParserCallback callback = new PrintStreamDivisionParserCallback(stream);
//            GotSportEventParser parser = new GotSportEventParser();
//            long t0 = System.currentTimeMillis();
//            parser.schedule(tournamentUrl, callback);
//            long t1 = System.currentTimeMillis();
//            logger.log(Level.INFO, "ISC tournament scheduling complete. Parsing runtime = '" + (t1 - t0) + "' ms");
//        }
//        catch (Exception x) {
//            logger.log(Level.SEVERE, "Top-level exception caught", x);
//        }
//
//        };
//    }
}
