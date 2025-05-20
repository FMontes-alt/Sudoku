package logicSudoku;

public class GenerarSudoku implements IGenerarSudoku {
    private int tam;

    public GenerarSudoku(int tam) {
        this.tam = tam;
    }

    @Override
    public int[][] generarFacil() {
        int[][] matriz = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matriz[i][j] = (int) (Math.random() * 9) + 1;
            }
        }
        return matriz;
    }

    @Override
    public int[][] generarNormal() {
        return new int[0][];
    }

    @Override
    public int[][] generarDificil() {
        return new int[0][];
    }


}
