package co.edu.unal.chess;

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
                    parseAnnotation();
                    break;
            }
        }
    }

    private void parseAnnotation(String annotation) {
        var regex = "(?>(?<NormalMove>(?<Type>[KQRBN])?(?<CaptureDisambiguation>[a-h])?(?<Capture>[x:])?(?<Row>" +
                "[a-h])(?<Column>[0-8])(?>=(?<PromotionType>[KQRBN]))?(?<Check>\\+)?(?<CheckMate>#)?)|(?<QueenSideCastling>" +
                "O-O-O)|(?<Castling>O-O)|(?<WhiteWon>1-0)|(?<BlackWon>0-1))";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(annotation);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
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
