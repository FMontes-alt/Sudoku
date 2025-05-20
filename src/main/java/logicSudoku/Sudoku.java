package logicSudoku;


public class Sudoku implements ISudoku {
    //! Atributos
    private int[][] tablero;
    private boolean[][] celdasFijas = new boolean[9][9];// celdas que no se pueden modificar
    int tam = 9;
    private GenerarSudoku generador = new GenerarSudoku(tam);


    public Sudoku(int[][] tablero, boolean[][] celdasFijas) {
        this.tablero = tablero;
        this.celdasFijas = celdasFijas;
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

            marcarCeldasFijas();
        }
    }


    @Override
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        return false;
    }

    @Override
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (celdasFijas[fila][columna]) {
            System.out.println("❌ Error: no se puede modificar una celda fija.");
            return false;
        }
        if (esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        } else {
            System.out.println("❌ Movimiento inválido. No se cumplen las reglas del Sudoku.");
            return false;
        }
    }

    @Override
    public boolean estaResuelto() {
        return false;
    }

    @Override
    public void mostarTablero() {

    }
}

