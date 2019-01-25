import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class FilteringLauncher {

    public static void main(String[] args) throws Exception {

        String exclusionName = args[0];
        Class<?> exclusionClass = Class.forName(exclusionName);
        Field excludedNames = exclusionClass.getDeclaredField("Excluded");

        //excludedNames.setAccessible(true); // posso rendere public un campo private !!!

        String[] excludedValues = (String[]) excludedNames.get(null);
        
        String className = args[1];
        if (Arrays.binarySearch(excludedValues, className) >= 0) {
            System.err.println("\"" + className + "\" is banned from running on this machine");
            return;
        }
        
        Class<?> cls = Class.forName(className);

        //String[] strings = {};
        //Method m = cls.getDeclaredMethod("main", strings.getClass());

        Method mainInt = cls.getMethod("main", int.class);
        //Object[] param = { 3 };
        //mainInt.invoke(null, param);
        mainInt.invoke(null, 3); // equivalente alle 2 righe sopra


        Method mainMethod = cls.getMethod("main", String[].class);

        // questa versione non funziona: IllegalArgumentException: argument type mismatch
        //mainMethod.invoke(null, new String[] { args[1] } );

        //mainMethod.invoke(null, new Object[] {new String[] { args[2] }});
        Object[] methodParams = { new String[] { args[2] } };
        mainMethod.invoke(null, methodParams );


    }
    
}
