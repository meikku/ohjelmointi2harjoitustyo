/**
 * 
 */
package kirppis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Pitää yllä tuoterekisteriä, osaa lisätä ja    
 * poistaa tuotteen                             
 * Lukee ja kirjoittaa tuotteiden tiedostoon    
 * Osaa etsiä ja lajitella tuotteita       
 * 
 * @author meikk
 * @version 19.10.2021
 *
 */
public class Tuotteet {
    
    private static final int MAX_TUOTTEITA = 5;
    private int lkm = 0;
    private Tuote[] alkiot;
    
    /**
     * Luodaan alustava taulukko
     */
    public Tuotteet() {
        alkiot = new Tuote[MAX_TUOTTEITA];
    }
    
    
    /**
     * Lisätään tuote luetteloon
     * @param tuote lisättävä tuote
     * @throws SailoException poikkeus jos väärä alkio
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Tuotteet tuotteet = new Tuotteet();
     * Tuote potkuKelkka = new Tuote();
     * Tuote potkuKelkka2 = new Tuote();
     * tuotteet.getLkm() === 0;
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 1;
     * tuotteet.lisaa(potkuKelkka2); tuotteet.getLkm() === 2;
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 3;
     * tuotteet.anna(0) === potkuKelkka;
     * tuotteet.anna(1) === potkuKelkka2;
     * tuotteet.anna(0) === potkuKelkka;
     * tuotteet.anna(1) == potkuKelkka === false;
     * tuotteet.anna(1) == potkuKelkka2 === true;
     * tuotteet.anna(3) === potkuKelkka; #THROWS IndexOutOfBoundException
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 4;
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 5;
     * tuotteet.lisaa(potkuKelkka); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Tuote tuote) throws SailoException {
        if ( lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        this.alkiot[this.lkm] = tuote;
        this.lkm++;
    }
    
    /**
     * Palauttaa viitteen indeksin i tuotteeseen 
     * @param i monennenko tuotteen viite halutaan
     * @return viite tuotteeseen, joka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Tuote anna(int i) {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    /**
     * Palauttaa tuotteiden lukumäärän
     * @return tuotteiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Tallentaa tuotteet tiedostoon
     * Tiedoston muoto: 
     * <pre>
     * 2|potkukelkka|25€|hyväkuntoinen|23.05.2015|myyty|punainen ja sievä|
     * 3|kesämekko|10€|käyttämätön|12.08.2019|ei myyty|sinikeltainen|
     * </pre>
     * @param tiedNimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna(String tiedNimi) throws SailoException {
        File ftied = new File(tiedNimi + "/nimet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Tuote tuote = anna(i);
                fo.println(tuote);
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
                
    }
    
    /**
     * Lukee tuotteet tiedostosta. kesken
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String tiedostonNimi = hakemisto + "/nimet.dat";
        File ftied = new File(tiedostonNimi);
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine();
                Tuote tuote = new Tuote();
                tuote.parse(s); // voisi palauttaa jotakin?
                lisaa(tuote);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
//        } catch ( IOException e ) {
//            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
        
        try {
            tuotteet.lueTiedostosta("kirppis");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }
        
        Tuote potkuKelkka = new Tuote();
        Tuote potkuKelkka2 = new Tuote();
        
        potkuKelkka.rekisteroi();
        potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka2.rekisteroi();
        potkuKelkka2.taytaTuoteTiedoilla();
        
        try {
            tuotteet.lisaa(potkuKelkka);
            tuotteet.lisaa(potkuKelkka2);
            tuotteet.lisaa(potkuKelkka2);
        } catch (SailoException ex) {
            System.err.println("Ei voi lukea: " + ex.getMessage());
        }
        
        try {
            tuotteet.tallenna("kirppis");
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
        System.out.println("============= Tuotteet testi ================");
        
        for (int i = 0; i < tuotteet.getLkm(); i++) {
            Tuote tuote = tuotteet.anna(i);
            System.out.println("Tuote indeksi: " + i);
            tuote.tulosta(System.out);
        }
    }

}