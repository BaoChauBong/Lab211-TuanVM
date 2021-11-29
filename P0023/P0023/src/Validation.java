
import entity.Fruit;
import java.util.ArrayList;

public class Validation {

    boolean isValidID(String id) {
        // access each character of the string id
        for (int i = 0; i < id.length(); i++) {
            // check each character in string 
            if ((id.charAt(i) < '0' || id.charAt(i) > '9') && (id.charAt(i) < 'a' || id.charAt(i) > 'z')
                    && (id.charAt(i) < 'A' || id.charAt(i) > 'Z')) {
                return true;
            }
        }
        return false;
    }

    boolean isDuplicateId(ArrayList<Fruit> listFruits, String id) {
        // loop to access each element of the list
        for (int i = 0; i < listFruits.size(); i++) {
            // Compare imported id with existing id 
            if (listFruits.get(i).getFruitId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
