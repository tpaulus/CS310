import data_structures.ArrayLinearList;
import data_structures.LinearListADT;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 1/23/17.
 */
public class P1Tester {
    public static void main(String[] args) {
        LinearListADT<Integer> list = new ArrayLinearList<Integer>();
        list.insert(1, 1);
        list.insert(2, 2);
        list.insert(3, 2);
        list.insert(4, 2);
        list.insert(5, 5);
        try {
            list.insert(2, 0);  // should throw an exception!
        } catch (RuntimeException e) {
            System.out.println("Exception Thrown - This is Good!");
        }
        try {
            list.insert(77777, 7);  // should throw an exception!
        } catch (RuntimeException e) {
            System.out.println("Exception Thrown - This is Good!");
        }
        list.addFirst(-1);
        list.addLast(-1);
        //Should print -1,1,4,3,2,5,-1
        for (int i : list)
            System.out.println(i);

        list.remove(new Integer(5));
        list.remove(new Integer(10));
        list.remove(3);

        try {
            list.remove(list.size() + 1);
            System.out.println("Exception Not Thrown - BAD!");
        } catch (RuntimeException e) {
            System.out.println("Exception Thrown - This is Good!");
        }

        for (int i : list)
            System.out.println(i);

        list.clear();

        for (int i : list) // should not print anything, nor crash
            System.out.println(i);

        for (int i = 0; i < 100; i++)
            list.insert((i + 1), 1);
        for (int i : list) // should print 100 .. 1
            System.out.print(i + " ");
        System.out.println();
    }

}
