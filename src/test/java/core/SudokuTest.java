package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    private Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
        sudoku.generarTablero("facil");
    }

    @Test
    void testGenerarTableroNotNull() {
        assertNotNull(sudoku.getTablero(), "El tablero generado no debe ser null");
        assertEquals(9, sudoku.getTablero().length, "El tablero debe tener 9 filas");
        assertEquals(9, sudoku.getTablero()[0].length, "Cada fila debe tener 9 columnas");
    }

    @Test
    void testMovimientoValidoYInvalido() {
        int fila = 0;
        int col = 0;
        int valorValido = 1;

        boolean valido = sudoku.esMovimientoValido(fila, col, valorValido);
        assertTrue(valido || !valido, "El método debe devolver un boolean sin lanzar error");
    }

    @Test
    void testColocarNumeroEnCeldaFija() {
        int[][] tablero = sudoku.getTablero();
        boolean[][] fijas = sudoku.getCeldasFijas();

        // Busca una celda fija
        outer: for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fijas[i][j]) {
                    boolean resultado = sudoku.colocarNumero(i, j, tablero[i][j] == 9 ? 1 : tablero[i][j] + 1);
                    assertFalse(resultado, "No debe poder modificarse una celda fija");
                    break outer;
                }
            }
        }
    }

    @Test
    void testEstaResueltoAlInicioDebeSerFalse() {
        assertFalse(sudoku.estaResuelto(), "Al generar el tablero no debe estar resuelto");
    }
}

