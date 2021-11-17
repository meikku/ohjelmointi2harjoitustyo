package kirppis;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Tietää kategorian kentät.                                          
 * Osaa tarkistaa kategorian tietyn kentän                           
 * oikeellisuuden.                                                    
 * Osaa muuttaa 2|sisustustavarat|verhot -merkki-                    
 * jonon tiedoiksi.                                                   
 * Osaa antaa merkkijonona i:n kentän tiedot.                         
 * Osaa laittaa merkkijonon i:nneksi kentäksi. 
 * @author meikk
 * @version 2.11.2021
 */
public class Kategoria {
    
    private int tunnusNro;
    private String nimi = "";
    private String kuvaus = "";

    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan kategoria. Toistaiseksi tyhjä.
     */
    public Kategoria() {
        // vielä tyhjä
    }
    
   
    
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Kategoria:  " + nimi);
        out.println("Kuvaus: " + kuvaus);
    }
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    /**
     * Antaa kategorialle seuraavan rekisterinumeron.
     * @return kategorian uusi tunnusnumero
     * @example
     * <pre name="test">
     * Kategoria huoneKalu = new Kategoria();
     * huoneKalu.getTunnusNro() === 0;
     * huoneKalu.rekisteroi();
     * Kategoria lastenTarvikkeet = new Kategoria();
     * lastenTarvikkeet.getTunnusNro() === 0;
     * lastenTarvikkeet.rekisteroi();
     * int n1 = huoneKalu.getTunnusNro();
     * int n2 = lastenTarvikkeet.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa kategorian tunnusnumeron
     * @return kategorian tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Palauttaa kategorian nimen
     * @return kategorian nimen 
     */
    public String getNimi() {
        return nimi;
    }
    

    
    /**
     * Arvotaan satunnainen kokonaisluku välille (ala, yla)
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala, yla}
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /**
     *  Täytetään kategorian tiedot
     */
    public void taytaKatTiedoilla() {
        nimi = "kulkuvälineet " + rand(1000, 9999);
        kuvaus = "kaikki liikkumista helpottava";
    }   
    
    
    
    /**
     * Testiohjelma kategorialle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kategoria kat = new Kategoria();
        kat.taytaKatTiedoilla();
        kat.tulosta(System.out);
        
        Kategoria kat2 = new Kategoria();
        kat2.taytaKatTiedoilla();
        kat2.tulosta(System.out);
    }
}
