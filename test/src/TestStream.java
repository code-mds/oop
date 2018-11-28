import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TestStream {
    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        int[] indexes = IntStream.rangeClosed(0, list.size())
                .filter((i) -> i%3 == 0 || i == list.size() )
                .toArray();

        printIndexes(indexes);

        ArrayList<List<Integer>> listOfList = IntStream.rangeClosed(1, indexes.length-1)
                .collect(
                        // supplier
                        ArrayList::new, // oppure: () -> new ArrayList<>()
                        // accumulator
                        (lol, idx) -> lol.add(list.subList(indexes[idx-1], indexes[idx])),
                        // combiner
                        ArrayList::addAll  // oppure: (lolTot, lol) -> lolTot.addAll(lol)
                );

        printListOfList(listOfList);
    }

    private static void printListOfList(ArrayList<List<Integer>> listOfList) {
        System.out.println("list of list:");
        for (List<Integer> ls: listOfList)
        {
            for (Integer i : ls )
                System.out.print(i + " ");

            System.out.println();
        }
    }

    private static void printIndexes(int[] indexes) {
        System.out.println("indexes:");
        for (Integer i : indexes )
            System.out.print(i + " ");
        System.out.println();
    }

}
