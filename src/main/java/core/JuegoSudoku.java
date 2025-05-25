package core;


import java.util.Scanner;

/**
 * Clase que permite jugar al Sudoku por consola.
 * Permite seleccionar dificultad, introducir números y ver el tablero.
 */
public class JuegoSudoku {

    private Sudoku sudoku;
    private Scanner sc;

    public JuegoSudoku() {
        sudoku = new Sudoku();
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("🎮 BIENVENIDO A SUDOKU (modo consola)");
        System.out.println("--------------------------------------");

        // Elegir dificultad
        String dificultad = seleccionarDificultad();
        sudoku.generarTablero(dificultad);

        // Bucle principal
        while (true) {
            sudoku.mostrarTablero();

            if (sudoku.estaResuelto()) {
                System.out.println("🎉 ¡Enhorabuena! Has completado el Sudoku.");
                break;
            }

            System.out.print("\nIntroduce la fila (1-9): ");
            int fila = sc.nextInt() - 1;

            System.out.print("Introduce la columna (1-9): ");
            int col = sc.nextInt() - 1;

            System.out.print("Introduce el número (1-9): ");
            int valor = sc.nextInt();

            if (!sudoku.getCeldasFijas()[fila][col]) {
                boolean exito = sudoku.colocarNumero(fila, col, valor);
                if (!exito) {
                    System.out.println("❌ Movimiento inválido. Revisa las reglas.");
                }
            } else {
                System.out.println("⛔ Esa celda es fija, no puedes modificarla.");
            }

            System.out.println();
        }
    }

    private String seleccionarDificultad() {
        while (true) {
            System.out.print("Selecciona dificultad (facil / medio / dificil): ");
            String dif = sc.next().toLowerCase();
            if (dif.equals("facil") || dif.equals("medio") || dif.equals("dificil")) {
                return dif;
            }
            System.out.println("❌ Opción no válida. Intenta de nuevo.");
        }
    }
}
