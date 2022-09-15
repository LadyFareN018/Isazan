import java.util.Scanner;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;

public class lb2 { /*Сазанська 124-19-2*/

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int r, c;
        int from = 1, to = 20;

        System.out.println("Введіть розмір матриці [rows x columns], максимальний розмір якої [20 x 20].");
        r = matrixSize("рядків");
        c = matrixSize("стовпців");

        int[][] arr = new int [r][c];
        for (int[] row: arr)
            Arrays.fill(row, 0);

        System.out.print("Створена матриця ["+r+" x "+c+"]. Заповнити самостійно - (1), згенерувати випадково - (2): ");
        int g = input.nextInt();
        if (g==1) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                        System.out.print("Введіть елемент[" + i + "][" + j + "]: ");
                        arr[i][j] = input.nextInt();
                    }
                }
                outputMatrix(arr);
            }
            else {
                arr = createRandomMatrix(r,c,from,to);
                outputMatrix(arr);
            }


        System.out.println("Максимальне значення у матриці: "+ max(arr));
        System.out.println("Мінімальне значення у матриці: "+ min(arr));
        System.out.println("Арифметичне середнє матриці: "+ arithmeticavg(arr));
        System.out.println("Геометричне середнє матриці: "+ geometricavg(arr));
    }

    /*Заповнення матриці випадковими цілими числами в діпазоні [from; to]*/
    public static int[][] createRandomMatrix(int rows, int cols, int f, int t) {
        return IntStream.range(0, rows)
                .mapToObj(i -> new Random()
                        .ints(cols, f, t)
                        .toArray())
                .toArray(int[][]::new);
    }
    /*Вивід матриці*/
    public static void outputMatrix(int[][] matrix) {
        for (int[] line : matrix) {
            for (int elem : line)
                System.out.print(elem + "\t");
            System.out.println();
        }
    }
    /*Пошук максимального значення*/
    public static int max(int[][] matrix) {
        int m = matrix[0][0];
        for (int[] line : matrix)
            for (int elem : line)
                if (elem > m) m = elem;
        return m;
    }
    /*Пошук мінімального значення*/
    public static int min(int[][] matrix) {
        int m = matrix[0][0];
        for (int[] line : matrix)
            for (int elem : line)
                if (elem < m) m = elem;
        return m;
    }
    /*Пошук серед. арифметичного значення*/
    public static double arithmeticavg(int[][] matrix) {
        double m = 0;
        for (int[] line : matrix)
            for (int elem : line)
                m += elem;
        return m/(matrix.length*matrix[0].length);
    }
    /*Пошук серед. геометричного значення*/
    public static double geometricavg(int[][] matrix) {
        double m = 1;
        for (int[] line : matrix)
            for (int elem : line)
                m *= elem;
        return Math.pow(m,1.0/(matrix.length*matrix[0].length));
    }
    /*Превірка на введення розміру матриці*/
    public static int matrixSize(String rcword) {
        Scanner input = new Scanner(System.in);
        int param;
        while(true) {
            System.out.print("Вкажіть кількість "+ rcword +": ");
            param = input.nextInt();
            if (param > 20) {
                System.out.print("Значення перевищіло 20. Спробуєте ще раз? y/n ");
                char yn = input.next().charAt(0);
                if (yn != 'y')
                    System.exit(0);
            }
            else break;
        }
        return param;
    }
}
