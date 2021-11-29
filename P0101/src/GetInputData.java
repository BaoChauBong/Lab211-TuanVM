
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GetInputData {

    EmployeeManagement management = new EmployeeManagement();
    static Validation validation = new Validation();
    static Scanner sc = new Scanner(System.in);

    /**
     * This method is used to get choice from user
     *
     * @return about a positive integer
     */
    public int getOption() {
        while (true) {
            try {
                System.out.print("Input your choice: ");
                String choice = sc.nextLine().trim();
                // check the selection is empty
                if (choice.isEmpty()) {
                    System.out.println(" Choice empty!!! Please choose again. ");
                    management.displayMenu();
                    continue;
                }
                int option = Integer.parseInt(choice);
                // check negative option number
                if (option <= 0) {
                    System.out.println("  Option must be a positive number. ");
                    management.displayMenu();

                } // check option is greater than 6
                else if (option > 6) {
                    System.out.println("  Selection should be between 1-6. ");
                    management.displayMenu();

                } else {
                    return option;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Choice does not exist.Please choose again. ");
                management.displayMenu();

            }
        }
    }

    /**
     * This method is use to get ID from user
     *
     * @param employees
     * @return the id the user entered that met the conditions
     */
    public String getID(List<Employee> employees) {
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
                if (!validation.isDuplicateId(employees, id)) {
                    return id;
                } else {
                    System.err.print("  ID is already in use!!! Please enter another ID.\n");
                }
            } else {
                System.err.print("  ID unvalible, enter again.\n ");
            }
        }
    }

    /**
     * This method is use get string from user
     *
     * @param mess
     * @return string that the user entered has been checked and normalized
     */
    public String getString(String mess) {
        String input;
        while (true) {
            System.out.print(mess);
            input = sc.nextLine().trim();
            // check the input string is empty
            if (input.isEmpty()) {
                System.err.println("  Empty! Enter try again! ");
                continue;
            }
            // check the input string matches the regex
            if (input.matches("^[a-zA-Z ]+$")) {
                return input;
            } else {
                System.err.println("  Invalid data! Please re-enter. ");
            }
        }
    }

    /**
     * This method is use to get phone from user
     *
     * @return the phone number entered by the user that satisfies the
     * conditions
     */
    public String getPhone() {
        while (true) {
            System.out.print("Enter Phone number(format:10 digits start with 0(e.g:0123456789)): ");
            String phone = sc.nextLine().trim();
            // Check if the phone number is empty
            if (!phone.isEmpty()) {
                // Check if phone number matches regex
                if (validation.isValidPhone(phone)) {
                    return phone;
                }

            } else {
                System.err.println("  Phone number can not empty. ");
            }

        }
    }

    /**
     * This method is use get email from user
     *
     * @return when the user has correctly entered the email
     */
    public String getEmail() {
        String input;
        while (true) {
            System.out.print("Enter Email(eg: abcd@gmail.com): ");
            input = sc.nextLine().trim();
            // Check the input string is empty
            if (input.isEmpty()) {
                System.err.println("  Empty! Enter try again! ");
                continue;
            }
            // check the input string matches the regex
            if (input.matches("^[a-z0-9A-Z]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}+$")) {
                return input;
            } else {
                System.err.println("  Emails do not have the same format. Please re-enter it in the correct format. ");
            }
        }
    }

    /**
     * This is method use get date from user
     *
     * @return when the user has correctly entered the date
     */
    public Date getDate() {
        while (true) {
            System.out.print("Enter date of birth(dd/MM/yyyy): ");
            String date = sc.nextLine().trim();
            // check date is empty
            if (date.isEmpty()) {
                System.err.println("  Empty! Enter try again! ");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date dt = sdf.parse(date);
                    String st = sdf.format(dt);
                    // check date with format 
                    if (st.equals(date)) {
                        if (validation.isValidAge(dt)) {
                            return dt;
                        }
                    } else {
                        System.err.println("  Please enter a right date. ");
                    }
                } catch (ParseException e) {
                    System.err.println("  Date of birth must in format dd/MM/yyyy. ");
                }
            }
        }

    }

    /**
     * This method is use get string from user
     *
     * @param min
     * @param max
     * @return about string matches min and max values
     */
    public String gexSex(int min, int max) {
        String input;
        while (true) {
            System.out.println("Enter sex: ");
            System.out.println("0: Male");
            System.out.println("1: Female");
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    int number = Integer.parseInt(input);
                    // check upper and lower limit is exceeded
                    if (number < min || number > max) {
                        System.out.println("  Please input number  " + min + " or " + max + "!!!");
                        continue;
                    } // check if the number is 0 
                    else if (number == 0) {
                        return "Male";
                    } else {
                        return "Female";
                    }

                } catch (NumberFormatException e) {
                    System.out.print("  Please re-enter a positive number !!! ");
                }
            } else {
                System.out.println("Empty! Please re-enter. ");
            }
        }

    }

    /**
     * This method is use get salary
     *
     * @return the salary that the user entered is satisfied
     */
    public double getSalary() {
        while (true) {
            System.out.print("Enter salary: ");
            String salary = sc.nextLine().trim();
            // check salary is empty
            if (!salary.isEmpty()) {
                try {
                    double price = Double.parseDouble(salary);
                    // check price is greater than 0
                    if (price > 0) {
                        return price;
                    } else {
                        System.err.println("  Salary must more than 0. ");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("  Salary must be a numberical value. ");
                }
            } else { // empty string -> display error & re-enter
                System.err.println("  Salary can not empty. ");
            }
        }
    }

    /**
     * Method used to get the id that the user wants to update
     *
     * @return id if it is not empty
     */
    public String getIdUpdate() {
        while (true) {
            System.out.print("Input ID employee need update: ");
            String id = sc.nextLine().trim();
            // check input id is empty
            if (!id.isEmpty()) {
                if (!validation.isValidID(id)) {
                    return id;
                }
                System.err.println(" The input id needs to be a positive integer.");
                continue;
            }
            System.err.println("  ID can not empty!!!");

        }
    }

    /**
     * The method is to use the user-entered keyword check to search
     *
     * @return the entered keyword if it satisfies the test conditions
     */
    public String getKeySearch() {
        // loop until have valid name were inputted
        while (true) {
            String string = sc.nextLine().trim();
            string = string.replace("\\s+", " ");
            // check string empty
            if (!string.isEmpty()) {
                return string;
            } else {
                System.err.print("  String can not empty, enter again: ");
            }
        }

    }
}
