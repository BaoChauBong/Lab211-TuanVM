
import entity.Fruit;
import entity.Order;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Manager {

    GetInputData getInputData = new GetInputData();

    /**
     * Method is use display Menu
     */
    void displayMenu() {
        System.out.println("FRUIT SHOP SYSTEM");
        System.out.println("        1. Create Fruit");
        System.out.println("        2. View orders");
        System.out.println("        3. Shopping(for buyer)");
        System.out.println("        4. Exit");
        System.out.println();
        System.out.println("(Please choose 1 to create product, 2 to view order, "
                + "3 for shopping, 4 to Exit program).");
    }

    /**
     * Method is use create fruits
     *
     * @param listFruits
     */
    void createFruit(ArrayList<Fruit> listFruits) {
        do {
            System.out.println("--------CREATE FRUIT--------");
            String fruitID = getInputData.fruitId(listFruits);
            String fruitName = getInputData.inputString("Fruit name: ");
            double price = getInputData.inputDouble("Price: ");
            int quantity = getInputData.inputInt("Quantity: ");
            String origin = getInputData.inputString("Origin: ");
            Fruit fruit = new Fruit(fruitID, fruitName, price, quantity, origin);
            listFruits.add(fruit);
            System.out.println("Creating successful. ");
        } while (getInputData.inputYesNo("Do you want to continue (Y/N)?"));

    }

    void displayFruits(ArrayList<Fruit> listFruits) {
        System.out.println("List of Fruit:");
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|\n", " ++Item++ ",
                " ++Fruit Name++ ", " ++Origin++ ", " ++Price++ ");
        DecimalFormat format = new DecimalFormat("0.##$");
        // access each element in list fruit
        for (int i = 0; i < listFruits.size(); i++) {
            System.out.printf("      %-5d    %-14s  %-11s   %-8s\n",
                    i + 1, listFruits.get(i).getFruitName(),
                    listFruits.get(i).getOrigin(),
                    format.format(listFruits.get(i).getPrice()));
        }

    }

// view orders 
    void viewOrders(ArrayList<Order> listView) {
        if (listView.isEmpty()) {
            System.out.println("There are nothing to view!");
            return;
        }
        DecimalFormat format = new DecimalFormat("0.##$");
        // access each element in list View
        for (Order order : listView) {
            ArrayList<Fruit> listOrder = new ArrayList<>();
            listOrder.addAll(order.getFruits());
            double total = 0;
            System.out.println("Customer: " + order.getCustomerName());
//            System.out.println("Id: " + order.getId());
            System.out.println("Product    | Quantity | Price | Amount |");
            //  access each element in list fruit
            for (Fruit fruit : listOrder) {
                System.out.printf("%-11s    %-6d     %-5s   %-6s\n",
                        fruit.getFruitName(), fruit.getQuantity(), format.format(fruit.getPrice()),
                        format.format(fruit.getPrice() * fruit.getQuantity()));
                // add total price
                total += fruit.getQuantity() * fruit.getPrice();
            }
            System.out.println("Total: " + format.format(total));
            System.out.println();

        }
    }

