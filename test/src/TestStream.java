import Person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String args[]) {
        testSharedMutation();
        testListOfList();
    }

    private static void testSharedMutation() {
        // infinite stream
        Stream.iterate(100, e -> e+1)    // unbounded, lazy
                .limit(10)                     // bounded, lazy
                .forEach(System.out::println); // terminal op (not lazy), 100 .. 109

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

        Collection collection = numbers;
        for (Object o : collection)
            System.out.print(o + " ");
        System.out.println();

        int sum = numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e*2)
                .sum();
        System.out.println("sum of even number square:" + sum);

        // WRONG, SHARED MUTATION
        List<Integer> doubleOfEven = new ArrayList<>();

        numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e*2)
                .forEach(e-> doubleOfEven.add(e)); // VERY BAD, doubleOfEven shared variable

        // OK, THREAD SAFE !!
        List<Integer> doubleOfEvenList = numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(Collectors.toList()); // 4, 8, 4, 8
        System.out.println(doubleOfEvenList);

        Set<Integer> doubleOfEvenSet = numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(Collectors.toSet());  // 4, 8
        System.out.println(doubleOfEvenSet);

        Person[] persons = { new Person("Massimo", 43), new Person("Mirella", 40), new Person("Mirella", 12) };
        Map<String, Person> personMap = Stream.of(persons)
                .collect(Collectors.toMap(
                        p -> p.getName() + " " + p.getAge(),  // Key (we have 2 Mirella, it throws exception without age)
                        p -> p        // Value
                ));
        System.out.println(personMap);

        // grouping by name -> list of person
        Map<String, List<Person>> group = Stream.of(persons)
                .collect(Collectors.groupingBy(Person::getName));
        System.out.println(group);

        // grouping by name -> list of ages
        Map<String, List<Integer>> groupByNameToAges = Stream.of(persons)
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.mapping(Person::getAge, Collectors.toList()) ));
        System.out.println(groupByNameToAges);

    }

    private static void testListOfList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

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
