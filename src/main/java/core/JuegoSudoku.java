package core;

import java.util.Scanner;

public class JuegoSudoku {
    public static void main(String[] args) {
        Sudoku juego = new Sudoku(new int[9][9], new boolean[9][9]);
        Scanner sc = new Scanner(System.in);
        int intentosRestantes = 5;
        boolean exito = false;

        System.out.println("🎮 Bienvenido al Sudoku");

        //! Selección de la dificultad
        String dificultad;
        do {
            System.out.print("Elige dificultad (facil / normal / dificil): ");
            dificultad = sc.nextLine().toLowerCase();

            if (!dificultad.equals("facil") && !dificultad.equals("normal") && !dificultad.equals("dificil")) {
                System.out.println("⚠️ Dificultad inválida. Intenta de nuevo.");
            }
        } while (!dificultad.equals("facil") && !dificultad.equals("normal") && !dificultad.equals("dificil"));
        juego.generarTablero(dificultad);

        //! Intentos restantes y validaciones
        while (!juego.estaResuelto() && intentosRestantes > 0) {
            juego.mostrarTablero();

            int fila;
            do {
                System.out.print("Fila (0-8): ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠️ Solo se permiten números entre 0 y 8.");
                    sc.next(); // descarta entrada no numérica
                }
                fila = sc.nextInt();
            } while (fila < 0 || fila > 8);

            int columna;
            do {
                System.out.print("Col (0-8): ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠️ Solo se permiten números entre 0 y 8.");
                    sc.next(); // descarta entrada no numérica
                }
                columna = sc.nextInt();
            } while (columna < 0 || columna > 8);


            int valor;
            do {
                System.out.print("Valor (1-9): ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠️ Solo se permiten números entre 1 y 9.");
                    sc.next(); // descarta entrada no numérica
                }
                valor = sc.nextInt();
            } while (fila < 1 || fila > 9);

            exito = juego.colocarNumero(fila, columna, valor);
            if (!exito) {
                intentosRestantes--;
                System.out.println("⚠️ Movimiento inválido. Te quedan " + intentosRestantes + " intentos.");
            }
        }

        if (juego.estaResuelto()) {
            juego.mostrarTablero();
            System.out.println("🎉 ¡Has completado el Sudoku!");
        } else {
            System.out.println("💥 Has agotado tus intentos. Fin del juego.");
        }

        sc.close();
    }
}
