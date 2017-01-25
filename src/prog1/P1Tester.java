import data_structures.ArrayLinearList;
import data_structures.LinearListADT;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 1/23/17.
 */
public class P1Tester {
    private static LinearListADT<String> list = new ArrayLinearList<>();

    public static void main(String[] args) {
        // Add
        list.addLast("World");
        list.addFirst("Hello ");
        System.out.println(list.size());
        list.remove("Hello ");
        System.out.println(list.size());
    }

    // Possibly implement Unit Tests
    // - Remove from Empty Array, etc.
}
