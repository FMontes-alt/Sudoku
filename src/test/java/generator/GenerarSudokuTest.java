package generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase GenerarSudoku
 */
class GenerarSudokuTest {
    private GenerarSudoku generador;

    @BeforeEach
    void setUp() {
        generador = new GenerarSudoku();
    }

    @Test
    void testGenerarFacilNoNulo() {
        int[][] tablero = generador.generarFacil();
        assertNotNull(tablero, "El tablero no debe ser null");
        assertEquals(9, tablero.length);
        assertEquals(9, tablero[0].length);
    }

    @Test
    void testGenerarNormalNoNulo() {
        int[][] tablero = generador.generarNormal();
        assertNotNull(tablero);
        assertEquals(9, tablero.length);
        assertEquals(9, tablero[0].length);
    }

    @Test
    void testGenerarDificilNoNulo() {
        int[][] tablero = generador.generarDificil();
        assertNotNull(tablero);
        assertEquals(9, tablero.length);
        assertEquals(9, tablero[0].length);
    }

    @Test
    void testResolverGeneraTableroCompleto() {
        int[][] tablero = new int[9][9];
        boolean resuelto = generador.resolver(tablero);
        assertTrue(resuelto);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(tablero[i][j] >= 1 && tablero[i][j] <= 9,
                        "Cada celda debe tener un valor entre 1 y 9");
            }
        }
    }
}