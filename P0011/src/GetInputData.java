
import java.util.Scanner;

public class GetInputData {

    Scanner sc = new Scanner(System.in);

    int getOption(int min, int max, String mess) {
        while (true) {
            System.out.print(mess);
            String input = sc.nextLine().trim();
            // check input empty
            if (input.isEmpty()) {
                System.out.println("Empty!!! Please Enter agian.");
                continue;
            }
            try {
                int choice = Integer.parseInt(input);
                // check choice is greater than 0
                if (choice > 0) {
                    // check choice is within the range of the menu
                    if (choice >= min && choice <= max) {
                        return choice;
                    } else {
                        System.out.println("Please input number in range " + min + "-" + max + " . Please enter again. ");
                    }
                } else {
                    System.out.println("Choice must be a positive integer, Please enter again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Choice must be an integer, Please enter again. ");
            }
        }
    }

    int inputIntLimit(int min, int max) {
        // Loop until user input correct
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                // Check that the selection is within the range of the menu
                if (number < min || number > max) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input! Enter input number in rage [" + min + ", " + max + "]");
                System.out.print("Please enter input again: ");
            }
        }
    }

    public String inputString() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = scanner.nextLine().trim();
            // check string empty 
            if (!string.isEmpty()) {
                return string.toUpperCase();
            } else {
                System.out.println("Number can not empty. ");
                System.out.print("Enter number you want to convert: ");
            }
        }
    }

    public boolean inputYesNo() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press \"Y\" if you want to keep taking action,"
                + " press \"N\" if you want to end the action.");
        System.out.print("Enter your choice: ");
        while (true) {
            String s = in.nextLine();
            if (!s.isEmpty()) {
                s = s.replaceAll(" ", "");
                if (s.compareToIgnoreCase("Y") == 0 || s.compareToIgnoreCase("y") == 0) {
                    return true;
                }
                if (s.compareToIgnoreCase("N") == 0 || s.compareToIgnoreCase("n") == 0) {
                    return false;
                }
                System.out.print("You must choose Yes(Y) or No(N)!\nEnter again: ");
            } else {
                System.out.print("Choice can not empty, enter again: ");
            }
        }
    }

}
