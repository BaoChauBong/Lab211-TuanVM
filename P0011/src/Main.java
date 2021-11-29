
public class Main {

    public static void main(String[] args) {
        GetInputData getInputData = new GetInputData();
        Manager manager = new Manager();

        while (true) {
            // Display a menu
            manager.displayMenu();
            // user selects an option
            int option = getInputData.getOption(1, 4, "Enter your choice: ");
            switch (option) {
                // 1.Convert From Binary
                case 1:
                    manager.convertFormBinary();
                    break;
                // 2.Convert From Decimal
                case 2:
                    manager.convertFormDecimal();
                    break;
                // 3.Convert From Hexadecimal
                case 3:
                    manager.convertFormHexadecimal();
                    break;
                // 4.Exit
                case 4:
                    return;

            }
        }
    }
}
