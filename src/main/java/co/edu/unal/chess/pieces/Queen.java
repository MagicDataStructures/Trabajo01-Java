package co.edu.unal.chess.pieces;

public class Queen extends Piece {

    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♕" : "♛";
    }
}
