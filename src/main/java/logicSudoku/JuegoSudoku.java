package logicSudoku;

import java.util.Scanner;

public class JuegoSudoku {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int intentosRestantes = 5;

        System.out.println("🎮 Bienvenido al Sudoku");
        System.out.print("Elige dificultad (facil / normal / dificil): ");
        String dificultad = sc.nextLine().toLowerCase();
        boolean exito = false;
        Sudoku juego = new Sudoku(new int[9][9], new boolean[9][9]);
        juego.generarTablero(dificultad);


        while (!juego.estaResuelto() && intentosRestantes > 0) {
            juego.mostrarTablero();

            System.out.println("Fila (0-8): ");
            int fila = sc.nextInt();

            System.out.println("Columna (0-8): ");
            int columna = sc.nextInt();

            System.out.println("Número (1-9): ");
            int valor = sc.nextInt();

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
