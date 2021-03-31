package co.edu.unal.chess;

import java.util.Scanner;
import java.util.logging.Logger;

public class ChessAnnotator {
    private final Logger logger = Logger.getLogger(ChessAnnotator.class.getName());

    private String whiteName;
    private String blackName;

    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var annotator = new ChessAnnotator();

        annotator.getLogger().info("Ingrese el nombre del que juega blancas");
        annotator.setWhiteName(in.nextLine());

        annotator.getLogger().info("Ingrese el nombre del que juega negras");
        annotator.setBlackName(in.nextLine());
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
