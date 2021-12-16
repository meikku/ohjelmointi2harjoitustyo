/**
 * 
 */
package kirppis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
public class Tuotteet implements Cloneable{
    
    private static final int MAX_TUOTTEITA = 5;
    private int lkm = 0;
    private Tuote[] alkiot;
    private boolean muutettu = false;
    
    /**
     * Luodaan alustava taulukko
     */
    public Tuotteet() {
        alkiot = new Tuote[MAX_TUOTTEITA];
    }
    
    
    /**
     * Lisätään tuote luetteloon
     * @param tuote tuote joka lisätään
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
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
     * tuotteet.anna(150) === potkuKelkka; #THROWS IndexOutOfBoundsException
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 4;
     * tuotteet.lisaa(potkuKelkka); tuotteet.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Tuote tuote) {
        if ( lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 10);
        
        this.alkiot[this.lkm] = tuote;
        this.lkm++;
        muutettu = true;
    }
    
    /**
     * @param tuote tuote joka korvataan tai joka lisätään
     */
    public void korvaaTaiLisaa(Tuote tuote) {
        int id = tuote.getTunnusNro();
        for (int i= 0; i <lkm; i++) {
            if (alkiot[i].getTunnusNro() == id) {
                alkiot[i] = tuote;
                muutettu = true;
                return;
            }
        }
        lisaa(tuote);
    }
    
    /**
     * Poistetaan annettu tuote taulukosta
     * @param id poistettavan tuotteen tunnusnumero
     * @example
     * <pre name="test">
     *  Tuotteet tuotteet = new Tuotteet();
     *  Tuote mopo = new Tuote(), mopo2 = new Tuote(), mopo3 = new Tuote();
     *  mopo.rekisteroi(); mopo2.rekisteroi();mopo3.rekisteroi();
     *  tuotteet.lisaa(mopo); tuotteet.lisaa(mopo2); tuotteet.lisaa(mopo3);
     *  tuotteet.getLkm() === 3;
     *  tuotteet.poista(1);
     *  tuotteet.getLkm() === 2;
     * </pre>
     */
    public void poista(int id) {
        if (id < 0) return;
        int j = 0;
            for (int i = 0; i < lkm; i++, j++) {
                if (alkiot[i].getTunnusNro() == id) {
                    muutettu = true;
                    j++;
                }
                alkiot[i] = alkiot[j]; 
            }
            lkm--;
            alkiot[lkm] = null;
            return;
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
     * </pre>
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
     * @param tied hakemiston nimi johon tallennetaan
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna(String tied) throws SailoException {
        if ( !muutettu ) return;
        
        File ftied = new File(tied + "/tuotteet.dat");
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
     * @param hakemisto tiedoston hakemisto TODO muuta tämä
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/tuotteet.dat";
        File ftied = new File(nimi); 
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine();
                Tuote tuote = new Tuote();
                tuote.parse(s); // voisi palauttaa jotakin?
                lisaa(tuote);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
        
        try {
            tuotteet.lueTiedostosta("kirppis");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Tuote potkuKelkka = new Tuote();
        Tuote potkuKelkka2 = new Tuote();
        
        potkuKelkka.rekisteroi();
        potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka2.rekisteroi();
        potkuKelkka2.taytaTuoteTiedoilla();
        

        tuotteet.lisaa(potkuKelkka);
        tuotteet.lisaa(potkuKelkka2);
        
        
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
