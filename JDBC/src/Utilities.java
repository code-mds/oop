public class Utilities {

    public static final String HSQLDB = "jdbc:hsqldb:file:";
    public static final String Path = "C:/Users/raffaello/SUPSI/2016-2017-SA/C2044/db/cars";
    public static final String Shutdown = "shutdown=true";
    public static final String ConnectString = HSQLDB + Path + ";" + Shutdown;
    
    public static String toSQLString(String s) {
        return "'" + s.replace("'", "''") + "'";
    }
    
}
