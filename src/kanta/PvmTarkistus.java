package kanta;


/**
 * Luokka päivämäärän tarkistamiseksi (muokattu Vesan Lappalaisen hetu-esimerkistä)
 * @author meikk
 * @version 19.10.2021
 *
 */
public class PvmTarkistus  {
    /** Pvm:ään kelpaavat tarkistusmerkit järjestyksessä */
    //                                           
    public static final String TARKISTUSMERKIT = "0123456789";

    
    /**
     * Palauttaa mikä olisi hetun tarkistumerkki. Tuotava parametrinä
     * laillista muotoa oleva hetu, josta mahdollisesti tarkistumerkki 
     * puuttuu.
     * @param hetu tutkittava hetu
     * @return hetun tarkistusmerkki
     * @example
     * <pre name="test">
     *    hetunTarkistusMerkki("121212-222")    === 'N';
     *    hetunTarkistusMerkki("121212-222S")   === 'N';
     *    hetunTarkistusMerkki("121212-222N")   === 'N';
     *    hetunTarkistusMerkki("121212-231Y")   === 'Y';
     *    hetunTarkistusMerkki("311212-2317")   === '7';
     *    hetunTarkistusMerkki("311212-2317XY") === '7'; // vaikka on liikaa merkkejä
     *    hetunTarkistusMerkki("999999-9999XY") === 'F'; // vaikka on pvm väärin
     *    hetunTarkistusMerkki("12121A-222S")   === 'N'; #THROWS NumberFormatException
     *    hetunTarkistusMerkki("12121A-22")     === 'N'; #THROWS StringIndexOutOfBoundsException
     *    hetunTarkistusMerkki("121")           === 'N'; #THROWS StringIndexOutOfBoundsException
     * </pre>
     */
    public static char hetunTarkistusMerkki(String hetu) {
        String pvm = hetu.substring(0,6);
        String yksilo = hetu.substring(7,10);
        long luku = Long.parseLong(pvm+yksilo);
        int jakojaannos = (int)(luku % 31L);
        return TARKISTUSMERKIT.charAt(jakojaannos);
    }
    
   
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala,yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala,yla]
     */
    public static int rand(int ala, int yla) {
      double n = (yla-ala)*Math.random() + ala;
      return (int)Math.round(n);
    }

    
    /**
     * Arvotaan satunnainen henkilötunnus, joka täyttää hetun ehdot    
     * @return satunnainen laillinen henkilötunnus
     */
    public static String arvoPvm() {
        String apuPvm = String.format("%02d",rand(1,28))   +
        String.format("%02d",rand(1,12))   +
        String.format("%02d",rand(18,21));  
    
        return apuPvm; // + hetunTarkistusMerkki(apuhetu);        
    }
    
}