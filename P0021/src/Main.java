
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        GetInputData getInputData = new GetInputData();
        ArrayList<Student> list = new ArrayList<>();
        while (true) {
            // Display a menu
            manager.displayMenu();
            // user selects an option
            int option = getInputData.getIntInput(1, 5, "Enter your choice: ");
            switch (option) {
                case 1:
                    // Create Student
                    manager.createStudent(list);
                    // display  list after Create
                    manager.displayAll(list);
                    break;
                case 2:
                    // Find/Sort (by name)
                    manager.findAndSortDisplayByName(list);
                    break;
                case 3:
                    // update/Delete
                    manager.updateOrDelete(list);
                    //display  list after update/delete
                    manager.displayAll(list);
                    break;
                case 4:
                    // Report
                    manager.report(list);
                    break;
                case 5:
                    // Exit
                    return;
            }

        }

    }
}
