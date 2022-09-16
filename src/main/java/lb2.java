import java.util.Scanner;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;

public class lb2 {  /*Сазанська 124-19-2*/

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rowsAmount, colsAmount;
        int randomGenerationFrom = 1, randomGenerationTo = 20;

        System.out.println("Введіть розмір матриці [rows x columns], максимальний розмір якої [20 x 20].");
        rowsAmount = matrixSizeCheck("Вкажіть кількість рядків: ");
        colsAmount = matrixSizeCheck("Вкажіть кількість стовпців: ");

        int[][] matrix = new int [rowsAmount][colsAmount];
        for (int[] row: matrix)
            Arrays.fill(row, 0);

        System.out.print("Створена матриця ["+ rowsAmount +" x "+ colsAmount +"]. " +
                "Заповнити самостійно - (1), згенерувати випадково - (2): ");
        int g = input.nextInt();
        if (g==1) {
            for (int i = 0; i < rowsAmount; i++) {
                for (int j = 0; j < colsAmount; j++) {
                        System.out.print("Введіть елемент[" + i + "][" + j + "]: ");
                        matrix[i][j] = input.nextInt();
                    }
                }
                outputMatrix(matrix);
            }
            else {
                matrix = createRandomMatrix(rowsAmount,colsAmount,randomGenerationFrom,randomGenerationTo);
                outputMatrix(matrix);
            }

        System.out.println("Максимальне значення у матриці: "+ findMaximum(matrix));
        System.out.println("Мінімальне значення у матриці: "+ findMinimum(matrix));
        System.out.println("Арифметичне середнє матриці: "+ findArithmeticAvg(matrix));
        System.out.println("Геометричне середнє матриці: "+ findGeometricAvg(matrix));
    }

    public static int[][] createRandomMatrix(int rows, int cols, int f, int t) {
        return IntStream.range(0, rows)
                .mapToObj(i -> new Random()
                        .ints(cols, f, t)
                        .toArray())
                .toArray(int[][]::new);
    }

    public static void outputMatrix(int[][] matrix) {
        for (int[] line : matrix) {
            for (int elem : line)
                System.out.print(elem + "\t");
            System.out.println();
        }
    }

    public static int findMaximum(int[][] matrix) {
        int searchingParameter = matrix[0][0];
        for (int[] line : matrix)
            for (int elem : line)
                if (elem > searchingParameter) searchingParameter = elem;
        return searchingParameter;
    }

    public static int findMinimum(int[][] matrix) {
        int searchingParameter = matrix[0][0];
        for (int[] line : matrix)
            for (int elem : line)
                if (elem < searchingParameter) searchingParameter = elem;
        return searchingParameter;
    }

    public static double findArithmeticAvg(int[][] matrix) {
        double matrixElemSum = 0;
        for (int[] line : matrix)
            for (int elem : line)
                matrixElemSum += elem;
        return matrixElemSum/(matrix.length*matrix[0].length);
    }

    public static double findGeometricAvg(int[][] matrix) {
        double matrixElemMultiplication = 1;
        for (int[] line : matrix)
            for (int elem : line)
                matrixElemMultiplication *= elem;
        return Math.pow(matrixElemMultiplication,1.0/(matrix.length*matrix[0].length));
    }

    public static int matrixSizeCheck(String messageToUser) {
        Scanner input = new Scanner(System.in);
        byte size;
        while(true) {
            System.out.print(messageToUser);
            size = input.nextByte();
            if (size > 20) {
                System.out.print("Значення перевищіло 20. Спробуєте ще раз? y/n ");
                char repeatTrial = input.next().charAt(0);
                if (repeatTrial != 'y')
                    System.exit(0);
            }
            else break;
        }
        return size;
    }
}
