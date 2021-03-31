package co.edu.unal.chess.pieces;

public abstract class Piece {

    private PieceColor color;

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public enum PieceColor {
        WHITE,
        BLACK
    }
}
