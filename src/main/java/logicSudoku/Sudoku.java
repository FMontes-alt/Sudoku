package logicSudoku;

public class Sudoku implements ISudoku {
    //! Atributos
    int[][] tablero = new int[9][9];
    boolean[][] celdasFijas = new boolean[9][9]; // celdas que no se pueden modificar

    public Sudoku(int[][] tablero, boolean[][] celdasFijas) {
        this.tablero = tablero;
        this.celdasFijas = celdasFijas;
    }

    @Override
    public void generarTablero(String dificultad) {
// TODO: Llamar a GenerarSudoku ( mas adelante )
        if (dificultad == null) {
            throw new IllegalArgumentException("La dificultad no puede ser null.");
        } else if (dificultad.equalsIgnoreCase("medio")) {
            // TODO: GeneradorSudoku.generarMedio();
        } else if (dificultad.equalsIgnoreCase("facil")) {
            // TODO: GeneradorSudoku.generarFacil();
        } else if (dificultad.equalsIgnoreCase("dificil")) {
            // TODO: GeneradorSudoku.generarDificil();
        }else{
            throw new IllegalArgumentException("Dificultad no valida: " + dificultad);
        }
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
