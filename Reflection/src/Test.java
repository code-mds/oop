import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        bitCount();
        testF();
        testDump();
    }

    private static void testDump() {
        Class<?> classes[] = {void.class, int.class, Integer.class};
        for (Class<?> c : classes)
            dump(c);

        Class<?> c = void.class;
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        int i = 1;
    }

    private static void testF() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = Class.forName("Test");
        Constructor<?> ctor = cls.getConstructor();
        Object o = ctor.newInstance();

        Method m = cls.getMethod("f", int.class);
        m.invoke(o, new Object [] { 1 });
    }

    private static void bitCount() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class c = Integer.class;
        Method mbit = c.getMethod("bitCount", int.class);
        int res = (int)mbit.invoke(null, 3);
        System.out.println("bits: " + res);
    }

    public void f(int c) {
        System.out.printf("f()");
    }


    private static void dump(Class<?> c) {
        System.out.printf("\n name: %s , typeName: %s, super: %s", c.getName(), c.getTypeName(), c.getSuperclass());



        System.out.printf("\n\tCONSTRUCTORS (%d): ", c.getConstructors().length);
        for (Object f : c.getConstructors())
            System.out.printf("%s ", f);

        System.out.printf("\n\tINTERFACES (%d): ", c.getInterfaces().length);
        for (Object f : c.getInterfaces())
            System.out.printf("%s ", f);



        System.out.printf("\n\tANNOTATIONS (%d): ", c.getAnnotations().length);
        for (Object f : c.getAnnotations())
            System.out.printf("%s ", f);

        System.out.printf("\n\tFIELDS (%d): ", c.getFields().length);
        for (Object f : c.getFields())
            System.out.printf("%s ", f);

        System.out.printf("\n\tDECLARED FIELDS (%d): ", c.getDeclaredFields().length);
        for (Object f : c.getDeclaredFields())
            System.out.printf("%s ", f);

        System.out.printf("\n\tMETHODS (%d): ", c.getMethods().length);
        for (Object f : c.getMethods())
            System.out.printf("%s ", f);

        System.out.printf("\n\tDECLARED METHODS (%d): ", c.getDeclaredMethods().length);
        for (Object f : c.getDeclaredMethods())
            System.out.printf("%s ", f);
    }
}