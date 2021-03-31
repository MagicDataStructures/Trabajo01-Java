package co.edu.unal.chess.pieces;

public class King extends Piece {

    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♔" : "♚";
    }
}
