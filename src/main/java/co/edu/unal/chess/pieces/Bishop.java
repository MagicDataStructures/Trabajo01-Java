package co.edu.unal.chess.pieces;

public class Bishop extends Piece {

    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♗" : "♝";
    }
}
