package generator;

import java.util.Random;

/**
 * Clase que permite generar tableros de Sudoku válidos
 * con diferentes niveles de dificultad.
 * Guarda internamente una copia del tablero resuelto
 * para usarlo como verificación.
 */
public class GenerarSudoku {

    private int[][] tableroResuelto;
    private static final Random random = new Random();

    public GenerarSudoku() {
      // En el caso de necesitarlo
    }

    /**
     * Genera un tablero con nivel fácil.
     * Deja hasta 30 celdas vacías.
     * @return tablero con huecos
     */
    public int[][] generarFacil() {
        return generarConDificultad(30);
    }

    /**
     * Genera un tablero con nivel medio.
     * Deja hasta 40 celdas vacías.
     * @return tablero con huecos
     */
    public int[][] generarNormal() {
        return generarConDificultad(40);
    }

    /**
     * Genera un tablero con nivel difícil.
     * Deja hasta 50 celdas vacías.
     * @return tablero con huecos
     */
    public int[][] generarDificil() {
        return generarConDificultad(50);
    }

    /**
     * Genera un tablero completo y luego elimina celdas.
     * Guarda la solución antes de vaciar.
     * @param vacias número de huecos a dejar
     * @return tablero con dificultad deseada
     */
    public int[][] generarConDificultad(int vacias) {
        int[][] tablero = new int[9][9];
        resolver(tablero); // resuelve completamente
        tableroResuelto = copiarTablero(tablero); // guarda solución
        eliminarCeldas(tablero, vacias); // quita celdas
        return tablero;
    }

    /**
     * Devuelve la solución guardada del tablero completo.
     * @return tablero resuelto
     */
    public int[][] getTableroResuelto() {
        return tableroResuelto;
    }

    /**
     * Algoritmo backtracking para resolver el tablero.
     * @param tablero tablero a resolver
     * @return true si se resolvió correctamente
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
     * Comprueba si se puede poner el número en una posición.
     */
    private boolean esValido(int[][] tablero, int fila, int col, int valor) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][col] == valor) return false;
        }

        int boxRow = (fila / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Genera los números del 1 al 9 en orden aleatorio.
     */
    private int[] numerosAleatorios() {
        int[] numeros = new int[9];
        for (int i = 0; i < 9; i++) {
            numeros[i] = i + 1;
        }

        for (int i = numeros.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }

        return numeros;
    }

    /**
     * Elimina celdas aleatorias del tablero para crear huecos.
     */
    private void eliminarCeldas(int[][] tablero, int cantidad) {
        int eliminadas = 0;
        while (eliminadas < cantidad) {
            int fila = random.nextInt(9);
            int col = random.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                eliminadas++;
            }
        }
    }

    /**
     * Crea una copia del tablero.
     */
    private int[][] copiarTablero(int[][] original) {
        int[][] copia = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, 9);
        }
        return copia;
    }
}
