package core;

/**
 * Clase que representa el tablero de Sudoku y contiene la lógica del juego.
 * Permite validar movimientos, insertar números y comprobar si el tablero está resuelto.
 */
public class Sudoku {

    private int[][] tablero;
    private boolean[][] celdasFijas;

    /**
     * Constructor que recibe un tablero inicial y marca las celdas fijas (no editables).
     *
     * @param tableroInicial matriz 9x9 con los números iniciales del tablero (0 = vacío)
     */
    public Sudoku(int[][] tableroInicial) {
        this.tablero = new int[9][9];
        this.celdasFijas = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = tableroInicial[i][j];
                celdasFijas[i][j] = (tableroInicial[i][j] != 0);
            }
        }
    }

    /**
     * Inserta un número si el movimiento es válido.
     *
     * @param fila    Fila del tablero (0-8)
     * @param columna Columna del tablero (0-8)
     * @param valor   Valor a insertar (1-9)
     * @return true si se insertó correctamente, false si fue inválido
     */
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (celdasFijas[fila][columna]) return false;
        if (!esMovimientoValido(fila, columna, valor)) return false;

        tablero[fila][columna] = valor;
        return true;
    }

    /**
     * Verifica si un valor es válido en la posición dada, según las reglas del Sudoku.
     *
     * @param fila    Fila del tablero
     * @param columna Columna del tablero
     * @param valor   Valor a verificar
     * @return true si cumple todas las reglas, false si no
     */
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (valor < 1 || valor > 9) return false;

        // Comprobar fila
        for (int j = 0; j < 9; j++) {
            if (tablero[fila][j] == valor) return false;
        }

        // Comprobar columna
        for (int i = 0; i < 9; i++) {
            if (tablero[i][columna] == valor) return false;
        }

        // Comprobar subcuadro 3x3
        int filaInicio = (fila / 3) * 3;
        int colInicio = (columna / 3) * 3;
        for (int i = filaInicio; i < filaInicio + 3; i++) {
            for (int j = colInicio; j < colInicio + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Comprueba si el tablero está completamente resuelto.
     *
     * @return true si todas las celdas están llenas y son válidas
     */
    public boolean estaResuelto() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = tablero[i][j];
                if (valor == 0 || !esMovimientoValidoParcial(i, j, valor)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Muestra el tablero por consola.
     * Solo para uso de depuración o interfaz consola.
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

    /**
     * Devuelve el tablero actual (para interfaz o pruebas).
     *
     * @return Matriz de enteros 9x9
     */
    public int[][] getTablero() {
        return tablero;
    }

    /**
     * Valida parcialmente el valor ya colocado, ignorando su posición actual.
     * Esto se usa en el método `estaResuelto` para evitar autocolisión.
     */
    private boolean esMovimientoValidoParcial(int fila, int columna, int valor) {
        // Validar fila
        for (int j = 0; j < 9; j++) {
            if (j != columna && tablero[fila][j] == valor) return false;
        }

        // Validar columna
        for (int i = 0; i < 9; i++) {
            if (i != fila && tablero[i][columna] == valor) return false;
        }

        // Validar subcuadro 3x3
        int filaInicio = (fila / 3) * 3;
        int colInicio = (columna / 3) * 3;
        for (int i = filaInicio; i < filaInicio + 3; i++) {
            for (int j = colInicio; j < colInicio + 3; j++) {
                if ((i != fila || j != columna) && tablero[i][j] == valor) return false;
            }
        }

        return true;
    }
}
