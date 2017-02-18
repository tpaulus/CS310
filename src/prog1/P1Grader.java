import data_structures.ArrayLinearList;
import data_structures.LinearLinkedList;
import data_structures.LinearListADT;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 2/15/17.
 */
public class P1Grader {
    private LinearListADT<String> list;

    public P1Grader() {
//        list = new ArrayLinearList<>();
        list = new LinearLinkedList<>();
        try {
            for (int i = 0; i < 101; i++)
                list.addFirst("" + (i + 1));
            for (String s : list)
                System.out.print(s + " ");
            System.out.println("\n");
            for (int i = 0; i < 101; i++)
                list.removeFirst();
        } catch (Exception e) {
            System.out.println("Block #1 " + e);
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < 10; i++)
                list.addLast("" + (i + 1));
            for (String s : list)
                System.out.print(s + " ");
            System.out.println();
        } catch (Exception e) {
            System.out.println("Block #2 " + e);
            e.printStackTrace();
        }
        try {
            list.clear();
            System.out.print("Should not print anything before MARK");
            for (String s : list)
                System.out.print(s + " ");
            System.out.println("\n ***** MARK *****");
            list.addLast("X");
            for (int i = 0; i < 10; i++)
                list.insert("" + (i + 1), 1);
            for (String s : list)
                System.out.print(s + " ");
            System.out.println();
            if (list.locate("X") != 11)
                System.out.println("ERROR, -10 location should be 11, but locate returned " + list.locate("X"));
            String tmp = list.remove("X");
            if (tmp.compareTo("X") != 0)
                System.out.println("ERROR, -10 remove should return 11, but remoe returned " + tmp);
            if (list.locate("X") != -1)
                System.out.println("ERROR, -10 found removed item X");
            if (list.contains("X"))
                System.out.println("ERROR -10, found removed item X");
            list.clear();
            for (int i = 0; i < 100000; i++)
                list.insert(i + "", (i + 1));
            System.out.println("Size is " + list.size());
            list.clear();

            for (int i = 0; i < 100; i++)
                list.addFirst("" + (i + 1));
            for (int i = 0; i < 90; i++)
                list.remove(5);
            for (String s : list)
                System.out.print(s + " ");
            System.out.println();
            for (int i = 0; i < 10; i++)
                list.remove(1);
            System.out.print("Should not print anything before MARK, if so -10");
            for (String s : list)
                System.out.print(s + " ");
            System.out.println("\n ***** MARK *****");
        } catch (Exception e) {
            System.out.println("Block #3 " + e);
            e.printStackTrace();
        }

        list.clear();
        list.insert("A", 1);
        list.insert("B", 2);
        list.insert("C", 3);
        list.insert("1", 2);

        final String s = list.get(2);
        if (s.compareTo("1") != 0)
            System.out.println("GET Failed!");
        if (list.removeLast().compareTo("C") != 0)
            System.out.println("Remove Last Failed to retrieve element!");
        if (list.removeLast().compareTo("B") != 0)
            System.out.println("Remove Last Failed to remove removed element!");
    }

    public static void main(String[] args) {
        new P1Grader();
    }
}
