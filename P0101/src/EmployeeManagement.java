
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class EmployeeManagement {

    static GetInputData getInputData = new GetInputData();
    static Validation validation = new Validation();
    static Scanner sc = new Scanner(System.in);

    /**
     * Method used to display the menu
     */
    public void displayMenu() {
        System.out.println("===========Employee management system===========");
        System.out.println("Main menu:");
        System.out.println("   1.  Add employees ");
        System.out.println("   2.  Update employees ");
        System.out.println("   3.  Remove employees ");
        System.out.println("   4.  Search employees ");
        System.out.println("   5.  Sort employees by salary ");
        System.out.println("   6.  Exit ");
    }

    /**
     * Method used to display employees information
     *
     * @param list
     */
    public void displayAll(ArrayList<Employee> list) {
        System.out.printf("%-5s | %-11s | %-20s | %-13s | %-22s | %-18s | %-28s | %-8s | %-10s | %-15s\n",
                "Id", "FirstName", "LastName", "Phone number", "Email",
                "Address", "Date of birth", "Sex", "Salary", "Agency");

        // access each element in list
        for (Employee employee : list) {
            employee.display();
        }
    }

    /**
     * This method is add employees to list
     *
     * @param list
     */
    public void addEmployeeToList(ArrayList<Employee> list) {
        System.out.println("----------Add Employees----------");
        String id = getInputData.getID(list);
        String firstName = getInputData.getString("Enter First Name: ");
        String lastName = getInputData.getString("Enter Last Name: ");
        String phone = getInputData.getPhone();
        String email = getInputData.getEmail();
        Date DOB = getInputData.getDate();
        String address = getInputData.getString("Enter Address: ");
        String sex = getInputData.gexSex(0, 1);
        double salary = getInputData.getSalary();
        String Agency = getInputData.getString("Enter Egency: ");
        Employee employee = new Employee(id, firstName, lastName, phone, email, address, DOB, sex, salary, Agency);
        list.add(employee);
        System.out.println("Add employee successful.");

    }

    /**
     * Method used to update staff when requested by user
     *
     * @param list
     */
    public void updateEmployees(ArrayList<Employee> list) {
        System.out.println("----------Update Employees----------");
        // Check the list is empty empty
        if (list.isEmpty()) {
            System.out.println("  Please enter employee first.");
            return;
        }
        String id = getInputData.getIdUpdate();
        // check the id entered does not match any id in the list 
        if (!validation.isDuplicateId(list, id)) {
            System.out.println("  ID not found !!! ");

        }
        // access each element in list
        for (int i = 0; i < list.size(); i++) {
            // compare the input id and the id in the list
            if (id.equalsIgnoreCase(list.get(i).getId())) {
                Employee employee = getEmployeeByID(list, id);
                // check the user wants to update the data
                if (validation.isInputYes("Do you want to update ID?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String Id = getInputData.getID(list);
                    employee.setId(Id);
                }
                // check the user wants to update the data
                if (validation.isInputYes("Do you want to update first name?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String firstName = getInputData.getString("Enter First Name: ");
                    employee.setFirstName(firstName);
                }
                if (validation.isInputYes("Do you want to update last name?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String lastName = getInputData.getString("Enter Last Name: ");
                    employee.setLastName(lastName);
                }
                if (validation.isInputYes("Do you want to update phone?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String phone = getInputData.getPhone();
                    employee.setPhone(phone);
                }
                if (validation.isInputYes("Do you want to update email?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String email = getInputData.getEmail();
                    employee.setEmail(email);
                }
                if (validation.isInputYes("Do you want to update addres?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String address = getInputData.getString("Enter Address: ");
                    employee.setAddress(address);
                }
                if (validation.isInputYes("Do you want to update date of birth?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    Date DOB = getInputData.getDate();
                    employee.setDob(DOB);
                }
                if (validation.isInputYes("Do you want to update sex?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    String sex = getInputData.gexSex(0, 1);
                    employee.setSex(sex);
                }
                if (validation.isInputYes("Do you want to update salary?\nChoose Yes(Y) to update, No(N) to continue.")) {
                    double salary = getInputData.getSalary();
                    employee.setSalary(salary);
                }
                if (validation.isInputYes("Do you want to update acency?\nChoose Yes(Y) to update, No(N) to continue.")) {

                    String Agency = getInputData.getString("Enter Egency: ");
                    employee.setAgency(Agency);
                }
                System.out.println("Update employee successful.");
            }
        }

    }

    /**
     * Methods used to get employees information from the employee's ID
     *
     * @param list
     * @param id
     * @return
     */
    public Employee getEmployeeByID(ArrayList<Employee> list, String id) {
        // access each object in the list
        for (Employee employee : list) {
            // compare input id and available id
            if (id.equals(employee.getId())) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Method use to remove employee from the list
     *
     * @param list
     */
    public void removeEmployee(ArrayList<Employee> list) {
        System.out.println("----------Remove Employees----------");
        // Check if the list is empty
        if (list.isEmpty()) {
            System.out.println("  Please enter employee first.");
        }
        do {
            System.out.print("Input ID employee need remove:");
            String id = sc.nextLine().trim();
            // checkDuplicate
            if (!validation.isDuplicateId(list, id)) {
                System.err.println("  ID not found !!! ");
                return;
            }
            // access each element in list 
            for (int i = 0; i < list.size(); i++) {
                // compare the input id and the id in the list
                if (list.get(i).getId().equalsIgnoreCase(id)) {
                    list.remove(i);
                    System.out.println("Remove employee successful.");

                }
            }

        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action,"
                + " press \"N\" if you want to end the action. "));

    }

    /**
     * Method use to find employees
     *
     * @param list
     */
    public void searchEmployee(ArrayList<Employee> list) {
        System.out.println("----------Search Employees----------");
        // Check if the list is empty
        if (list.isEmpty()) {
            System.out.println("  List Empty! ");
            return;
        }
        do {
            System.out.print("Enter search keywords: ");
            String searchValue = getInputData.getKeySearch();
            ArrayList<Employee> listEmployeeSearch = new ArrayList<>();
            // access each object in the list
            for (Employee employee : list) {
                String str = employee.getFirstName() + " " + employee.getLastName();
                // check to see if the generated string contains the input string
                if (str.toLowerCase().contains(searchValue.toLowerCase())) {
                    listEmployeeSearch.add(employee);
                }
            }
            // Check if the search list is empty
            if (listEmployeeSearch.isEmpty()) {
                System.out.println("  There are no employee match with "
                        + "\"" + searchValue + "\"");
                return;
            }
            System.out.println("LIST OF EMPLOYEES FOUND ");
            displayAll(listEmployeeSearch);
        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action,"
                + " press \"N\" if you want to end the action. "));
    }

    /**
     * Method use to Sort the salaries of the employees in the list
     *
     * @param list
     */
    public void sortListEmployeesBySalary(ArrayList<Employee> list) {
        System.out.println("----------Sort Employees----------");
        // Check the list is empty
        if (!list.isEmpty()) {
            // check the length of the list is less than 2
            if (list.size() < 2) {
                if (list.size() == 1) {
                    displayAll(list);
                }
                return;
            }
            System.out.println("LIST EMPLOYEES BEFORE SORT::");
            displayAll(list);
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (int) (o1.getSalary() - o2.getSalary());
                }

            });
            System.out.println("LIST EMPLOYEES AFTER SORT:");
            displayAll(list);
        } else {
            System.out.println("  List Empty! Can not sort. ");
        }
    }

}
