package e1;

import org.junit.jupiter.api.Test;

class BoxTest {
    @Test
    void moveFirstTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Integer> to = new Box<>(null, 20);
        from.moveFirstTo(to);
        System.out.println("from: " + from);
        System.out.println("to: " + to);
    }

    @org.junit.jupiter.api.Test
    void differentTypeMoveFirstTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Double> to = new Box<>(null, 20.2);
        from.moveFirstTo(to);
        System.out.println("from: " + from);
        System.out.println("to: " + to);
    }

    @org.junit.jupiter.api.Test
    void moveSecondTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Integer> to = new Box<>("XXX", null);
        from.moveSecondTo(to);
        System.out.println("from: " + from);
        System.out.println("to: " + to);
    }

    @org.junit.jupiter.api.Test
    void moveItemsFrom() {
        Box<String, Integer> from = new Box<>("CIAO", 14);
        Box<String, Integer> to = new Box<>(null, null);
        to.moveItemsFrom(from);
        System.out.println("from: " + from);
        System.out.println("to: " + to);
    }

    @org.junit.jupiter.api.Test
    void swapFirstWith() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Double> to = new Box<>(null, 20.2);
        from.swapFirstWith(to);
        System.out.println("from: " + from);
        System.out.println("to: " + to);
    }

    @org.junit.jupiter.api.Test
    void moveFirstToSecond() {
        Box<String, String> box = new Box<>("Ciao", null);
        Box.moveFirstToSecond(box);
        System.out.println("box: " + box);
    }

}