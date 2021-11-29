
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();
        EmployeeManagement employeeManagement = new EmployeeManagement();
        GetInputData getInputData = new GetInputData();
        int options = 0;
        do {
            // step1: display a menu 
            employeeManagement.displayMenu();
            // user selects an option
            options = getInputData.getOption();
            switch (options) {
                case 1:
                    // step 2:add employees 
                    employeeManagement.addEmployeeToList(list);
                    // display the list of employees after adding
                    employeeManagement.displayAll(list);
                    break; 
                case 2:
                    // step 3:update employees
                    employeeManagement.updateEmployees(list);
                    // display list of all employees after being updated
                    employeeManagement.displayAll(list);
                    break;
                case 3:
                    // step 4:remove employees
                    employeeManagement.removeEmployee(list);
                    // display list of all employees after being remove
                    employeeManagement.displayAll(list);
                    break;
                case 4:
                    // step 5:search employees
                    employeeManagement.searchEmployee(list);
                    break;
                case 5:
                    // step 6:sort employees by salary
                    employeeManagement.sortListEmployeesBySalary(list);
                    break;
                case 6:
                    // Exit
                    return;
            }

        } while (options <= 6);

    }
}
