import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class FilteringLauncher {

    public static void main(String[] args) throws Exception {
        String exclusionName = args[0];
        Class<?> exclusionClass = Class.forName(exclusionName);
        Field excludedNames = exclusionClass.getDeclaredField("Excluded");
        String[] excludedValues = (String[]) excludedNames.get(null);
        
        String className = args[1];
        if (Arrays.binarySearch(excludedValues, className) >= 0) {
            System.err.println("\"" + className + "\" is banned from running on this machine");
            return;
        }
        
        Class<?> cls = Class.forName(className);
        Method mainMethod = cls.getMethod("main", String[].class);
        
        String arg = args[2];
        mainMethod.invoke(null, new Object[] {new String[] {arg}});
    }
    
}
