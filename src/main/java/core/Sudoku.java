package core;

import generator.GenerarSudoku;
import interfaces.ISudoku;

/**
 * Clase principal que representa la lógica de un juego de Sudoku.
 * Permite generar tableros, validar movimientos, insertar números y verificar si está resuelto.
 */
public class Sudoku implements ISudoku {
    private int[][] tablero;
    private boolean[][] celdasFijas;
    private GenerarSudoku generador;
    private final int tam = 9;

    /**
     * Constructor sin parámetros, utilizado normalmente desde la interfaz gráfica.
     * Inicializa un tablero vacío y el generador de Sudoku.
     */
    public Sudoku() {
        this.tablero = new int[9][9];
        this.celdasFijas = new boolean[9][9];
        this.generador = new GenerarSudoku();
    }

    /**
     * Constructor con parámetros, usado para crear una instancia con un tablero ya existente.
     * @param tablero matriz de enteros con los valores del tablero.
     * @param celdasFijas matriz booleana que indica qué celdas son fijas (no editables).
     */
    public Sudoku(int[][] tablero, boolean[][] celdasFijas) {
        this.tablero = tablero;
        this.celdasFijas = celdasFijas;
        this.generador = new GenerarSudoku();
    }

    /**
     * Genera un nuevo tablero según la dificultad indicada.
     * @param dificultad puede ser \"facil\", \"normal\" o \"dificil\".
     */
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

    /**
     * Comprueba si se puede colocar un número en una posición dada sin violar las reglas del Sudoku.
     * @param fila la fila del tablero (0-8)
     * @param columna la columna del tablero (0-8)
     * @param valor el número que se quiere colocar (1-9)
     * @return true si el movimiento es válido; false en caso contrario.
     */
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

    /**
     * Intenta colocar un número en una celda, si la celda no es fija y el movimiento es válido.
     * @param fila la fila del tablero
     * @param columna la columna del tablero
     * @param valor el número a colocar
     * @return true si el número se colocó correctamente; false si fue inválido o celda fija.
     */
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
            System.out.println("❌ Movimiento inválido.");
            return false;
        }
    }

    /**
     * Comprueba si el tablero está completamente lleno y todos los valores son válidos.
     * @return true si el Sudoku está correctamente resuelto; false en caso contrario.
     */
    @Override
    public boolean estaResuelto() {
        for (int fila = 0; fila < tam; fila++) {
            for (int columna = 0; columna < tam; columna++) {
                int valor = tablero[fila][columna];

                if (valor == 0) return false;

                tablero[fila][columna] = 0;
                if (!esMovimientoValido(fila, columna, valor)) {
                    tablero[fila][columna] = valor;
                    return false;
                }
                tablero[fila][columna] = valor;
            }
        }
        return true;
    }

    /**
     * Muestra el tablero actual en consola con formato visual.
     */
    @Override
    public void mostrarTablero() {
        System.out.println();
        for (int fila = 0; fila < tam; fila++) {
            if (fila % 3 == 0 && fila != 0) {
                System.out.println("---------------------");
            }

            for (int columna = 0; columna < tam; columna++) {
                if (columna % 3 == 0 && columna != 0) {
                    System.out.print("| ");
                }

                int valor = tablero[fila][columna];
                System.out.print((valor == 0 ? "_" : valor) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Marca qué celdas no se podrán modificar por el usuario (fijas).
     */
    private void marcarCeldasFijas() {
        celdasFijas = new boolean[tam][tam];
        for (int fila = 0; fila < tam; fila++) {
            for (int columna = 0; columna < tam; columna++) {
                celdasFijas[fila][columna] = tablero[fila][columna] != 0;
            }
        }
    }

    /**
     * Devuelve el tablero actual.
     * @return matriz de enteros con los valores del tablero.
     */
    public int[][] getTablero() {
        return tablero;
    }

    /**
     * Devuelve el estado de las celdas fijas del tablero.
     * @return matriz booleana con las celdas que no se pueden modificar.
     */
    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    /**
     * Devuelve el generador de Sudoku asociado.
     * @return instancia de GenerarSudoku.
     */
    public GenerarSudoku getGenerador() {
        return generador;
    }
}
