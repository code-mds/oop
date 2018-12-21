import java.lang.reflect.Method;
import java.util.Arrays;

public class LauncherEx {

    public static void main(String[] args) throws Exception {
        System.out.println("LauncherEx");

        String className = args[0];
        Class<?> cls = Class.forName(className);

        Class<?> param = String[].class;
        Method mainMethod = cls.getMethod("main", param);

        Object methodArg = Arrays.copyOfRange(args, 1, args.length);
        //mainMethod.invoke(null, new Object[] {new String[] { args[1] } });
        mainMethod.invoke(null, methodArg);
    }
}
