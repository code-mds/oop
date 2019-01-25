import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
        Class<?> classes[] = {void.class, int.class, Integer.class};
        for (Class<?> c : classes)
            dump(c);

        Class<?> c = void.class;
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

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