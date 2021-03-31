package co.edu.unal.chess.pieces;

public class Knight extends Piece {
    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♘" : "♞";
    }
}
