import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class StoCastico {
    // edges1 ritorna indici degli elementi della List che soddisfano
    // il Predicate pred:
    // Predicate<String> s = n -> n.contains("a");
    // Predicate<String> s = (n) -> {return n.contains("a");};
    // s = Predicate
    // n = parametro
    // n.contains("a"); = funzione di return
    private static <E> int[] edges1(List<E> list, Predicate<E> pred) {
        return IntStream
                .range(0, list.size()-1)
                .filter((x)->pred.test(list.get(x)))
                .toArray();
    }
    // edges2 ritorna indici degli elementi della List che soddisfano
    // il Predicate pred
    // ma alla pos[0] = -1;
    // alla pos[end] = list.size();
    private static <E> int[] edges2(List<E> list, Predicate<E> pred) {
        return IntStream
                .rangeClosed(-1, list.size())
                .filter((x)->x==-1||x==list.size()||pred.test(list.get(x)))
                .toArray();
    }
    // split divide la List iniziale in una List<List> di sottoliste dal primo elemento
    // al primo elemento che soddisfa il Predicate, ecc. fino alla fine.
    public static <E> ArrayList<List<E>> split(List<E> list, Predicate<E> pred){
        int a[] = edges2(list,pred);

        return IntStream.rangeClosed(0, a.length-2)
                .parallel()
                .collect(()->new ArrayList<List<E>>()
                        ,(t,i)-> t.add(list.subList(a[i]+1, a[i+1])),(tTot,t)->tTot.addAll(t));
    }
    public static void main(String[] args) {
        Predicate<String> s = (n) -> {return n.contains("a");};
        List<String> a = new ArrayList<>();
        a.add("c");
        a.add("a");
        a.add("b");
        ArrayList<List<String>> ab = split(a, s);
        System.out.println(ab);
    }
}