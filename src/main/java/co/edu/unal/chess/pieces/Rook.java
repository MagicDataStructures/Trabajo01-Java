package co.edu.unal.chess.pieces;

public class Rook extends Piece {

    @Override
    public String toString() {
        return getColor() == PieceColor.WHITE ? "♖" : "♜";
    }
}
