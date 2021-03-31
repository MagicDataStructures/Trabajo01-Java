package co.edu.unal.chess;

public class Position {

    private Horizontal horizontal;
    private byte vertical;

    public Position(String input) {
        horizontal = Horizontal.valueOf(("" + input.charAt(0)).toUpperCase());
        vertical = Byte.parseByte("" + input.charAt(1));
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
}
