
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetInputData {

    static Scanner sc = new Scanner(System.in);
    Validation validation = new Validation();

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

    String getID(String mess) {
        while (true) {
            System.out.print(mess);
            String id = sc.nextLine().trim();
            // check input id is empty
            if (id.isEmpty()) {
                System.err.print("  ID can not empty, enter again.\n");
                continue;
            }
            // check input id
            if (!validation.isValidID(id)) {
                return id;
            } else {
                System.err.print("  ID unvalible, enter again.\n ");
            }
        }
    }

    public String getStringInput(String mess) {
        while (true) {
            System.out.print(mess);
            String studentName = sc.nextLine().trim();
            // check input id is empty
            if (studentName.isEmpty()) {
                System.err.println("Empty! Please Enter again ");
                continue;
            }
            // Input string must match with regex
            if (studentName.matches("^[a-zA-Z ]+$")) {
                return upperCaseFirstChar(studentName);
            } else {
                System.err.println("Invalid data! Please re-enter.");
            }
        }
    }

    /**
     * Method use to normalize strings
     *
     * @param stringInput
     * @return the normalized string from the input string
     */
    public String upperCaseFirstChar(String stringInput) {

        // check the input string is empty
        if (stringInput.isEmpty()) {
            return null;
        }
        String[] strA = stringInput.replaceAll("\\s+", " ").trim().split(" ");
        String tmp;
        StringBuilder result = new StringBuilder();
        // access each access element in the array
        for (String string : strA) {
            string = string.toLowerCase();
            if (string.length() == 1) {
                result.append(string.toUpperCase()).append(" ");
                continue;
            }
            tmp = string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
            result.append(tmp);
        }
        return result.toString().trim();
    }

    String getSemester(String mess) {
        while (true) {
            System.out.print(mess);
            String semester = sc.nextLine().trim();
            // check input id is empty
            if (semester.isEmpty()) {
                System.err.print("  semester can not empty, enter again.\n");
                continue;
            }
            // check input id
            if (!validation.isValidID(semester)) {
                return semester;
            } else {
                System.err.print("  semester unvalible, enter again.\n ");
            }
        }
    }

    String getCourseName(String message ) {
           Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, ".Net");
        map.put(3, "C/C++");

        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C/C++");

        return map.get(getIntInput(1, 3, message));
    }

   

}
