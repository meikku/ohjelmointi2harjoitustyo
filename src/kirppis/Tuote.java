/**
 * 
 */
package kirppis;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static kanta.PvmTarkistus.*;

/**
 * Tietää tuotteen tiedot (hinta, kunto, myynnin  
 *   aloituspvm...).                                
 * Osaa tarkistaa tietyn kentän tietojen oikeellisuuden.                               
 * Osaa muuttaa 4|kukkaruukku|...| -merkkijonon  
 *   tiedot tuotteen tiedoiksi.                     
 * Osaa antaa merkkijonona i:n kentän tiedot.     
 * Osaa laittaa merkkijonon i:nneksi kentäksi. 
 * @author meikk
 * @version 18.10.2021
 *
 */
public class Tuote {

    private int tunnusNro = 0;
    private String nimi = "";
    private double hinta = 0;
    private String kunto = "";
   
    private String myynninAlkuPvm = "000000";
    boolean myyty = false;
    private String kuvaus = "";

    private static int seuraavaNro = 1;

    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + nimi + " "
                + String.format("%4.2f", hinta) + "€");
        out.println("Tuotteen kunto: " + kunto);
        out.println("Tuote laitettu myyntiin: " + myynninAlkuPvm);
        out.println("Myyty? : " + myyty + " kuvaus: " + kuvaus);   
    }
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Antaa tuotteelle seuraavan rekisterinumeron.
     * @return tuotteen uusi tunnusnumero
     * @example
     * <pre name="test">
     * Tuote potkuKelkka = new Tuote();
     * potkuKelkka.getTunnusNro() === 0;
     * potkuKelkka.rekisteroi();
     * Tuote kesaMekko = new Tuote();
     * kesaMekko.getTunnusNro() === 0;
     * kesaMekko.rekisteroi();
     * int n1 = potkuKelkka.getTunnusNro();
     * int n2 = kesaMekko.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa tuotteen tunnusnumeron.
     * @return tuotteen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa, että 
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    public void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    /**
     * Arvotaan satunnainen kokonaisluku välille (ala, yla)
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainne luku väliltä [ala, yla}
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /**
     *  Täytetään tuotteen tiedot
     */
    public void taytaTuoteTiedoilla() {
        nimi = "potkukelkka " + rand(1000, 9999);
        hinta = 40.00;
        kunto = "kelvollinen"; 
        myynninAlkuPvm = arvoPvm();
        myyty = false;
        kuvaus = "punainen, tukeva";    
    }
    
    @Override
    public String toString() {
        return "" + 
                getTunnusNro() + "|" +
                nimi + "|" +
                hinta + "€|" + 
                kunto + "|" +
                myynninAlkuPvm + "|" +
                myyty + "|" + 
                kuvaus;
    }
    
    /**
     * @param rivi josta tuotteen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *  Tuote tuote = new Tuote();
     *  tuote.parse("3  | potkukelkka | 20€");
     *  tuote.getTunnusNro() === 3;
     *  tuote.toString().startsWith("3|potkukelkka|20.0€|") === true; 
     *  tuote.rekisteroi();
     *  int n = tuote.getTunnusNro();
     *  tuote.parse("" + (n+20));
     *  tuote.rekisteroi();
     *  tuote.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        nimi = Mjonot.erota(sb, '|', nimi);
        hinta = Mjonot.erota(sb, '|', hinta);
        kunto = Mjonot.erota(sb, '|', kunto);
        myynninAlkuPvm = Mjonot.erota(sb, '|', myynninAlkuPvm);
        kuvaus = Mjonot.erota(sb, '|', kuvaus);
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuote potkuKelkka = new Tuote();
        Tuote potkuKelkka2 = new Tuote();

        potkuKelkka.rekisteroi();
        potkuKelkka2.rekisteroi();

        potkuKelkka.tulosta(System.out);
        potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka.tulosta(System.out);

        potkuKelkka2.tulosta(System.out);
        potkuKelkka2.taytaTuoteTiedoilla();
        potkuKelkka2.tulosta(System.out);
    }


    /**
     * @return tuotteen nimi
     */
    public String getNimi() {
        
        return nimi;
    }

}
