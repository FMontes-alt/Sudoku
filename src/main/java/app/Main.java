package app;

import core.JuegoSudoku;
import gui.SudokuGUI;

import javax.swing.*;
import java.util.Scanner;

/**
 * Clase principal que permite lanzar el juego en modo consola o gráfico.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🧩 ¿Cómo quieres jugar?");
        System.out.println("1. Modo consola");
        System.out.println("2. Modo gráfico (Swing)");
        System.out.print("Elige una opción: ");
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                JuegoSudoku juego = new JuegoSudoku();
                juego.iniciar();
                break;
            case "2":
                // Asegura que Swing se lanza en el hilo correcto
                SwingUtilities.invokeLater(() -> new SudokuGUI());
                break;
            default:
                System.out.println("❌ Opción no válida. Ejecuta de nuevo el programa.");
        }
    }
}