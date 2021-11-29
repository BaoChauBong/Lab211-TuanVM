
import java.util.Scanner;

public class GetInputData {

    static Scanner sc = new Scanner(System.in);
    static Management management = new Management();
    private int row;
    private int column;

    /**
     * This method is used to get choice from user
     *
     * @return
     */
    public int getInputChoice() {
        while (true) {
            try {
                System.out.print("Your choice: ");
                String input = sc.nextLine().trim();
                //check input is empty
                if (input.isEmpty()) {
                    System.out.println("Empty! Please input again ");
                    management.displayMenu();
                    continue;
                }
                int choice = Integer.parseInt(input);
                // check choice is a positive integer
                if (choice > 0) {
                    // check selection no greater than 4 
                    if (choice < 5) {
                        return choice;
                    } else {
                        System.out.println("choice with 1-4.Please input again ");
                        management.displayMenu();
                    }
                } else {
                    System.out.println("input must postive integer");
                    management.displayMenu();
                }

            } catch (NumberFormatException e) {
                System.out.println("Invaild choice,please input again");
                management.displayMenu();
            }
        }
    }

    /**
     * Method use get input matrix1
     *
     * @return about the matrix desired by the user
     */
    public Matrix getInputMatrix1() {
        System.out.printf("Enter Row Matrix 1:");
        row = getRowColumn("Enter Row Matrix 1:");
        System.out.printf("Enter column Matrix 1:");
        column = getRowColumn("Enter Row Matrix 1:");
        return inputMatrix(row, column, 1);

    }

    /**
     * Method use get input matrix2
     *
     * @param number
     * @param matrix1
     * @return about the matrix desired by the user
     */
    public Matrix getInputMatrix2(int number, Matrix matrix1) {

        while (true) {
            System.out.print("Enter Row Matrix 2:");
            row = getRowColumn("Enter Row Matrix 2:");
            // check row condition of two matrices together 
            if (number == 1 && row != matrix1.getRows()) {
                System.out.println("Two matrices must have the same number of rows");
            } // check the row condition of two matrices 2 with columns of matrix 1       
            else if (number == 2 && row != matrix1.getColumns()) {
                System.out.println("Columns in matrix 1 and rows in matrix 2 must be equal");
            } else {
                break;
            }
        }

        while (true) {
            System.out.printf("Enter Column Matrix 2:");
            column = getRowColumn("Enter Column Matrix 2:");
            // check column condition of two matrices together 
            if (number == 1 && column != matrix1.getColumns()) {
                System.out.println("Two matrices must have the same number of Columns");
            } else {
                break;
            }
        }
        return inputMatrix(row, column, 2);
    }

    /**
     * Method use get Row and Column of matrix
     *
     * @param mess
     * @return
     */
    public int getRowColumn(String mess) {
        while (true) {

            try {
                String input = sc.nextLine().trim();
                //check if input is empty
                if (input.isEmpty()) {
                    System.out.println("Empty!!Please enter a positive integer ");
                    System.out.print(mess);
                    continue;
                }
                int number = Integer.parseInt(input);
                // check number is less than or equal to 0
                if (number <= 0) {
                    System.out.println("Please enter a positive integer ");
                    System.out.print(mess);
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer ");
                System.out.print(mess);
            }
        }
    }

    public Matrix inputMatrix(int row, int column, int number) {
        int[][] matrix = new int[row][column];
        // access to each element of rows
        for (int i = 0; i < row; i++) {
            // Loop to access j from 0 to column - 1
            for (int j = 0; j < column; j++) {
                System.out.print("Enter Matrix" + number + "[" + (i + 1) + "]" + "[" + (j + 1) + "]:");
                matrix[i][j] = inputInt(i, j, number);
            }
        }
        return new Matrix(row, column, matrix);
    }

    public int inputInt(int i, int j, int matrix) {
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Value of matrix is digit");
                System.out.print("Enter Matrix" + matrix + "[" + (i + 1) + "]" + "[" + (j + 1) + "]:");
            }
        }
    }

}
