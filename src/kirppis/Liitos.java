package kirppis;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Yhdistää tuotteen oikeaan kategoriaan. 
 * Sama tuote voi kuulua myös useampaan kategoriaan.
 * @author meikk
 * @version 4.11.2021
 *
 */
public class Liitos {
    
    private int tuoteNro;
    private int katNro;
    private int yhdysNro;
    
    
    private static int seuraavaNro = 1;
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", yhdysNro) + ": tuoteNro " + String.format("%03d", tuoteNro)
            + ", kategoriaNro " + String.format("%03d", katNro));
    }
    
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Lisätään uuden tuotteen & kategorian tunnusnumerot
     * @param tuote tuote joka lisätään
     * @param kat kategoria johon tuote kuuluu
     */
    public void rekisteroi(int tuote, int kat) {
        this.yhdysNro = seuraavaNro;
        tuoteNro = tuote;
        katNro = kat;
        seuraavaNro++; 
    }
    
    
    /**
     * Palauttaa tuotteen tunnusNron
     * @return tuotteen tunnusNro
     */
    public int getTuoteNro() {
        return tuoteNro;
    }
    
    /**
     * Palauttaa kategorian tunnusnumeron
     * @return kategorian tunnusNro
     */
    public int getKatNro() {
        return katNro;
    }
    
    /**
     * Palauttaa yhdysnumeron, joka kertoo mihin kohtaan taulukkoa 
     *      on talletettu tuote ja sitä vastaava kategoria
     * @return yhdysnumeron
     */
    public int getyhdysNro() {
        return yhdysNro;
    }
     
    
    /**
     * Testataan tuote-kategorialuokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }
}
