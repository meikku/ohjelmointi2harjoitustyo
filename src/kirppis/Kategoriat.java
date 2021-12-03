package kirppis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Pitää yllä kategorioiden rekisteriä, osaa                     
 * lisätä ja poistaa kategorian.                                  
 * Lukee ja kirjoittaa kategoriat tiedostoon.                     
 * Osaa etsiä ja lajitella.        
 * @author meikk
 * @version 3.11.2021
 *
 */
public class Kategoriat {

    private Collection<Kategoria> alkiot = new ArrayList<Kategoria>();  
    private String tiedostonPerusNimi = "kategoriat";
    
    /**
     * Alustaminen
     */
    public Kategoriat() {
        //
    }
    
    /**
     * Lisätään kategoria listalle
     * @param kat kategoria joka lisätään
     */
    public void lisaa(Kategoria kat) {
        alkiot.add(kat);
    }
    
    /**
     * Tulostetaan kaikki kategoriat
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        for (Kategoria kat : alkiot) {
            kat.tulosta(os);
        }
    }
    
    /**
     * Palauttaa viitteen indeksin i kategoriaan
     * @param tunnusNro monesko kategoria halutaan
     * @return viite kategoriaan jonka indeksi on i
     */
    public Kategoria anna(int tunnusNro) {
        for (Kategoria kat : alkiot) {
            if (tunnusNro == kat.getTunnusNro()) return kat;
        }
        return null;
    }
    
    /**
     * Palauttaa listan kaikista kategorioista
     * @return lista kategorioista
     */
    public Collection<Kategoria> annaKaikki() {
        return alkiot;
    }
    
    /**
     * @return alkioiden lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * @param tied hakemiston nimi
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna(String tied) throws SailoException {
        File ftied = new File(tied + "/kategoriat.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (Kategoria kat : alkiot) {
                fo.println(kat);
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
    }
    
    /**
     * Lukee kategoriat tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos tiedoston lukeminen ei onnistu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        // setTiedostonPerusNimi(hakemisto);
        String nimi = hakemisto + "/kategoriat.dat";
        File ftied = new File(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while ( fi.hasNext() ) {
                String s = "";
                s = fi.nextLine();
                Kategoria kat = new Kategoria();
                kat.parse(s); 
                lisaa(kat);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
    }
    
  
    
 
    /**
     * Testataan kategoriat-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kategoriat kategoriat = new Kategoriat();
        
        try {
            kategoriat.lueTiedostosta("kirppis");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }
        
        Kategoria kulkuValine1 = new Kategoria();
        Kategoria kulkuValine2 = new Kategoria();
        Kategoria kulkuValine3 = new Kategoria();
        Kategoria kulkuValine4 = new Kategoria();
        
        kulkuValine1.rekisteroi();
        kulkuValine1.taytaKatTiedoilla();
        
        kulkuValine2.rekisteroi();
        kulkuValine2.taytaKatTiedoilla();
        
        kulkuValine3.rekisteroi();
        kulkuValine3.taytaKatTiedoilla();
        
        kulkuValine4.rekisteroi();
        kulkuValine4.taytaKatTiedoilla();
          
        kategoriat.lisaa(kulkuValine1);
        kategoriat.lisaa(kulkuValine2);
        kategoriat.lisaa(kulkuValine3);
        kategoriat.lisaa(kulkuValine4);
        
        try {
            kategoriat.tallenna("kirppis");
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
        System.out.println("======= Kategoriat testi =======");
    
        kategoriat.tulosta(System.out);
        

    }

}
