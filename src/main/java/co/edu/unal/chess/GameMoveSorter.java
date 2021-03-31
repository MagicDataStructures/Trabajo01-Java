package co.edu.unal.chess;

import java.util.Comparator;

public class GameMoveSorter implements Comparator<GameMove> {
    @Override
    public int compare(GameMove x, GameMove y) {
            assert x.getPosition() != null;
            assert y.getPosition() != null;

            return x.getPosition().getHorizontal().compareTo(y.getPosition().getHorizontal());
    }
}