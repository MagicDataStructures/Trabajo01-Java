package co.edu.unal.chess;

import co.edu.unal.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessAnnotator {

    private final Logger logger = Logger.getLogger(ChessAnnotator.class.getName());
    private final ArrayList<Position> positions = new ArrayList<>();

    private final Scanner input;

    private final String whiteName;
    private final String blackName;

    public ChessAnnotator() {
        input = new Scanner(System.in);

        getLogger().info("Ingrese el nombre del que juega blancas");
        whiteName = input.nextLine();

        getLogger().info("Ingrese el nombre del que juega negras");
        blackName = input.nextLine();

        mainLoop();
    }

    public static void main(String[] args) {
        new ChessAnnotator();
    }

    private void mainLoop() {
        while (true) {
            getLogger().info("Escriba un movimiento (en notación estándar) o una operación (*imprimir, *insertar," +
                    " *consultar, *eliminar, *editar, *capturas, *salir):");

            var instruction = input.nextLine();
            var parameters = instruction.split(" ");

            try {
                switch (parameters[0]) {
                    // Imprimir (mostrar en consola) en cualquier momento el listado de jugadas
                    //  guardadas.
                    case "*imprimir":
                        break;
                    // Adición de una ronda en medio de la partida
                    case "*insertar":
                        break;
                    case "*eliminar":
                        break;
                    // Corrección de las anotaciones realizadas en una ronda.
                    case "*editar":
                        break;
                    // consultar las fichas capturadas
                    //  debe ordenar las fichas por el orden alfabético y color
                    case "*capturas":
                        break;
                    // Consultar las rondas guardadas
                    case "*consultar":
                        break;
                    case "*salir":
                        return;
                    // Anotar cada una de las rondas sin límite alguno y siguiendo el sistema de
                    //  anotación estándar.
                    default:
                        getLogger().info(parseAnnotation(instruction).toString());
                        break;
                }
            } catch (Exception e) {
                getLogger().warning(e.toString());
            }
        }
    }

    private GameRound parseAnnotation(String annotation) {
        var regex = "(?>(?<NormalMove>(?<Type>[KQRBN])?(?<Disambiguation>[a-h])?(?<Capture>[x:])?(?<Row>" +
                "[a-h])(?<Column>[0-8])(?>=(?<PromotionType>[KQRBN]))?(?<Check>\\+)?(?<CheckMate>#)?)|(?<QueenSideCastling>" +
                "O-O-O)|(?<Castling>O-O)|(?<WhiteWon>1-0)|(?<BlackWon>0-1))";
        var pattern = Pattern.compile(regex, Pattern.MULTILINE);
        var matcher = pattern.matcher(annotation);

        var round = new GameRound();

        if (matcher.find()) {
            round.setWhiteMove(parseMove(Piece.PieceColor.WHITE, matcher));
        } else {
            throw new RuntimeException("No se pudo encontrar el movimiento especificado para blancas");
        }

        if (matcher.find()) {
            round.setBlackMove(parseMove(Piece.PieceColor.BLACK, matcher));
        } else {
            throw new RuntimeException("No se pudo encontrar el movimiento especificado para negras");
        }

        return round;
    }

    private GameMove parseMove(Piece.PieceColor color, Matcher matcher) {
        var move = new GameMove();

        if (matcher.group("NormalMove") != null) {
            var piece = PieceFactory.createPiece(matcher.group("Type"));
            piece.setColor(color);
            move.setPiece(piece);

            if (matcher.group("Capture") != null) {
                move.setMoveType(GameMove.GameMoveType.CAPTURE);
            } else if (matcher.group("Check") != null) {
                move.setMoveType(GameMove.GameMoveType.CHECK);
            } else if (matcher.group("CheckMate") != null) {
                move.setMoveType(GameMove.GameMoveType.CHECK_MATE);
            } else if (matcher.group("PromotionType") != null) {
                move.setMoveType(GameMove.GameMoveType.PROMOTION);
                move.setPiece(PieceFactory.createPiece(matcher.group("PromotionType")));
            } else {
                move.setMoveType(GameMove.GameMoveType.NORMAL);
            }
        } else if (matcher.group("QueenSideCastling") != null) {
            move.setMoveType(GameMove.GameMoveType.QUEEN_SIDE_CASTLING);
        } else if (matcher.group("Castling") != null) {
            move.setMoveType(GameMove.GameMoveType.CASTLING);
        } else if (matcher.group("WhiteWon") != null || matcher.group("BlackWon") != null) {
            move.setMoveType(GameMove.GameMoveType.END);
        } else {
            throw new RuntimeException("No se pudo validar el movimiento");
        }

        return move;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }
}
