package app;

import core.JuegoSudoku;
import gui.SudokuGUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎮 ¿Cómo quieres jugar?");
        System.out.println("1. Consola");
        System.out.println("2. Interfaz Gráfica (Swing)");
        System.out.print("Elige una opción: ");

        int opcion = 0;
        while (opcion != 1 && opcion != 2) {
            while (!sc.hasNextInt()) {
                System.out.println("⚠️ Introduce 1 o 2.");
                sc.next();
            }
            opcion = sc.nextInt();
            if (opcion != 1 && opcion != 2) {
                System.out.println("⚠️ Opción inválida. Elige 1 o 2.");
            }
        }

        if (opcion == 1) {
            JuegoSudoku.main(null); // ejecuta la versión consola
        } else {
            new SudokuGUI(); // lanza la GUI directamente
        }

        sc.close();
    }
}