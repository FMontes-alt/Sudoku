package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitarios para la clase JuegoSudoku
 */
public class JuegoSudokuTest {

    private JuegoSudoku juego;

    @BeforeEach
    void setUp() {
        juego = new JuegoSudoku();
    }

    @Test
    void testInstanciaSudokuNoNula() {
        assertNotNull(juego.getSudoku(), "La instancia de Sudoku no debe ser null");
    }


    @Test
    void testGenerarTableroCorrecto() {
        juego.getSudoku().generarTablero("facil");
        int[][] tablero = juego.getSudoku().getTablero();
        assertEquals(9, tablero.length);
        assertEquals(9, tablero[0].length);
    }
}