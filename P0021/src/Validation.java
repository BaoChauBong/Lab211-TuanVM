
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    Scanner sc = new Scanner(System.in);

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

    boolean isDuplicate(ArrayList<Student> list, String id, String semester, String courseName) {
        // access each element in the list
        for (Student student : list) {
            // check id, semester, course Name entered has equals with id, semester, course name in the list
            if (id.equalsIgnoreCase(student.getId())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdExists(ArrayList<Student> students, String id) {
        // access each element in the list
        for (Student student : students) {
            // check if the entered id is equal to the id in the list
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    boolean isInputUD(String mess) {
        while (true) {
            System.out.print(mess);
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Empty!! Please enter agian.");
            }
            //return true if user input u/U
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            //return false if user input d/D
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }
}
