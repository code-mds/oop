package e2;

import e1.Dictionary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.OptionalDouble;

public class TestE2 {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Dictionary<LocalDate> myDic = new Dictionary<>();
        myDic.put("Massimo", LocalDate.parse("19.12.1975", formatter));
        myDic.put("Alessandro", LocalDate.parse("19.12.2001", formatter));
        myDic.put("Barbara", LocalDate.parse("19.10.1970", formatter));
        myDic.put("Alessandra", LocalDate.parse("10.05.1970", formatter));
        myDic.put("Luca", LocalDate.parse("01.05.1990", formatter));
        printCollection(myDic);

        LocalDate old = myDic.remove("Alessandro");
        printCollection(myDic);

        myDic.get("BBB");
        myDic.get("Luca");

        ArrayList a = new ArrayList();
        a.stream();
    }

    private static void printCollection(Dictionary<LocalDate> myDic) {
        for (Dictionary.Association a : myDic)
            System.out.println(a);

        System.out.println();
    }
}
