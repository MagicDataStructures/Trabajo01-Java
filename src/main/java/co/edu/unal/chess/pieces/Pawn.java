package co.edu.unal.chess.pieces;

public class Pawn extends Piece {

    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♙" : "♟";
    }
}
