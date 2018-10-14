package e2;

import e1.Dictionary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestE2 {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Dictionary<LocalDate> myDic = new Dictionary<>();
        myDic.put("Massimo", LocalDate.parse("19.12.1975", formatter));
        myDic.put("Alessandro", LocalDate.parse("19.12.2001", formatter));
        myDic.put("Barbara", LocalDate.parse("19.10.1970", formatter));
        myDic.put("Alessandra", LocalDate.parse("10.05.1970", formatter));
        myDic.put("Luca", LocalDate.parse("01.05.1990", formatter));

        for (Dictionary.Association a : myDic)
            System.out.println(a);
    }
}
