package co.edu.unal.chess;

public class Position {

    private Horizontal horizontal;
    private byte vertical;

    public Position(Horizontal horizontal, byte vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Horizontal horizontal) {
        this.horizontal = horizontal;
    }

    public byte getVertical() {
        return vertical;
    }

    public void setVertical(byte vertical) {
        this.vertical = vertical;
    }

    public enum Horizontal {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
    }

    @Override
    public String toString() {
        return "%s%s".formatted(horizontal, vertical);
    }
}