// Shopping fruit
    public void shopping(ArrayList<Fruit> listFruits, ArrayList<Order> listView) {
        // check list fruit
        if (listFruits.isEmpty()) {
            System.err.println("Empty! Add more fruit first.");
            return;
        }
        String customer;
        ArrayList<Fruit> listFruitOrder = new ArrayList<>();
        // loop util user want to exit
        while (true) {
            // display orders list
            displayForOrder(listFruits);
            int item = getInputData.inputInt("Select item: ");
            Fruit fruit = findFruitByIndex(item, listFruits);
            // check fruit
            if (fruit == null) {
                System.err.println("Not found product with item: " + item);
            } else {
                System.out.println("You selected:" + fruit.getFruitName());
                int quantity;
                while (true) {
                    quantity = getInputData.inputInt("Please input quantity: ");
                    // check the quantity entered with the number in the fruit list of that fruit
                    if (quantity <= fruit.getQuantity()) {
                        break;
                    } else if (getInputData.inputYesNo("Quantity exceeds stock"
                            + ", you want to take all the remaining quantity ("
                            + fruit.getQuantity() + " " + fruit.getFruitName() + "s)?(Y/N): ")) {
                        quantity = fruit.getQuantity();
                        break;
                    } else {
                        System.err.println("Choose quantity you want!");
                    }
                }
                // update quantity fruit in stock
                Fruit o = new Fruit(fruit.getFruitId(), fruit.getFruitName(),
                        fruit.getPrice(), quantity, fruit.getOrigin());
                setQuantity(o, listFruits);
                // check order list
                if (listFruitOrder.isEmpty()) {
                    listFruitOrder.add(o);
                } else {
                    // update quantity fruit order
                    boolean isExist = false;
                    //  access each element in listFruitOrder
                    for (Fruit fruitOrder : listFruitOrder) {
                        // check fruit buy with fruit in order list
                        if (fruitOrder.getFruitId().equalsIgnoreCase(o.getFruitId())) {
                            fruitOrder.setQuantity(fruitOrder.getQuantity() + o.getQuantity());
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        listFruitOrder.add(o);
                    }
                }
                // add fruit and complete order
                if (getInputData.inputYesNo("Do you want to order now? (Y/N): ")) {
                    viewOrder(listFruitOrder);
                    customer = getInputData.inputString("Input your name: ");
                   // int id = listView.size() + 1;
                    listView.add(new Order(customer, listFruitOrder));
                    System.err.println("Order success!");
                    break;
                }
            }
            // ask user want to continue shopping
            if (!getInputData.inputYesNo("Do you want to shopping continue? (Y/N): ")) {
                System.out.println("The products you have selected:");
                viewOrder(listFruitOrder);
                if (getInputData.inputYesNo("You want to buy them? (Y/N): ")) {
                    customer = getInputData.inputString("Customer: ");
                   // int id = listView.size() + 1;
                    listView.add(new Order(customer, listFruitOrder));
                } else {
                    // access each element in listFruitOrder
                    for (Fruit o : listFruitOrder) {
                        setQuantityIfNotOrder(o, listFruits);
                    }
                }
                break;
            }
        }
    }

    private void displayForOrder(ArrayList<Fruit> listFruits) {
        System.out.println("List of Fruit:");
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|\n", " ++Item++ ",
                " ++Fruit Name++ ", " ++Origin++ ", " ++Price++ ");
        DecimalFormat format = new DecimalFormat("0.##$");
        // access each element in list fruit
        for (int i = 0; i < listFruits.size(); i++) {
            if (listFruits.get(i).getQuantity() > 0) {
                System.out.printf("      %-5d    %-14s  %-11s   %-8s\n",
                        i + 1, listFruits.get(i).getFruitName(),
                        listFruits.get(i).getOrigin(), format.format(listFruits.get(i).getPrice()));
            }
        }
    }

    // get fruit by index
    public static Fruit findFruitByIndex(int item, ArrayList<Fruit> listFruits) {
        // access each element in list fruit
        for (int i = 0; i < listFruits.size(); i++) {
            // check i with item input
            if (i == (item - 1)) {
                return listFruits.get(i);
            }
        }
        return null;
    }
    // update quantity when user buy fruit

    public static void setQuantity(Fruit o, ArrayList<Fruit> listFruits) {
        // access each element in list fruit
        for (Fruit fruit : listFruits) {
            // check the id of the purchased fruit with the fruit in the fruit list
            if (fruit.getFruitId().equalsIgnoreCase(o.getFruitId())) {
                fruit.setQuantity(fruit.getQuantity() - o.getQuantity());
                break;
            }
        }
    }

    public void viewOrder(ArrayList<Fruit> order) {
        int count = 1;
        DecimalFormat format = new DecimalFormat("0.##$");
        System.err.println("Your order: ");
        System.out.println();
        System.out.println("Product    | Quantity | Price | Amount  ");
        // access each element in list order
        for (Fruit o : order) {
            System.out.printf("%-11s    %-6d     %-5s   %-6s\n", count++ + "." + o.getFruitName(), o.getQuantity(),
                    format.format(o.getPrice()), format.format(o.getPrice() * o.getQuantity()));
        }
    }

//    // add new order to list order
//    public static void order(ArrayList<Fruit> orderList, ArrayList<Order>  customerList) {
//        // check(orderList,customer)-> !empty
//        if (!orderList.isEmpty() ) {
//            customerList.add(orderList);
//        }
//    }
    // update quantity when user cancel an order
    public static void setQuantityIfNotOrder(Fruit o, ArrayList<Fruit> listFruits) {
        // access each element in list Fruit
        for (Fruit fruit : listFruits) {
            // check the id of the purchased fruit with the fruit in the fruit list
            if (fruit.getFruitId().equalsIgnoreCase(o.getFruitId())) {
                fruit.setQuantity(fruit.getQuantity() + o.getQuantity());
                break;
            }
        }
    }
}
