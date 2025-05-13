package logicSudoku;

public class Sudoku implements ISudoku {
    //! Atributos
    int [][] tablero = new int[9][9];
    boolean [][] celdasFijas = new boolean[9][9]; // celdas que no se pueden modificar

    public Sudoku(int[][] tablero, boolean[][] celdasFijas) {
        this.tablero = tablero;
        this.celdasFijas = celdasFijas;
    }

    @Override
    public void generarTablero(String dificultad) {

    }

    @Override
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        return false;
    }

    @Override
    public boolean colocarNumero(int fila, int columna, int valor) {
        return false;
    }

    @Override
    public boolean estaResuelto() {
        return false;
    }

    @Override
    public void mostarTablero() {

    }
}
