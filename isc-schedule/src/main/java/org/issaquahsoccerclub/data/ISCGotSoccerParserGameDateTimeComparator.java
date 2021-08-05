package org.issaquahsoccerclub.data;

import org.issaquahsoccerclub.model.Game;

import java.util.Comparator;

public class ISCGotSoccerParserGameDateTimeComparator implements Comparator<Game> {
    // TODO: unit testing
    @Override
    public int compare(Game o1, Game o2) throws IllegalArgumentException {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Games cannot be null");
        }

        return o1.getGameDate().compareTo(o2.getGameDate());
    }
}
