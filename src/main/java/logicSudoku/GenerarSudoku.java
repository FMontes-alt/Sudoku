package logicSudoku;

import java.util.Random;

public class GenerarSudoku implements IGenerarSudoku {
    private final int tam;

    public GenerarSudoku(int tam) {
        this.tam = tam;
    }

    @Override
    public int[][] generarFacil() {
        int[][] tablero = generarTableroCompleto();
        eliminarCeldas(tablero, 30); // Fácil: hasta 30 vacías
        return tablero;
    }

    @Override
    public int[][] generarNormal() {
        int[][] tablero = generarTableroCompleto();
        eliminarCeldas(tablero, 40); // Normal: hasta 40 vacías
        return tablero;
    }

    @Override
    public int[][] generarDificil() {
        int[][] tablero = generarTableroCompleto();
        eliminarCeldas(tablero, 50); // Difícil: hasta 50 vacías
        return tablero;
    }

    public boolean resolver(int[][] tablero) {
        for (int fila = 0; fila < tam; fila++) {
            for (int columna = 0; columna < tam; columna++) {
                if (tablero[fila][columna] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (esValido(tablero, fila, columna, num)) {
                            tablero[fila][columna] = num;
                            if (resolver(tablero)){
                                return true;
                            }
                            tablero[fila][columna] = 0; // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean esValido(int[][] tablero, int fila, int columna, int num) {
        for (int i = 0; i < tam; i++) {
            if (tablero[fila][i] == num || tablero[i][columna] == num) return false;
        }

        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if (tablero[i][j] == num) return false;
            }
        }

        return true;
    }

    private int[][] generarTableroCompleto() {
        int[][] tablero = new int[tam][tam];
        resolver(tablero);
        return tablero;
    }

    private void eliminarCeldas(int[][] tablero, int cantidad) {
        Random rand = new Random();
        int eliminadas = 0;

        while (eliminadas < cantidad) {
            int fila = rand.nextInt(tam);
            int columna = rand.nextInt(tam);

            if (tablero[fila][columna] != 0) {
                tablero[fila][columna] = 0;
                eliminadas++;
            }
        }
    }
}
