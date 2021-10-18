/**
 * 
 */
package kirppis;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * tietää tuotteen tiedot (hinta, kunto, myynnin  
 *   aloituspvm...)                                
 * - osaa tarkistaa tietyn kentän tietojen         
 *    oikeellisuuden                               
 * - osaa muuttaa 4|kukkaruukku|...| -merkkijonon  
 *   tiedot tuotteen tiedoiksi                     
 * - osaa antaa merkkijonona i:n kentän tiedot     
 * - osaa laittaa merkkijonon i:nneksi kentäksi 
 * @author meikk
 * @version 18.10.2021
 *
 */
public class Tuote {
    
   private int      id = 0; 
   private String   nimi = "";
   private double   hinta = 0;
   private String   kunto = "";
   // private Pvm   myynninAlkuPvm; ??
   private boolean  myyty = false;
   private String   kuvaus = "";
   //private String kategoria; ??
   
    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", id) + " " + nimi + " " + String.format("%4.2f", hinta) + "€");
        out.print("Tuotteen kunto: " + kunto);
        out.println("Myyty? : " + myyty + " kuvaus: " + kuvaus);
        //
    }
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuote potkuKelkka = new Tuote();
        Tuote kesaMekko = new Tuote();
        
        // potkuKelkka.rekisteroi();
        // kesaMekko.rekisteroi();
        
        potkuKelkka.tulosta(System.out);
        // potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka.tulosta(System.out);
        
        kesaMekko.tulosta(System.out);
        // kesaMekko.taytaTuoteTiedoilla();
        kesaMekko.tulosta(System.out);
    }

}
