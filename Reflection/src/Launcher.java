import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Launcher {

    public static void main(String[] args) throws Exception {
        String className = args[0];
        Class<?> cls = Class.forName(className);

        Class<?> param = String[].class;
        Method mainMethod = cls.getMethod("main", param);
        cls.getField("");

        // il primo parametro e' il target, siccome main e' statico possiamo passare null
        mainMethod.invoke(null, new Object[] {new String[] { args[1] } });

        mainMethod.invoke(null, (Object) new String[] { args[1] } );

        // questa versione non funziona: IllegalArgumentException: argument type mismatch
        //mainMethod.invoke(null, new String[] { args[1] } );
    }



}
