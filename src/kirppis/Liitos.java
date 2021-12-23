package kirppis;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
     * @example
     * <pre name="test">
     * Liitos liitos = new Liitos();
     * liitos.rekisteroi(4, 6);
     * liitos.getYhdysNro() === 1;
     * liitos.getTuoteNro() === 4;
     * liitos.getKatNro() === 6; 
     * Liitos liitos2 = new Liitos();
     * liitos2.rekisteroi(2, 5);
     * liitos.toString() === "1|4|6";
     * liitos2.toString() === "2|2|5";
     * </pre>
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
    public int getYhdysNro() {
        return yhdysNro;
    }
    
    /**
     * Asetetaan tunnusnro ja varmistetaan että seuraava numero on aina suurempi kuin 
     * suurin tähän mennessä
     * @param nr asetettava tunnusnumero
     */
    public void setYhdysNro(int nr) {
        yhdysNro = nr;
        if (yhdysNro >= seuraavaNro) seuraavaNro = yhdysNro + 1;
    }
    
    @Override
    public String toString() {
        return "" 
                + getYhdysNro() + "|"
                + getTuoteNro() + "|"
                + getKatNro();
    }
    
    /**
     * Parsitaan rivi merkkijonoksi
     * @param rivi rivi josta tiedot luetaan
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setYhdysNro(Mjonot.erota(sb, '|', getYhdysNro()));
        tuoteNro = Mjonot.erota(sb, '|', tuoteNro);
        katNro = Mjonot.erota(sb, '|', katNro);
    }
     
    
    /**
     * Testataan tuote-kategorialuokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }
}
