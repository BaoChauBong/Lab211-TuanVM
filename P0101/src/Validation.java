
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    static Scanner sc = new Scanner(System.in);
    EmployeeManagement controller = new EmployeeManagement();

    /**
     * This is method is use to check id
     *
     * @param id
     * @return
     *
     */
    public boolean isValidID(String id) {

        // access each character of the string id
        for (int i = 0; i < id.length(); i++) {
            // check each character contains character 
            // less than '0' || is greater than '9' 
            if ((id.charAt(i) < '0' || id.charAt(i) > '9')) {
                return true;
            }
        }
        return false;

    }

    /**
     * This is the method used to check for duplicate of id
     *
     * @param list
     * @param id
     * @return true if that id is duplicate and returns false if it is not
     */
    public boolean isDuplicateId(List<Employee> list, String id) {
        // loop to access each element of the list
        for (int i = 0; i < list.size(); i++) {
            // Compare imported id with existing id 
            if (list.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is use to check the phone
     *
     * @param phone
     * @return true if the input is satisfied and returns false otherwise
     */
    public boolean isValidPhone(String phone) {
        // check match input string with regex
        if (phone.matches("^[0-9]{10}$")) {
            // check if the phone number in the first place is the character '0'
            if (phone.charAt(0) == '0') {
                return true;
            } else {
                System.err.println("  Phone number must start by '0'. ");

                return false;
            }
        } else {
            System.err.println("  Phone must a string of numbers and has 10 digits. ");
            return false;
        }
    }

    /**
     * This method is used to check the age when the user enters the date of
     * birth
     *
     * @param date
     * @return true when age is satisfied and false when age is not satisfied
     */
    public boolean isValidAge(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(date);
        // Check the input date compared to the determination date
        if (dob.after(now)) {
            System.err.println("  Can't be born in the future. ");
            return false;
        }
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age = year1 - year2;

        int moth1 = now.get(Calendar.MONTH);
        int moth2 = dob.get(Calendar.MONTH);
        // The current monthly check was set first compared to the month in DOB 
        if (moth2 > moth1) {
            age--;
        } else if (moth1 == moth2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            // Check if the current date is bigger than the date of birth
            if (day2 > day1) {
                age--;
            }
        }
        // Check which age is greater than 18 years old
        if (age > 18) {
            return true;
        } else {
            System.err.println("  You are under 18 years old. ");
            return false;
        }
    }

    /**
     * Method used to check user's request
     *
     * @param notification
     * @return about true if user input is reasonable
     */
    public boolean isInputYes(String notification) {
        System.out.println(notification);
        System.out.print("Enter your choice: ");
        while (true) {
            String s = sc.nextLine();
            // Check if the input string is empty
            if (!s.isEmpty()) {
                s = s.replaceAll(" ", "");
                // compare two strings
                if (s.equalsIgnoreCase("Y")) {
                    return true;
                }
                //compare two strings
                if (s.equalsIgnoreCase("N")) {
                    return false;
                }
                System.err.print("  You must choose Yes(Y) or No(N)!\nEnter again: ");
            } else {
                System.err.print("  Choice can not empty, enter again: ");
            }
        }

    }

    
    }


