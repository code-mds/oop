package e4;

public class Utilities {
    static final String path = "..\\db\\e4";
    static final String shutdown = "shutdown=true";

    public static String getConnString(){
        String connectionString = String.format("jdbc:hsqldb:file:%s;%s", path, shutdown);
        return connectionString;
    }

}
