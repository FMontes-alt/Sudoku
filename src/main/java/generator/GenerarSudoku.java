package generator;

import java.util.Random;

/**
 * Clase que permite generar tableros de Sudoku válidos
 * con diferentes niveles de dificultad.
 * Usa el algoritmo de backtracking para generar tableros completos
 * y luego elimina celdas para simular los niveles: fácil, medio y difícil.
 */
public class GenerarSudoku {

    private static final Random random = new Random();

    /**
     * Genera un tablero con nivel fácil.
     * Deja hasta 30 celdas vacías.
     *
     * @return Tablero 9x9 con valores iniciales y huecos.
     */
    public int[][] generarFacil() {
        return generarConDificultad(30);
    }

    /**
     * Genera un tablero con nivel medio.
     * Deja hasta 40 celdas vacías.
     *
     * @return Tablero 9x9 con valores iniciales y huecos.
     */
    public int[][] generarNormal() {
        return generarConDificultad(40);
    }

    /**
     * Genera un tablero con nivel difícil.
     * Deja hasta 50 celdas vacías.
     *
     * @return Tablero 9x9 con valores iniciales y huecos.
     */
    public int[][] generarDificil() {
        return generarConDificultad(50);
    }

    /**
     * Genera un tablero completo y luego elimina celdas
     * para adaptarlo al número de huecos indicado.
     *
     * @param vacias Número de celdas vacías a dejar
     * @return Tablero con dificultad personalizada
     */
    private int[][] generarConDificultad(int vacias) {
        int[][] tablero = new int[9][9];
        resolver(tablero); // Genera un tablero completo válido
        eliminarCeldas(tablero, vacias);
        return tablero;
    }

    /**
     * Algoritmo recursivo que llena el tablero de forma válida
     * usando backtracking.
     *
     * @param tablero Tablero a rellenar
     * @return true si se ha podido generar una solución válida
     */
    public boolean resolver(int[][] tablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0) {
                    for (int num : numerosAleatorios()) {
                        if (esValido(tablero, fila, col, num)) {
                            tablero[fila][col] = num;
                            if (resolver(tablero)) return true;
                            tablero[fila][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica si un número se puede colocar en una celda sin romper
     * las reglas del Sudoku.
     *
     * @param tablero Tablero actual
     * @param fila    Fila del número
     * @param col     Columna del número
     * @param valor   Número a colocar (1-9)
     * @return true si el número es válido en esa celda
     */
    private boolean esValido(int[][] tablero, int fila, int col, int valor) {
        // Fila y columna
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][col] == valor) return false;
        }

        // Subcuadro 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioCol = (col / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Elimina aleatoriamente un número de celdas en el tablero.
     * No garantiza unicidad de solución.
     *
     * @param tablero Tablero a modificar
     * @param vacias  Número total de celdas a vaciar
     */
    private void eliminarCeldas(int[][] tablero, int vacias) {
        int eliminadas = 0;
        while (eliminadas < vacias) {
            int fila = random.nextInt(9);
            int col = random.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                eliminadas++;
            }
        }
    }

    private int[] numerosAleatorios() {
        int[] numeros = {1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = numeros.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }
        return numeros;
    }
}
