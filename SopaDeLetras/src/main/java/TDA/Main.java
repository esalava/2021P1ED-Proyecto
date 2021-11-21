package TDA;

import Matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[2][2];
        System.out.println(matrix[0].length);
        System.out.println(matrix.length);
        System.out.println("***************************");
        for(int y = 0 ; y<matrix.length; y++){
            for(int x = 0; x < matrix[y].length ; x++){
                matrix[y][x] = 0;
            }
        }
        
        for(int y = 0 ; y<matrix.length; y++){
            for(int x = 0; x < matrix[y].length ; x++){
                System.out.print(matrix[y][x]);
            }
            System.out.println("");
        }
        
        System.out.println("***************************");
        
        int posNuevaColumna = 1;
        int[] nuevaColumna = {1,2};
        
        int[][] newMatrix = new int[2][3];
        
        for (int filas = 0; filas < newMatrix.length; filas++) {
            int valor = 0;
            for (int columnas = 0; columnas < newMatrix[0].length; columnas++) {
                if (columnas == posNuevaColumna) {
                    newMatrix[filas][columnas] = nuevaColumna[filas];
                    valor = 1;
                } else {
                    newMatrix[filas][columnas] = matrix[filas][columnas - valor];
                }

            }
        }
        newMatrix[0][0] = 2;
        newMatrix[1][0] = 3;
        newMatrix[1][2] = 5;
        
        for(int y = 0 ; y<newMatrix.length; y++){
            for(int x = 0; x < newMatrix[y].length ; x++){
                System.out.print(newMatrix[y][x]);
            }
            System.out.println("");
        }
        
        System.out.println(newMatrix.length);
        for(int y = 0 ; y<newMatrix.length; y++){
            System.out.println(newMatrix[y][0]);
        }
        
        
    }
}
