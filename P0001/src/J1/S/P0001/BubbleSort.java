package J1.S.P0001;

import java.util.Scanner;
import java.util.Random;

public class BubbleSort {

    public static void main(String[] args) {
        //input size of array
        int inputSize = getSize();
        int[] array = new int[inputSize];

        //Generate random values ​​of array
        randomArray(array);
        //int[]array = {5, 1, 12, -5, 16};

        //Display unsorted array
        displayArray("Unsorted array: ", array);

        //sort array by bubblesort
        bubbleSort(array);

        //Display sorted array
        displayArray("Sorted array: ", array);

    }

    public static int getSize() {

        Scanner sc = new Scanner(System.in);

        //loop until input correct
        while (true) {
            String input = "";
            try {
                System.out.println("Enter number of array: ");
                //validate input, input must be an int number
                input = sc.nextLine().trim();
                int size = Integer.parseInt(input);
                // check for positive integers
                if (size <= 0) {
                    System.out.println(input + " is not a positive number again!");
                } else {
                    return size;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, try again!");
            }
        }
    }

    // random number into array
    private static void randomArray(int[] array) {
        Random rd = new Random();
        //loop array and add random value to array
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(array.length);
        }

    }
    // print out the value of array

    private static void displayArray(String message, int[] array) {
        System.out.print(message);
        System.out.print("[");
        // loop to print out the elements in the array
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
            //not add "," at the end of array
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }
    // print out the value of array

    private static void displayArray(int[] array) {
        System.out.print("[");
        // loop to print out the elements in the array
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
            //not add "," at the end of array
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }

    // bubble sort
    private static void bubbleSort(int[] array) {
        // System.out.println();
        // loop to access each array element
        for (int i = 0; i < array.length; i++) {
            //  System.out.println();
            boolean check = true;
            // access each element in the unsorted array
            for (int j = 0; j < array.length - i - 1; j++) {
                // check if the number before is greater than the number after
                if (array[j] > array[j + 1]) {
                    // displayArray(array);
                    //  System.out.println("   " + array[j] + ">" + array[j + 1] + ",swap");
                    check = false;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
//                } else {
//                    displayArray(array);
//                    System.out.println("   " + array[j] + "<" + array[j + 1] + ",ok");
                }
                // System.out.println();
            }
            // if there is no swap --> break
            if (check) {
                break;
            }

        }
        System.out.println();

    }

}
