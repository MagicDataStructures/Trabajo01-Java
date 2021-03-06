package co.edu.unal.chess;

import co.edu.unal.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessAnnotator {

    private final Logger logger = Logger.getLogger(ChessAnnotator.class.getName());
    private final ArrayList<GameRound> rounds = new ArrayList<>();
    private final ArrayList<GameMove> captures = new ArrayList<>();

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

    private String checkArgument(String[] arguments, int position) {
        if (position >= arguments.length) {
            throw new RuntimeException("Falta un parámetro en la posición %d".formatted(position));
        }
        return arguments[position];
    }

    private void mainLoop() {
        while (true) {
            getLogger().info("Escriba un movimiento (en notación estándar) o una operación (*imprimir, *insertar [posición] [movimiento]," +
                    " *consultar, *eliminar [posición], *editar [posición] [movimiento], *capturas, *salir):");

            var instruction = input.nextLine();
            var parameters = instruction.split(" ");

            try {
                switch (parameters[0]) {
                    case "*imprimir" -> {
                        getLogger().info("[White \"%s\"]".formatted(getWhiteName()));
                        getLogger().info("[Black \"%s\"]".formatted(getBlackName()));

                        for (int i = 0, roundsSize = rounds.size(); i < roundsSize; i++) {
                            var round = rounds.get(i);
                            getLogger().info("%d. %s".formatted(i + 1, round.toString()));
                        }
                    }
                    case "*insertar" -> {
                        var pos = Integer.parseInt(checkArgument(parameters, 1));
                        var white = checkArgument(parameters, 2);
                        var black = checkArgument(parameters, 3);

                        rounds.add(pos, parseAnnotation("%s %s".formatted(white, black)));

                        getLogger().info("Se ha insertado un elemento");
                    }
                    case "*eliminar" -> {
                        var pos = Integer.parseInt(checkArgument(parameters, 1));

                        rounds.remove(pos);

                        getLogger().info("Se ha eliminado un elemento");
                    }
                    case "*editar" -> {
                        var pos = Integer.parseInt(checkArgument(parameters, 1));
                        var white = checkArgument(parameters, 2);
                        var black = checkArgument(parameters, 3);

                        rounds.set(pos, parseAnnotation("%s %s".formatted(white, black)));

                        getLogger().info("Se ha editado un elemento");
                    }
                    case "*capturas" -> {
                        for (int i = 0, roundsSize = rounds.size(); i < roundsSize; i++) {
                            var round = captures.get(i);
                            getLogger().info("%d. %s".formatted(i + 1, round.toString()));
                        }
                    }
                    case "*consultar" -> {
                        var pos = Integer.parseInt(checkArgument(parameters, 1));

                        getLogger().info(rounds.get(pos).toString());
                    }
                    case "*salir" -> {
                        return;
                    }
                    default -> {
                        rounds.add(parseAnnotation(instruction));
                        getLogger().info("Se ha insertado un elemento");
                    }
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

            var column = Byte.parseByte(matcher.group("Column"));
            var row = Position.Horizontal.valueOf(matcher.group("Row").toUpperCase());

            move.setPosition(new Position(row, column));

            if (matcher.group("Capture") != null) {
                move.setMoveType(GameMove.GameMoveType.CAPTURE);

                captures.add(move);
                captures.sort(new GameMoveSorter());
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
