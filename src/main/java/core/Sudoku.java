package core;

import generator.GenerarSudoku;

/**
 * Clase principal que representa la lógica de un juego de Sudoku.
 * Permite generar tableros, validar movimientos, insertar números y verificar si está resuelto.
 */
public class Sudoku implements ISudoku {

    private int[][] tablero;
    private boolean[][] celdasFijas;
    private GenerarSudoku generador;
    private final int tam = 9;

    public Sudoku() {
        this.tablero = new int[9][9];
        this.celdasFijas = new boolean[9][9];
        this.generador = new GenerarSudoku();
    }

    public Sudoku(int[][] tablero, boolean[][] celdasFijas) {
        this.tablero = tablero;
        this.celdasFijas = celdasFijas;
        this.generador = new GenerarSudoku();
    }

    @Override
    public void generarTablero(String dificultad) {
        if (dificultad == null || dificultad.isBlank()) {
            throw new IllegalArgumentException("La dificultad no puede estar vacía.");
        }

        switch (dificultad.toLowerCase()) {
            case "facil" -> tablero = generador.generarFacil();
            case "normal" -> tablero = generador.generarNormal();
            case "dificil" -> tablero = generador.generarDificil();
            default -> throw new IllegalArgumentException("Dificultad desconocida: " + dificultad);
        }

        marcarCeldasFijas();
    }

    private void marcarCeldasFijas() {
        celdasFijas = new boolean[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                celdasFijas[fila][col] = tablero[fila][col] != 0;
            }
        }
    }

    @Override
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        for (int i = 0; i < tam; i++) {
            if (tablero[fila][i] == valor || tablero[i][columna] == valor) return false;
        }

        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    @Override
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (!celdasFijas[fila][columna] && esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean estaResuelto() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0 || !esMovimientoValido(fila, col, tablero[fila][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void mostrarTablero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int[][] getTablero() {
        return tablero;
    }

    @Override
    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    @Override
    public GenerarSudoku getGenerador() {
        return generador;
    }
}
