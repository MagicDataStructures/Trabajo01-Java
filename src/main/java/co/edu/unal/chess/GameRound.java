package co.edu.unal.chess;

public class GameRound {

    private GameMove whiteMove;
    private GameMove blackMove;

    public GameRound() {
        super();
    }

    public GameRound(GameMove whiteMove, GameMove blackMove) {
        super();

        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
    }

    public GameMove getWhiteMove() {
        return whiteMove;
    }

    public void setWhiteMove(GameMove whiteMove) {
        this.whiteMove = whiteMove;
    }

    public GameMove getBlackMove() {
        return blackMove;
    }

    public void setBlackMove(GameMove blackMove) {
        this.blackMove = blackMove;
    }

    @Override
    public String toString() {
        return "Blanco=%s, Negro=%s".formatted(whiteMove, blackMove);
    }
}
