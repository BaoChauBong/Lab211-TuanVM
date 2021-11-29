
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class GetInputData {

    static Scanner sc = new Scanner(System.in);
    Validation validation = new Validation();

    /**
     * Method use get integer input
     *
     * @param min
     * @param max
     * @param mess
     * @return
     */
    int getIntInput(int min, int max, String mess) {
        while (true) {
            try {
                System.out.print(mess);
                String input = sc.nextLine().trim();
                //check input is empty
                if (input.isEmpty()) {
                    System.out.println("Empty! Please input again ");
                    continue;
                }
                int choice = Integer.parseInt(input);
                // Check if the selection is over the allowable limit
                if (choice < min || choice > max) {
                    System.out.println("Please input number in range " + min + "-" + max + " . Please input again ");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, try again!");
            }
        }
    }

    String getString(String mess) {
        while (true) {
            System.out.print(mess);
            String input = sc.nextLine().trim();
            // check input id is empty
            if (input.isEmpty()) {
                System.err.println("Empty! Please Enter again ");
                continue;
            }
            // Input string must match with regex
            if (input.matches("^[a-zA-Z ]+$")) {
                return input;
            } else {
                System.err.println("Invalid data! Please re-enter.");
            }
        }
    }

    String getID(ArrayList<Product> listProduct) {
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
                if (!validation.isDuplicateId(listProduct, id)) {
                    return id;
                } else {
                    System.err.print("  ID is already in use!!! Please enter another ID.\n");
                }
            } else {
                System.err.print("  ID unvalible, enter again.\n ");
            }
        }
    }

    double getPrice() {
        while (true) {
            System.out.print("Enter Price: ");
            String salary = sc.nextLine().trim();
            // check salary is empty
            if (!salary.isEmpty()) {
                try {
                    double price = Double.parseDouble(salary);
                    // check price is greater than 0
                    if (price > 0) {
                        return price;
                    } else {
                        System.err.println("  Price must more than 0. ");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("  Price must be a numberical value. ");
                }
            } else { // empty string -> display error & re-enter
                System.err.println("  Price can not empty. ");
            }
        }
    }

    Date getExpiryDate() {
        while (true) {
            System.out.print("Enter Expiry Date(dd/MM/yyyy): ");
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
                        return dt;
                    } else {
                        System.err.println("  Please enter a right date. ");
                    }

                } catch (ParseException e) {
                    System.err.println("  Expiry Date must in format dd/MM/yyyy. ");

                }
            }
        }
    }

    Date getManufactureDate(Date expiryDate) {
        while (true) {
            System.out.print("Enter Manufacture date(dd/MM/yyyy): ");
            String date = sc.nextLine().trim();
            if (date.isEmpty()) {
                System.err.println("  Empty! Enter try again! ");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date now = new Date();
                try {
                    Date dt = sdf.parse(date);
                    String st = sdf.format(dt);
                    // check date with format 
                    if (st.equals(date)) {
                        // check enter date with expiration date with now date
                        if (dt.before(expiryDate) && now.after(dt)) {
                            return dt;
                        } else {
                            System.out.println("Manufacture date must before " + "expiry date. ");
                        }
                    } else {
                        System.err.println("  Please enter a right date. ");
                    }
                } catch (Exception e) {
                    System.out.println("Manufacture date must in format dd/MM/yyyy. ");
                }
            }

        }
    }

    public Storekeeper getStorekeeperByID(int id, ArrayList<Storekeeper> listStorekeeper) {
        // access each element in the list
        for (Storekeeper storekeeper : listStorekeeper) {
            // check input id with id in list 
            if (storekeeper.getId() == id) {
                return storekeeper;
            }
        }
        return null;
    }

    /**
     * Method used to get the id that the user wants to update
     *
     * @return id if it is not empty
     */
    public String getIdUpdate() {
        while (true) {
            System.out.print("Input ID need update: ");
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

    public Product getProductByID(String id, ArrayList<Product> listProduct) {
        // access each element in the list
        for (Product product : listProduct) {
            // check input id with id in list are equal
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    Date getReceiptDate(Date dateOfManufacture, Date expiryDate) {

        // loop until have valid name were inputted
        while (true) {
            System.out.print("Enter Receipt date: ");
            String raw = sc.nextLine().trim();
            if (!raw.isEmpty()) { // not empty ~> check next condition
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date now = new Date();
                try {
                    Date date = sdf.parse(raw);
                    String result_str = sdf.format(date);
                    // check date with format 
                    if (result_str.equals(raw)) {
                        // check enter date with manufacture date, expiry date.
                        if (date.after(dateOfManufacture) && date.before(expiryDate)) {
                            return date;
                        } else {
                            System.out.println("Receipt date must "
                                    + "after manufacture date, before expiry date.");
                        }
                    } else {
                        System.out.println("Please enter a right date. ");
                    }
                } catch (ParseException e) {
                    System.out.println("Receipt date must in format dd/MM/yyyy. ");
                }
            } else { // empty string ~> display error & re-enter
                System.out.println("Receipt date can not empty. ");
            }
        }
    }

    public static String EoD() {

        System.out.print("Enter your choice: ");
        // loop until have valid name were inputted
        while (true) {
            String string = sc.nextLine().trim();
            if (!string.isEmpty() && (string.equalsIgnoreCase("e") || (string.equalsIgnoreCase("d")))) { // not empty + satisfy conditions  ~> finish
                return string;
            } else { // empty string ~> display error & re-enter
                System.out.print("Choice can not empty, enter again: ");
            }
        }
    }

    Date getdate() {
        while (true) {
            System.out.print("Enter the Receipt Date to search:(dd/MM/yyyy): ");
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
                        return dt;
                    } else {
                        System.err.println("  Please enter a right date. ");
                    }

                } catch (ParseException e) {
                    System.err.println("  Receipt Date must in format dd/MM/yyyy. ");

                }
            }
        }
    }
}
