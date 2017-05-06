import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dog on 4/30/17.
 */
public class Utils {
    /**
     * File format:
     * rowsCount columnsCount
     * matrixElement matrixElement .. matrixElement
     *                  :
     * matrixElement matrixElement .. matrixElement
     * @param filePath - file path
     * @return matrix
     */
    public int [][] readMatrix(String filePath) {
        int matrix[][] = null;
        try {
            Scanner s = new Scanner(new File(filePath));
            int rows = s.nextInt();
            int columns = s.nextInt();
            matrix = new int[rows][columns];
            int row = 0;
            int col = 0;
            while (s.hasNextInt()) {
                matrix[row][col] = s.nextInt();
                col++;
                if (col == columns)
                {
                    row++;
                    col = 0;
                }
            }
            s.close();
        } catch (IOException i) {
            System.out.println("Error : " + i);
        }
        return matrix;
    }

    public int [][] shiftLeft(int matrix [][], int count)
    {
        if (matrix[0].length < count)
        {
            System.out.println("Do not removed.");
            return matrix;
        }

        int result [][] = new int[matrix.length][matrix[0].length - count];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - count; j++) {
                result[i][j] = matrix[i][count + j];
            }
        }
        return result;
    }
}