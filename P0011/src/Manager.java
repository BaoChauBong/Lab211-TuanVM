
import java.math.BigInteger;

public class Manager {

    GetInputData getInputData = new GetInputData();

    /**
     * Method use display a menu
     */
    public void displayMenu() {
        System.out.println("====CONVERT BASE NUMBER PROGRAM====");
        System.out.println("1. Convert From Binary.");
        System.out.println("2. Convert From Decimal.");
        System.out.println("3. Convert From Hexadecimal.");
        System.out.println("4. Exit");
    }

    /**
     *
     * @param form
     * @param case1
     * @param case2
     * @return
     */
    int displayConvert(String form, String case1, String case2) {
        System.out.println("1.Convert form " + form + " to " + case1);
        System.out.println("2.Convert form " + form + " to " + case2);
        System.out.println("3.Convert form " + form + " to " + form);
        System.out.print("Enter your choice: ");
        return getInputData.inputIntLimit(1, 3);
    }

    void convertFormBinary() {
        do {
            int option = displayConvert("binary", "decimal", "hexadecimal");
            switch (option) {
                case 1:
                    // display binary --> decimal
                    displayChangeBinToDec();
                    break;
                case 2:
                    // display binary --> hexadecimal 
                    displayChangeBinToHex();
                    break;
                case 3:
                    // display binary --> binary
                    String number = getInputBinary();
                    convertToItSeft(number, "Binary");
                    break;
            }
        } while (getInputData.inputYesNo());
    }

    private void displayChangeBinToDec() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // regex match to see if the input string is all 0 and 1
            if (number.matches("^[01]+$")) {
                String output = ConvertFromBaseToDec(number, 2);
                System.out.println(" Binary after convert to Decimal: " + output);
                break;
            }
            System.out.println("Number you entered isn't binary number. ");
        }
    }

    private void displayChangeBinToHex() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            //  regex match to see if the input string is all 0 and 1
            if (number.matches("^[01]+$")) {
                String output = ConvertFromBaseToDec(number, 2);
                output = convertFromDecToBase(output, 16);
                System.out.println(" Binary after convert to Hexadecimal: " + output);
                break;
            }
            System.out.println("Number you entered isn't binary number. ");
        }
    }

    private void convertToItSeft(String input, String base) {
       
        System.out.println(input+" same base does not perform conversion " + base );
    }

    private String ConvertFromBaseToDec(String string, int base) {
        // Create string Letters to get value is index of string
        String Letters = "0123456789ABCDEF";
        string = string.toUpperCase();
        BigInteger BASE = new BigInteger(Integer.toString(base));
        BigInteger dec = new BigInteger("0");
        // access each element in the string 
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            // Convert char to int by get index
            int number = Letters.indexOf(ch);
            BigInteger a = new BigInteger(Integer.toString(number));
            dec = (dec.multiply(BASE)).add(a);
        }
        return dec.toString();
    }

    private String convertFromDecToBase(String dec, int baseInput) {
        BigInteger DEC = new BigInteger(dec);
        BigInteger zero = new BigInteger("0");
        // Create biginteger to store number convert by String base input
        BigInteger base = new BigInteger(Integer.toString(baseInput));
        // Create string result to return result over number
        StringBuilder result = new StringBuilder();
        // check dec = 0 -> append
        if (DEC.compareTo(zero) == 0) {
            result.append("0");
        } else {
            // Loop ulti dec number != 0
            while (DEC.compareTo(zero) != 0) {
                BigInteger remainder = DEC.mod(base);
                // check value <10 --> append 
                if (remainder.intValue() < 10) {
                    result.append(remainder.intValue());
                } else {
                    switch (remainder.intValue()) {
                        case 10:
                            // 10 will be replace by A
                            result.append("A");
                            break;
                        case 11:
                            // 11 will be replace by B
                            result.append("B");
                            break;
                        case 12:
                            // 12 will be replace by C
                            result.append("C");
                            break;
                        case 13:
                            // 13 will be replace by D
                            result.append("D");
                            break;
                        case 14:
                            // 14 will be replace by E
                            result.append("E");
                            break;
                        case 15:
                            // 15 will be replace by F
                            result.append("F");
                            break;
                    }
                }
                DEC = DEC.divide(base);
            }
        }
        return result.reverse().toString();
    }

    /**
     * Method use convert Form Decimal
     */
    void convertFormDecimal() {
        do {
            int option = displayConvert("decimal", "binary", "hexadecimal");
            switch (option) {
                case 1:
                    // display decimal --> binary
                    displayChangeDecToBin();
                    break;
                case 2:
                    // display decimal --> hexadecimal
                    displayChangeDecToHex();
                    break;

                case 3:
                    // display decimal --> decimal
                    String number = getInputDecimal();
                    convertToItSeft(number, "Decimal");
                    break;
            }
        } while (getInputData.inputYesNo());
    }

    private void displayChangeDecToBin() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match the input string with the regex to see if it is 0->9
            if (number.matches("^[0-9]+$")) {
                String output = convertFromDecToBase(number, 2);
                System.out.println(" Decimal after convert to binary: " + output);
                break;
            }
            System.out.println("Number you entered isn't decimal number. ");
        }
    }

    private void displayChangeDecToHex() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match the input string with the regex to see if it is 0->9
            if (number.matches("^[0-9]+$")) {
                String output = convertFromDecToBase(number, 16);
                System.out.println(" Decimal after convert to Hexadecimal: " + output);
                break;
            }
            System.out.println("Number you entered isn't decimal number ");
        }
    }

    /**
     * Method use convert Form Hexadecimal
     */
    void convertFormHexadecimal() {
        do {
            int option = displayConvert("hexadecimal", "binary", "decimal");
            switch (option) {
                case 1:
                    // display hexadecimal --> binary
                    displayChangeHexToBin();
                    break;
                case 2:
                    // display hexadecimal --> decimal
                    displayChangeHexToDec();
                    break;

                case 3:
                    // display hexadecimal --> hexadecimal
                    String number = getInputHexa();
                    convertToItSeft(number, "Hexadecimal");
                    break;

            }
        } while (getInputData.inputYesNo());
    }

    private void displayChangeHexToBin() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match input string with regex to see if it is 0->9 and A -> F
            if (number.matches("[0-9A-F]*")) {
                String output = ConvertFromBaseToDec(number, 16);
                output = convertFromDecToBase(output, 2);
                System.out.println(" Hexadecimal after convert to binary: " + output);
                break;
            }
            System.out.println("Number you entered isn't hex number. ");
        }
    }

    private void displayChangeHexToDec() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match input string with regex to see if it is 0->9 and A -> F
            if (number.matches("[0-9A-F]*")) {
                String output = ConvertFromBaseToDec(number, 16);
                System.out.println(" Hexadecimal after convert to decimal: " + output);
                break;
            }
            System.out.println("Number you entered isn't hex number. ");
        }
    }

    private String getInputBinary() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // regex match to see if the input string is all 0 and 1
            if (number.matches("^[01]+$")) {
                return number;
            }
            System.out.println("Number you entered isn't binary number. ");
        }
    }

    private String getInputDecimal() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match the input string with the regex to see if it is 0->9
            if (number.matches("^[0-9]+$")) {
                return number;
            }
            System.out.println("Number you entered isn't decimal number. ");
        }
    }

    private String getInputHexa() {
        while (true) {
            System.out.print("Enter number you want to convert: ");
            String number = getInputData.inputString();
            // match input string with regex to see if it is 0->9 and A -> F
            if (number.matches("[0-9A-F]*")) {
                return number;
            }
            System.out.println("Number you entered isn't hex number. ");
        }
    }

}
