/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo;

import java.util.regex.Pattern;

/**
 *
 * @author jowe2
 */
public class Validering {
    
    /**
     * Kontrollerar om ett datum är giltigt och i formatet YYYY-MM-DD
     * @param datum strängen som ska valideras
     * @return true om datumet är giltigt
     */
    public static boolean arGiltigtDatum(String datum) {
        // Kontrollera att formatet är YYYY-MM-DD med regular expression
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(regex, datum)) {
            return false;
        }
        
        try {
            // Försök att tolka datumet som ett riktigt datum
            java.time.LocalDate.parse(datum);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
