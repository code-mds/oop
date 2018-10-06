package e1;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoxTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void moveFirstTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Integer> to = new Box<>(null, 20);
        from.moveFirstTo(to);

        TestCase.assertEquals(from.getFirst(), null);
        TestCase.assertEquals(to.getFirst(), "Ciao");
    }

    @Test
    public void failMoveFirstTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Integer> to = new Box<>("BUSY", 20);

        exception.expect(BoxRuntimeException.class);
        from.moveFirstTo(to);
    }


    @Test
    public void differentTypeMoveFirstTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Double> to = new Box<>(null, 20.2);
        from.moveFirstTo(to);

        TestCase.assertEquals(from.getFirst(), null);
        TestCase.assertEquals(to.getFirst(), "Ciao");
    }

    @Test
    public void moveSecondTo() {
        Box<String, Integer> from = new Box<>("Ciao", 14);
        Box<String, Integer> to = new Box<>("XXX", null);
        from.moveSecondTo(to);

        TestCase.assertEquals(from.getSecond(), null);
        TestCase.assertEquals(to.getSecond(), Integer.valueOf(14));
    }

    @Test
    public void moveItemsFrom() {
        Box<String, Integer> from = new Box<>("CIAO", 14);
        Box<String, Integer> to = new Box<>(null, null);
        to.moveItemsFrom(from);

        TestCase.assertEquals(from.getFirst(), null);
        TestCase.assertEquals(from.getSecond(), null);

        TestCase.assertEquals(to.getFirst(), "CIAO");
        TestCase.assertEquals(to.getSecond(), Integer.valueOf(14));
    }


    @Test
    public void swapFirstWith() {
        Box<String, Integer> b1 = new Box<>("Ciao", 14);
        Box<String, Double> b2 = new Box<>("Casa", 20.2);
        b1.swapFirstWith(b2);

        TestCase.assertEquals(b1.getFirst(), "Casa");
        TestCase.assertEquals(b2.getFirst(), "Ciao");
    }


    @Test
    public void swapSecondWith() {
        Box<String, Double> b1 = new Box<>("Ciao", 14.1);
        Box<Integer, Double> b2 = new Box<>(2221, 20.2);
        b1.swapSecondWith(b2);

        TestCase.assertEquals(b1.getSecond(), Double.valueOf(20.2));
        TestCase.assertEquals(b2.getSecond(), Double.valueOf(14.1));
    }
    @Test
    public void swapItemsWith() {
        Box<String, Double> b1 = new Box<>("Ciao", 14.1);
        Box<String, Double> b2 = new Box<>("Casa", 20.2);
        b1.swapItemsWith(b2);

        TestCase.assertEquals(b1.getFirst(), "Casa");
        TestCase.assertEquals(b1.getSecond(), Double.valueOf(20.2));

        TestCase.assertEquals(b2.getFirst(), "Ciao");
        TestCase.assertEquals(b2.getSecond(), Double.valueOf(14.1));
    }

    @Test
    public void moveFirstToSecond() {
        Box<String, String> box = new Box<>("Ciao", null);
        Box.moveFirstToSecond(box);
        TestCase.assertEquals(box.getFirst(), null);
        TestCase.assertEquals(box.getSecond(), "Ciao");
    }

    @Test
    public void swapItems() {
        Box<String, String> box = new Box<>("Ciao", "Pappa");
        Box.swapItems(box);
        TestCase.assertEquals(box.getFirst(), "Pappa");
        TestCase.assertEquals(box.getSecond(), "Ciao");
    }
}