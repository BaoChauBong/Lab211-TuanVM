
import entity.Fruit;
import entity.Order;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        GetInputData getInputData = new GetInputData();
        ArrayList<Fruit> listFruits = new ArrayList<>();
        ArrayList<Order> listView =  new ArrayList<>();
        while (true) {
            // display a menu
            manager.displayMenu();
            // user selects an option
            int option = getInputData.getIntInput(1, 6, "Enter your choice: ");
            switch (option) {
                case 1:
                    // Create fruit
                    manager.createFruit(listFruits);
                    // Display all Fruits are created 
                    manager.displayFruits(listFruits);
                    break;
                case 2:
                    // View order
                    manager.viewOrders(listView);
                    break;
                case 3:
                    // Shopping
                    manager.shopping(listFruits, listView);
                    break;
                case 4:
                    // Exit
                    return;

            }

        }

    }
}
