public class Person {
    
    private final int id;
    private final String lastNames;
    private final String firstNames;
    
    public Person(int id, String lastNames, String firstNames) {
        this.id = id;
        this.lastNames = lastNames;
        this.firstNames = firstNames;
    }

    public int getId() {
        return id;
    }
    
    public String getLastNames() {
        return lastNames;
    }
    
    public String getFirstNames() {
        return firstNames;
    }

}
