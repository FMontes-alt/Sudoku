package core;

import generator.GenerarSudoku;

public interface ISudoku {


    void generarTablero(String dificultad);

    boolean esMovimientoValido(int fila, int columna, int valor);

    boolean colocarNumero(int fila, int columna, int valor);

    boolean estaResuelto();

    int[][] getTablero();

    boolean[][] getCeldasFijas();

    GenerarSudoku getGenerador();

    void mostrarTablero();
}
