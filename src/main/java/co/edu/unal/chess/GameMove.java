package co.edu.unal.chess;

import co.edu.unal.chess.pieces.Piece;
import org.jetbrains.annotations.Nullable;

public class GameMove {

    @Nullable
    private Piece piece;

    private GameMoveType moveType;

    @Nullable
    private Position position;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "GameMove{" +
                "piece=" + piece +
                ", moveType=" + moveType +
                ", position=" + position +
                '}';
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
}
