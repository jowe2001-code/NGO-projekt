package ngo;

import oru.inf.InfDB;
import oru.inf.InfException;

public class Start {
    public static void main(String[] args) {
        try {
            InfDB db = new InfDB("SDGSweden", "3306", 
                                 "dbAdmin2024", "dbAdmin2024PW");
            System.out.println("Uppkoppling lyckades!");
        } catch (InfException ex) {
            System.out.println("Fel: " + ex.getMessage());
        }
    }
}