
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    Scanner sc = new Scanner(System.in);

    public boolean isInputYes(String message) {
        System.out.print(message);
        while (true) {
            String s = sc.nextLine().trim();
            // !check input is empty
            if (!s.isEmpty()) {
                // check if the input is the equals as the character 'y'/'Y'
                if (s.equalsIgnoreCase("Y")) {
                    return true;
                }
                // check if the input is the equals as the character 'n'/'N'
                if (s.equalsIgnoreCase("N")) {
                    return false;
                }
                System.out.print("You must choose Yes(Y) or No(N)!\nEnter again: ");
            } else {
                System.out.print("Choice can not empty, enter again: ");
            }
        }
    }

    boolean isValidID(String id) {
        // access each character of the string id
        for (int i = 0; i < id.length(); i++) {
            // check each character in string 
            if ((id.charAt(i) < '0' || id.charAt(i) > '9') && (id.charAt(i) < 'a' || id.charAt(i) > 'z')
                    && (id.charAt(i) < 'A' || id.charAt(i) > 'Z')) {
                return true;
            }
        }
        return false;
    }

    boolean isDuplicateId(ArrayList<Product> listProduct, String id) {
        // loop to access each element of the list
        for (int i = 0; i < listProduct.size(); i++) {
            // Compare imported id with existing id 
            if (listProduct.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
