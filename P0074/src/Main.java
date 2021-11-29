
public class Main {

    public static void main(String[] args) {
        Management management = new Management();
        GetInputData getinputData = new GetInputData();

        while (true) {
            //display a menu 
            management.displayMenu();
            // user selects an option
            int option = getinputData.getInputChoice();

            switch (option) {
                case 1:
                    //Addition matrixes
                    management.additionMatrix();
                    break;
                case 2:
                    //  Subtraction matrixes
                    management.subtractionMatrix();
                    break;
                case 3:
                    // Multiplication matrixes
                    management.multiplicationMatrix();
                    break;
                case 4:
                    //Exit program.
                    return;

            }
        }
    }
}
