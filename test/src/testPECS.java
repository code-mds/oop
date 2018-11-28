import java.util.ArrayList;
import java.util.List;

class A { }
class B extends A { }
class C extends B { }

// PECS ->  Producer "extends" (get), Consumer "super" (set, add)
public class testPECS {
    public static void testList() {
        List<? super B> listA = new ArrayList<A>();  // OK A is super of B
        List<? super B> listB = new ArrayList<B>();  // OK B is super of B
        // List<? super B> listC = new ArrayList<C>();  // KO A is super of B

        // list1.add(new A()); // KO
        listA.add(new B()); // OK
        listA.add(new C()); // OK

        List<? super A> list2 = new ArrayList<A>();  // OK A is super of A
        list2.add(new A()); // OK
        list2.add(new B()); // OK
        list2.add(new C()); // OK

        List<? extends B> list3 = new ArrayList<B>();  // OK B extends B
        // list3.add(new A()); // KO
        // list3.add(new B()); // KO
        // list3.add(new C()); // KO

        List<? extends A> list4 = new ArrayList<B>();  // OK A extends B
        // list4.add(new A()); // KO
        // list4.add(new B()); // KO
    }

    // public static <E> void addToList1(E element, List<? extends E> list) {
    // non compila: Non posso eseguire "add" su una wildcard con extends
    //  list.add(element); }

    // questi due metodi sono equivalenti
    public static <E> void addToListSuperTypes(E element, List<? super E> list) { list.add(element); }
    public static <T extends E, E> void addToListSubTypes(T element, List<E> list) { list.add(element); }


    public static <E> void addToMatrix(List<E> list, List<? super List<E>> matrix) {
        matrix.add(list);
    }

    public static <E> void addToMatrix2(List<E> list, List<List<? super E>> matrix) {
        matrix.add(list);
    }

    public static void testMatrix() {
        List<A> listA = new ArrayList<>();
        addToListSuperTypes(new A(), listA); // OK in questo caso E e' A
        addToListSuperTypes(new B(), listA); // OK in questo caso E e' B
        addToListSubTypes(new A(), listA); // OK
        addToListSubTypes(new B(), listA); // OK

        List<B> listB = new ArrayList<>();
        addToListSubTypes(new B(), listB); // OK listB.add(new B());
        addToListSuperTypes(new B(), listB); // OK listB.add(new B());

        List<List<A>> matrix_A = new ArrayList<>();
        addToMatrix(listA, matrix_A);
        // addToMatrix2(listA, matrix_A); KO , E is A

        List<List<? extends A>> matrix_AB = new ArrayList<>();
        addToMatrix(listA, matrix_AB);  // OK matrix_AB.add(listA);
        addToMatrix(listB, matrix_AB);  // OK matrix_AB.add(listB);
    }

    public static void main(String args[]) {
        testList();
        testMatrix();
    }
}