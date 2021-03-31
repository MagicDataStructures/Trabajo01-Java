package co.edu.unal.chess;

import co.edu.unal.chess.pieces.Piece;

import javax.annotation.Nullable;

public class GameMove {

    private Piece piece;
    private GameMoveType moveType;

    public GameMove() {
        super();
    }

    public GameMove(@Nullable Piece piece, GameMoveType moveType) {
        super();

        this.piece = piece;
        this.moveType = moveType;
    }

    public GameMoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(GameMoveType moveType) {
        this.moveType = moveType;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public enum GameMoveType {
        NORMAL,
        CAPTURE,
        PROMOTION,
        CHECK,
        CHECK_MATE,
        QUEEN_SIDE_CASTLING,
        CASTLING,
        END,
    }

    @Override
    public String toString() {
        return "GameMove{" +
                "piece=" + piece +
                ", moveType=" + moveType +
                '}';
    }
}
