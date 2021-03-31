package co.edu.unal.chess;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class ChessAnnotator {

    private final Logger logger = Logger.getLogger(ChessAnnotator.class.getName());
    private final ArrayList<Position> positions = new ArrayList<>();

    private String whiteName;
    private String blackName;

    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var annotator = new ChessAnnotator();

        annotator.getLogger().info("Ingrese el nombre del que juega blancas");
        annotator.setWhiteName(in.nextLine());

        annotator.getLogger().info("Ingrese el nombre del que juega negras");
        annotator.setBlackName(in.nextLine());

        while (true) {
            annotator.getLogger().info("Escriba un movimiento o una operación (*imprimir, *eliminar, *editar)");

            var instruction = in.nextLine();

            switch (instruction) {
                // Imprimir (mostrar en consola) en cualquier momento el listado de jugadas
                //  guardadas.
                case "*imprimir":
                    break;
                // Adición de una ronda en medio de la partida
                case "*insertar":
                    break;
                // Buscar una ronda por su identificador.
                case "*buscar":
                    break;
                case "*eliminar":
                    break;
                // Corrección de las anotaciones realizadas en una ronda.
                case "*editar":
                    break;
                // consultar las fichas capturadas
                //  debe ordenar las fichas por el orden alfabético y color. Puede usar más de un
                //  arreglo.
                case "*capturar":
                    break;
                // Consultar las rondas guardadas
                case "*consultar":
                    break;
                case "*salir":
                    return;
                // Anotar cada una de las rondas sin límite alguno y siguiendo el sistema de
                //  anotación estándar.
                default:
                    break;
            }
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public void setWhiteName(String whiteName) {
        this.whiteName = whiteName;
    }

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }
}
