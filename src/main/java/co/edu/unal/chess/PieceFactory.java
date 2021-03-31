package co.edu.unal.chess;

import co.edu.unal.chess.pieces.*;

public class PieceFactory {

    public static Piece createPiece(String name) {
        return switch (name) {
            case "K" -> new King();
            case "Q" -> new Queen();
            case "R" -> new Rook();
            case "B" -> new Bishop();
            case "N" -> new Knight();
            default -> null;
        };
    }
}
