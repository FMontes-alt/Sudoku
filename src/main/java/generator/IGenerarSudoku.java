package generator;

public interface IGenerarSudoku {

    int[][] generarFacil(int num);

    int[][] generarNormal(int num);

    int[][] generarDificil(int num);

    int[][] generarConDificultad(int vacias);
}