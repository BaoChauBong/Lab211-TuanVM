
public class Management {

    GetInputData inputData = new GetInputData();

    /**
     * Method used to display the menu
     */
    public void displayMenu() {
        System.out.println("=======Calculator program======");
        System.out.println("1. Addition Matrix");
        System.out.println("2. Subtraction Matrix");
        System.out.println("3. Multiplication Matrix");
        System.out.println("4. Quit");

    }

    /**
     * Method used to perform the addition of two matrices
     */
    public void additionMatrix() {
        System.out.println("---------Addition------");
        Matrix matrix1 = inputData.getInputMatrix1();
        Matrix matrix2 = inputData.getInputMatrix2(1,matrix1);
        int[][] resultMatrix = new int[matrix1.getRows()][matrix1.getColumns()];
        // access to each element of rows
        for (int i = 0; i < matrix1.getRows(); i++) {
            // access to each element of colums
            for (int j = 0; j < matrix1.getColumns(); j++) {
                resultMatrix[i][j] = matrix1.getMatrix()[i][j] + matrix2.getMatrix()[i][j];
            }
        }
        System.out.println("----------Result----------");
        displayMatrix(matrix1.getMatrix(), matrix1.getRows(), matrix1.getColumns());
        System.out.println("+");
        displayMatrix(matrix2.getMatrix(), matrix2.getRows(), matrix2.getColumns());
        System.out.println("=");
        displayMatrix(resultMatrix, matrix1.getRows(), matrix1.getColumns());
    }

    /**
     * Method used to perform the subtraction of two matrices
     */
    public void subtractionMatrix() {
        System.out.println("---------Subtraction------");
        Matrix matrix1 = inputData.getInputMatrix1();
        Matrix matrix2 = inputData.getInputMatrix2(1,matrix1);
        int[][] resultMatix = new int[matrix1.getRows()][matrix1.getColumns()];
        // access to each element of rows
        for (int i = 0; i < matrix1.getRows(); i++) {
            // access to each element of colums
            for (int j = 0; j < matrix1.getColumns(); j++) {
                resultMatix[i][j] = matrix1.getMatrix()[i][j] - matrix2.getMatrix()[i][j];
            }
        }
        System.out.println("----------Result----------");
        displayMatrix(matrix1.getMatrix(), matrix1.getRows(), matrix1.getColumns());
        System.out.println("-");
        displayMatrix(matrix2.getMatrix(), matrix2.getRows(), matrix2.getColumns());
        System.out.println("=");
        displayMatrix(resultMatix, matrix1.getRows(), matrix1.getColumns());
    }

    /**
     * Method used to perform the multiplication of two matrices
     */
    public void multiplicationMatrix() {
        System.out.println("---------Multiplication------");
        Matrix matrix1 = inputData.getInputMatrix1();
        Matrix matrix2 = inputData.getInputMatrix2(2,matrix1);
        int[][] resultMatix = new int[matrix1.getRows()][matrix2.getColumns()];
        //  access to each element of rows matrix1
        for (int i = 0; i < matrix1.getRows(); i++) {
            //  access to each element of colums matrix2
            for (int j = 0; j < matrix2.getColumns(); j++) {
                // Loop element of column matrix1
                for (int k = 0; k < matrix1.getColumns(); k++) {
                    resultMatix[i][j] += matrix1.getMatrix()[i][k] * matrix2.getMatrix()[k][j];
                }
            }
        }
        System.out.println("----------Result----------");
        displayMatrix(matrix1.getMatrix(), matrix1.getRows(), matrix1.getColumns());
        System.out.println("*");
        displayMatrix(matrix2.getMatrix(), matrix2.getRows(), matrix2.getColumns());
        System.out.println("=");
        displayMatrix(resultMatix, matrix1.getRows(), matrix2.getColumns());
    }

    /**
     * Method use to display matrix
     *
     * @param matrix
     * @param row
     * @param column
     */
    static void displayMatrix(int matrix[][], int row, int column) {
        // access to each element of rows
        for (int i = 0; i < row; i++) {
            // access to each element of column
            for (int j = 0; j < column; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
    }
}
