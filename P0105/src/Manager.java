
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

class Manager {

    GetInputData getInputData = new GetInputData();
    Validation validation = new Validation();

    /**
     * Method use display menu
     */
    void displayMenu() {
        System.out.println("Main Menu: ");
        System.out.println("      1. Add Storekeeper ");
        System.out.println("      2. Add product ");
        System.out.println("      3. Update product ");
        System.out.println("      4. Search product by Name, Category, Storekeeper, ReceiptDate ");
        System.out.println("      5. Sort product by Expiry date, Date of manufacture ");
        System.out.println("      6. Exit ");
    }

    void addStorekeeper(ArrayList<Storekeeper> listStorekeeper) {
        System.out.println("----------Add Name Storekeeper----------");
        do {
            int id;
            // check list is empty
            if (listStorekeeper.isEmpty()) {
                id = 1;
            } else {
                id = listStorekeeper.size() + 1;
            }
            String name = getInputData.getString("Enter Storekeeper Name: ");
            Storekeeper storekeeper = new Storekeeper(id, name);
            listStorekeeper.add(storekeeper);
            System.out.println("Add storekeeper successful.");
        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action," + " press \"N\" if you want to end the action. Enter choose: "));

    }

    void displayStorekeeper(ArrayList<Storekeeper> listStorekeeper) {
        // access each element in the list
        for (Storekeeper storekeeper : listStorekeeper) {
            System.out.println(storekeeper.getId() + "-" + storekeeper.getName());
        }
    }

    void addProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) {
        System.out.println("----------Add Product----------");
        do {
            String id = getInputData.getID(listProduct);
            String name = getInputData.getString("Enter product name: ");
            String location = getInputData.getString("Enter location: ");
            double Price = getInputData.getPrice();
            Date expiryDate = getInputData.getExpiryDate();
            Date dateOfManufacture = getInputData.getManufactureDate(expiryDate);
            String category = getInputData.getString("Enter Category: ");
            System.out.println("Choose Storekeeper[1-" + (listStorekeeper.size()) + "]");
            displayStorekeeper(listStorekeeper);
            int idStore = getInputData.getIntInput(1, listStorekeeper.size(), "Enter Storekeeper: ");
            Storekeeper storekeeper = new Storekeeper(getInputData.getStorekeeperByID(idStore, listStorekeeper).getId(), getInputData.getStorekeeperByID(idStore, listStorekeeper).getName());
            Date receiptDate = getInputData.getReceiptDate(dateOfManufacture, expiryDate);
            Product product = new Product(id, name, location, Price, expiryDate, dateOfManufacture, category, storekeeper, receiptDate);
            listProduct.add(product);
            System.out.println("Add product successful. ");
        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action," + " press \"N\" if you want to end the action. Enter choose:"));
    }

    void displayProducts(ArrayList<Product> listProduct) {
        // check list is empty
        if (listProduct.isEmpty()) {
            System.out.println("  Please enter employee first.");
            return;
        }

        System.out.printf("%5s %10s %10s %10s %15s %25s %15s %15s %15s\n", "ID", "Name",
                "Location", "Price", "Expiry date", "Date of manufacture",
                "Category", "Storekeeper", "Receipt date");
        // access each element in the list
        for (Product product : listProduct) {
            product.display();
        }
    }

    void updateProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) {
        System.out.println("----------Update Product----------");
        do {
            // check list is empty
            if (listProduct.isEmpty()) {
                System.out.println("  Please enter employee first.");
                return;
            }
            String id = getInputData.getIdUpdate();
            // check the id entered does not match any id in the list 
            if (!validation.isDuplicateId(listProduct, id)) {
                System.err.println("  ID not found !!! ");
            }
            // access each element in the list
            for (int i = 0; i < listProduct.size(); i++) {
                if (id.equalsIgnoreCase(listProduct.get(i).getId())) {
                    Product product = getInputData.getProductByID(id, listProduct);
                    // check the user wants to update the data
                    if (validation.isInputYes("Do you want to update ID?(Y/N): ")) {
                        String ID = getInputData.getID(listProduct);
                        product.setId(ID);
                    }
                    // check the user wants to update the data
                    if (validation.isInputYes("Do you want update product name?(Y/N): ")) {
                        String name = getInputData.getString("Enter Product name: ");
                        product.setName(name);
                    }
                    // check the user wants to update the data
                    if (validation.isInputYes("Do you want update location?(Y/N): ")) {
                        String location = getInputData.getString("Enter Location name: ");
                        product.setLocation(location);
                    }
                    // check the user wants to update the data
                    if (validation.isInputYes("Do you want update price?(Y/N): ")) {
                        double price = getInputData.getPrice();
                        product.setPrice(price);
                    }
                    if (validation.isInputYes("Do you want update expiry date?(Y/N): ")) {
                        Date expiryDate = getInputData.getExpiryDate();
                        product.setExpiryDate(expiryDate);
                    }
                    if (validation.isInputYes("Do you want update manufacture date?(Y/N): ")) {
                        Date dateOfManufacture = getInputData.getManufactureDate(product.getExpiryDate());
                        product.setDateOfManufacture(dateOfManufacture);
                    }
                    if (validation.isInputYes("Do you want update category?(Y/N): ")) {
                        String category = getInputData.getString("Enter Category: ");
                        product.setCategory(category);
                    }
                    if (validation.isInputYes("Do you want update storekeeper?(Y/N): ")) {
                        System.out.println("Choose storekeeper [1-" + (listStorekeeper.size()) + "]");
                        displayStorekeeper(listStorekeeper);
                        System.out.print("Your choice: ");
                        int idStore = getInputData.getIntInput(1, listStorekeeper.size(), "Enter Storekeeper: ");
                        Storekeeper storekeeper = new Storekeeper(getInputData.getStorekeeperByID(idStore, listStorekeeper).getId(), getInputData.getStorekeeperByID(idStore, listStorekeeper).getName());
                        product.setStorekeeper(storekeeper);
                    }
                    if (validation.isInputYes("Do you want update receipt date?(Y/N): ")) {
                        Date receiptDate = getInputData.getReceiptDate(product.getDateOfManufacture(), product.getExpiryDate());
                        product.setReceiptDate(receiptDate);
                    }
                    System.out.println("Update employee successful.");
                }
            }
        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action," + " press \"N\" if you want to end the action. Enter choose:"));
    }

    void search(ArrayList< Product> listProduct) {
        // check list is empty
        if (listProduct.isEmpty()) {
            System.out.println("  Please enter employee first.");
            return;
        }
        do {
            System.out.print("------Search product-------");
            int option = getInputData.getIntInput(1, 4, "\n 1:Name\n 2:Category\n 3:Storekeeper\n 4:ReceiptDate\n Enter search choice: ");
            switch (option) {
                case 1:
                    String input = getInputData.getString("Enter the value to search: ");
                    // search by name 
                    searchByName(listProduct, input);
                    break;
                case 2:
                    // search by category
                    input = getInputData.getString("Enter the value to search: ");
                    searchByCategory(listProduct, input);
                    break;
                case 3:
                    input = getInputData.getString("Enter the value to search: ");
                    // search by storekeeper 
                    searchByStorekeeper(listProduct, input);
                    break;
                case 4:
                    Date date = getInputData.getdate();
                    searchByReceiptDate(listProduct, date);
                    break;

            }

        } while (validation.isInputYes("Press \"Y\" if you want to keep taking action," + " press \"N\" if you want to end the action. Enter choose: "));
    }

    private void searchByName(ArrayList<Product> listProduct, String input) {
        ArrayList<Product> searchByName = new ArrayList<>();
        // access each element in the list
        for (Product product : listProduct) {
            // check name on list contains input string
            if (product.getName().contains(input)) {
                searchByName.add(product);
            }
        }
        // check the list search by name is empty
        if (searchByName.isEmpty()) {
            System.err.println("Not exist.");
            return;
        }
        System.out.println("List Product search by name.");
        displayProducts(searchByName);

    }

    private void searchByCategory(ArrayList<Product> listProduct, String input) {
        ArrayList<Product> searchByCategory = new ArrayList<>();
        // access each element in the list
        for (Product product : listProduct) {
            // check Category on list contains input string
            if (product.getCategory().contains(input)) {
                searchByCategory.add(product);
            }
        }
        // check the list search by category is empty
        if (searchByCategory.isEmpty()) {
            System.err.println("Not exist.");
            return;
        }
        System.out.println("List Product search by Category.");
        displayProducts(searchByCategory);
    }

    private void searchByStorekeeper(ArrayList<Product> listProduct, String input) {
        ArrayList<Product> searchByStorekeeper = new ArrayList<>();
        // access each element in the list
        for (Product product : listProduct) {
            // check Storekeeper on list contains input string
            if (product.getStorekeeper().getName().contains(input)) {
                searchByStorekeeper.add(product);
            }
        }
        // check the list search by Storekeeper is empty
        if (searchByStorekeeper.isEmpty()) {
            System.err.println("Not exist.");
            return;
        }
        System.out.println("List Product search by Storekeeper.");
        displayProducts(searchByStorekeeper);
    }

    private void searchByReceiptDate(ArrayList<Product> listProduct, Date date) {
        ArrayList<Product> searchByReceiptDate = new ArrayList<>();
        // access each element in the list
        for (Product product : listProduct) {
            // check ReceiptDate on list contains input string
            if (product.getReceiptDate().equals(date)) {
                searchByReceiptDate.add(product);
            }
        }
        // check the list search by ReceiptDate is empty
        if (searchByReceiptDate.isEmpty()) {
            System.err.println("Not exist.");
            return;
        }
        System.out.println("List Product search by ReceiptDate.");
        displayProducts(searchByReceiptDate);
    }

    void sort(ArrayList<Product> listProduct) {
        System.out.println("----------Sort Product----------");
        // Check the list is empty
        if (listProduct.isEmpty()) {
            System.out.println("List empty");
            return;
        }
        System.out.println("LIST Product BEFORE SORT::");
        displayProducts(listProduct);
        System.out.println("LIST AFTER SORT:");
        Collections.sort(listProduct, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                switch (o1.getExpiryDate().compareTo(o2.getExpiryDate())) {
                    case 1:
                        return 1;
                    case 0:
                        return o1.getDateOfManufacture().compareTo(o2.getDateOfManufacture());
                    default:
                        return -1;
                }
            }
        });
        displayProducts(listProduct);

    }

}
