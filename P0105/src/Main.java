
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        GetInputData getInputData = new GetInputData();
        ArrayList<Product> listProduct = new ArrayList<>();
        ArrayList<Storekeeper> storekeeper = new ArrayList<>();
        while (true) {
            // Display a menu
            manager.displayMenu();
            // user selects an option
            int option = getInputData.getIntInput(1, 6, "Enter your choice: ");
            switch (option) {
                case 1:
                    // Add Storekeeper: Storekeeper Name
                    manager.addStorekeeper(storekeeper);
                    // display after add Storekeeper
                    manager.displayStorekeeper(storekeeper);
                    break;
                case 2:
                    // Add product
                    manager.addProduct(listProduct, storekeeper);
                    // display after add Poduct 
                    manager.displayProducts(listProduct);
                    break;
                case 3:
                    // Update product
                    manager.updateProduct(listProduct, storekeeper);
                    // display after Update product
                    manager.displayProducts(listProduct);
                    break;
                case 4:
                    // Search product by Name, Category, Storekeeper,ReceiptDate
                    manager.search(listProduct);
                    break;
                case 5:
                    // Sort product by Expiry date, Date of manufacture
                    manager.sort(listProduct);
                    break;
                case 6:
                    // Exit
                    return;
            }

        }
    }
}
