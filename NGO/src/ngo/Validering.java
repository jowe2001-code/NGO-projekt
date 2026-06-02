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
    public static boolean arGiltigtDatum(String datum) 
    {
        // Kontrollera att formatet är YYYY-MM-DD med regular expression
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(regex, datum))    
        {
            return false;
        }        
        try 
        {
            // Försök att tolka datumet som ett riktigt datum
            java.time.LocalDate.parse(datum);
            return true;
        }
        catch (java.time.format.DateTimeParseException e) 
        {
            return false;
        }
    }
    
    /**
    * Kontrollerar att ett namn bara innehåller bokstäver och mellanslag
    * @param namn namnet som ska kontrolleras
    * @return true om namnet är giltigt
    */
    public static boolean arGiltigtNamn(String namn) 
    {
        // Tillåt bokstäver (inkl. åäö), mellanslag och bindestreck. Minst en bokstav.
        String regex = "^[a-zA-ZåäöÅÄÖ]+([ -][a-zA-ZåäöÅÄÖ]+)*$";
        return Pattern.matches(regex, namn);
    }

    /**
     * Gör första bokstaven stor och resten små i ett namn
     * Hanterar även dubbelnamn med mellanslag
     * @param namn namnet som ska formateras
     * @return namnet med stor första bokstav
     */
    public static String storForstaBokstav(String namn) {
        String[] delar = namn.trim().split(" ");
        StringBuilder resultat = new StringBuilder();
    
        for (int i = 0; i < delar.length; i++) 
        {
            if (delar[i].length() > 0) 
            {
                // Stor första bokstav + resten i små bokstäver
                String del = delar[i].substring(0, 1).toUpperCase() 
                    + delar[i].substring(1).toLowerCase();
                resultat.append(del);
                // Lägg till mellanslag mellan namn-delar
                if (i < delar.length - 1) 
                {
                    resultat.append(" ");
                }
            }
        }
        return resultat.toString();
    }

    /**
     * Genererar ett lösenord på formen "password" + 3 slumpmässiga tecken
     * @return ett genererat lösenord
     */
    public static String genereraLosenord() 
    {
        String tecken = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder losenord = new StringBuilder("password");
        java.util.Random random = new java.util.Random();
    
        // Lägg till 3 slumpmässiga tecken
        for (int i = 0; i < 3; i++) 
        {
            int index = random.nextInt(tecken.length());
            losenord.append(tecken.charAt(index));
        }
    
        return losenord.toString();
    }   
    
    /**
     * Kontrollerar att ett telefonnummer bara innehåller siffror och bindestreck
     * @param telefon telefonnumret som ska kontrolleras
     * @return true om telefonnumret är giltigt
     */
    public static boolean arGiltigtTelefon(String telefon) 
    {
        // Tillåt siffror, bindestreck och plus-tecken (för landskod)
        String regex = "^[0-9+-]+$";
        return Pattern.matches(regex, telefon);
    }

    /**
     * Skapar en e-postadress på formen fornamn.efternamn@example.com
     * Tar bort åäö och gör om till gemener
     * @param fornamn förnamnet
     * @param efternamn efternamnet
     * @return en genererad e-postadress
     */
    public static String skapaEpost(String fornamn, String efternamn) 
    {
        // Gör om till gemener och ersätt svenska tecken
        String fnamn = fornamn.toLowerCase()
            .replace("å", "a").replace("ä", "a").replace("ö", "o")
            .replace(" ", "");
        String enamn = efternamn.toLowerCase()
            .replace("å", "a").replace("ä", "a").replace("ö", "o")
            .replace(" ", "");
    
        return fnamn + "." + enamn + "@example.com";
    }

    /**
     * Kontrollerar att en sträng bara innehåller siffror
     * @param text strängen som ska kontrolleras
     * @return true om strängen bara innehåller siffror
     */
    public static boolean arEnbartSiffror(String text) 
    {
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, text);
    }    
}
