package core;

import generator.GenerarSudoku;

/**
 * Clase que representa el tablero de Sudoku.
 * Contiene la lógica del juego: validación, inserción y comprobación de solución.
 */
public class Sudoku {

    private int[][] tablero;
    private boolean[][] celdasFijas;
    private GenerarSudoku generator;

    /**
     * Constructor por defecto. Genera un tablero vacío.
     */
    public Sudoku() {
        this.tablero = new int[9][9];
        this.celdasFijas = new boolean[9][9];
        this.generator = new GenerarSudoku(9);
    }

    /**
     * Genera un nuevo tablero con una dificultad específica.
     * Usa la clase GenerarSudoku.
     *
     * @param dificultad Puede ser "facil", "medio" o "dificil"
     */
    public void generarTablero(String dificultad) {
        GenerarSudoku generador = new GenerarSudoku();
        switch (dificultad.toLowerCase()) {
            case "facil":
                this.tablero = generador.generarFacil();
                break;
            case "medio":
                this.tablero = generador.generarMedio();
                break;
            case "dificil":
                this.tablero = generador.generarDificil();
                break;
            default:
                throw new IllegalArgumentException("Dificultad no válida.");
        }

        // Establecer celdas fijas según valores no cero
        this.celdasFijas = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                celdasFijas[i][j] = tablero[i][j] != 0;
            }
        }
    }

    /**
     * Intenta colocar un número en el tablero.
     *
     * @param fila    Índice de fila (0-8)
     * @param columna Índice de columna (0-8)
     * @param valor   Valor a insertar (1-9)
     * @return true si el número se colocó correctamente
     */
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (celdasFijas[fila][columna]) return false;
        if (!esMovimientoValido(fila, columna, valor)) return false;

        tablero[fila][columna] = valor;
        return true;
    }

    /**
     * Verifica si colocar un número es válido según las reglas del Sudoku.
     */
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (valor < 1 || valor > 9) return false;

        // Verificar fila y columna
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][columna] == valor) return false;
        }

        // Verificar bloque 3x3
        int bloqueFila = (fila / 3) * 3;
        int bloqueCol = (columna / 3) * 3;
        for (int i = bloqueFila; i < bloqueFila + 3; i++) {
            for (int j = bloqueCol; j < bloqueCol + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Verifica si el tablero está completamente resuelto.
     */
    public boolean estaResuelto() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                int valor = tablero[fila][col];
                if (valor == 0 || !esMovimientoValidoParcial(fila, col, valor)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Método auxiliar para comprobar si un valor ya colocado es válido sin contarse a sí mismo.
     */
    private boolean esMovimientoValidoParcial(int fila, int columna, int valor) {
        for (int i = 0; i < 9; i++) {
            if (i != columna && tablero[fila][i] == valor) return false;
            if (i != fila && tablero[i][columna] == valor) return false;
        }

        int bloqueFila = (fila / 3) * 3;
        int bloqueCol = (columna / 3) * 3;
        for (int i = bloqueFila; i < bloqueFila + 3; i++) {
            for (int j = bloqueCol; j < bloqueCol + 3; j++) {
                if ((i != fila || j != columna) && tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Imprime el tablero por consola (modo texto).
     */
    public void mostrarTablero() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("---------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(tablero[i][j] == 0 ? "_ " : tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    public GenerarSudoku getGenerador() {
        return generador;
    }
}
