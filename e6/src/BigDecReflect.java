import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BigDecReflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        String input = "123.56";

        Class cls = Class.forName("java.math.BigDecimal");
        Constructor c = cls.getConstructor(String.class);
        Object o = c.newInstance(input);

        Field f = cls.getField("TEN");
        Method m = cls.getMethod("multiply", cls );
        Object ten = f.get(null);
        Object newVal = m.invoke(o, ten);

        System.out.println(newVal);
    }

}
