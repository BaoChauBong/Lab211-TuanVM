
import entity.Fruit;
import java.util.ArrayList;
import java.util.Scanner;

public class GetInputData {

    static Scanner sc = new Scanner(System.in);
    static Validation validation = new Validation();

    /**
     * Method use get integer input
     *
     * @param min
     * @param max
     * @param mess
     * @return
     */
    int getIntInput(int min, int max, String mess) {
        while (true) {
            try {
                System.out.print(mess);
                String input = sc.nextLine().trim();
                //check input is empty
                if (input.isEmpty()) {
                    System.out.println("Empty! Please input again ");
                    continue;
                }
                int choice = Integer.parseInt(input);
                // check choice is a positive integer
                if (choice > 0) {
                    // kiểm tra lựa chọn có vượt quá với giới hạn có được
                    if (choice < min || choice > max) {
                        System.out.println("Please input number in range " + min + "-" + max + " . Please input again ");
                    } else {
                        return choice;
                    }
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, try again!");
            }
        }
    }

    String fruitId(ArrayList<Fruit> listFruits) {
        while (true) {
            System.out.print("Enter ID: ");
            String id = sc.nextLine().trim();
            // check input id is empty
            if (id.isEmpty()) {
                System.err.print("  ID can not empty, enter again.\n");
                continue;
            }
            // check input id
            if (!validation.isValidID(id)) {
                // Check if input id matches id in list
                if (!validation.isDuplicateId(listFruits, id)) {
                    return id;
                } else {
                    System.err.print("  ID is already in use!!! Please enter another ID.\n");
                }
            } else {
                System.err.print("  ID unvalible, enter again.\n ");
            }
        }
    }

    String inputString(String mess) {
        while (true) {
            System.out.print(mess);
            String string = sc.nextLine().trim();
            // check input id is empty
            if (string.isEmpty()) {
                System.err.println("Empty! Please Enter again ");
                continue;
            }
            // Input string must match with regex
            if (string.matches("^[a-zA-Z ]+$")) {
                return string;
            } else {
                System.err.println("Invalid data! Please re-enter.");
            }
        }
    }

    double inputDouble(String mess) {
        while (true) {
            System.out.print(mess);
            String salary = sc.nextLine().trim();
            // check salary is empty
            if (!salary.isEmpty()) {
                try {
                    double price = Double.parseDouble(salary);
                    // check price is greater than 0
                    if (price > 0) {
                        return price;
                    } else {
                        System.err.println("  Price must more than 0. ");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("  Price must be a numberical value. ");
                }
            } else { // empty string -> display error & re-enter
                System.err.println("  Price can not empty. ");
            }
        }
    }

    int inputInt(String mess) {
        while (true) {
            System.out.print(mess);
            String input = sc.nextLine().trim();
            // check salary is empty
            if (!input.isEmpty()) {
                try {
                    int Quantity = Integer.parseInt(input);
                    // check price is greater than 0
                    if (Quantity > 0) {
                        return Quantity;
                    } else {
                        System.err.println("  Quantity must more than 0. ");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("  Quantity must be a numberical value. ");
                }
            } else { // empty string -> display error & re-enter
                System.err.println("  Quantity can not empty. ");
            }
        }
    }

     public boolean inputYesNo(String message) {
        System.out.print(message);
        while (true) {
            String str = sc.nextLine().trim();
            // check input is not empty
            if (!str.isEmpty()) {
                // check if the input is the equals as the character 'y'/'Y'
                if (str.equalsIgnoreCase("y")) {
                    return true;
                }
                // check if the input is the equals as the character 'n'/'N'
                if (str.equalsIgnoreCase("n")) {
                    return false;
                }
                System.out.print("You must choose Yes(Y) or No(N)!\nEnter again: ");
            } else {
                System.err.print("Choice can not empty, enter again: ");
            }
        }

    }
}
