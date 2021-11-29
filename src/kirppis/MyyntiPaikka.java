/**
 * 
 */
package kirppis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Huolehtii Tuotteet ja Kategoriat -luokkien välisestä yhteistyöstä ja välittää näitä tietoja pyydettäessä    
 * Lukee ja kirjoittaa myyntipaikan tiedostoon pyytämällä apua avustajiltaan            
 * @author meikk
 * @version 21.10.2021
 *
 */
public class MyyntiPaikka {
    private final Tuotteet tuotteet = new Tuotteet();
    private final Kategoriat kategoriat = new Kategoriat();
    private final Liitokset liitokset = new Liitokset();
    
    /**
     * Lisätään uusi jäsen
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     */
    public void lisaa(Tuote tuote) throws SailoException{
       tuotteet.lisaa(tuote);
    }
    
    
    /**
     * Lisätään uusi kategoria
     * @param kat lisättävä kategoria
     */
    public void lisaa(Kategoria kat) {
        kategoriat.lisaa(kat);
    }
    
    /**
     * Lisätään uusi liitos
     * @param lts liitos
     */
    public void lisaa(Liitos lts) {
        liitokset.lisaa(lts);
    }
    
    /**
     * @return tuotteiden lukumäärä
     */
    public int getTuotteet() {
       return this.tuotteet.getLkm(); 
    }
    
    /**
     * @return kategorioiden lukumäärä
     */
    public int getKategoriat() {
        return this.kategoriat.getLkm();
    }
    
    /**
     * Antaa myyntipaikan i:nnen tuotteen
     * @param i monesko tuote (alkaa 0:sta)
     * @return tuote paikasta i
     */
    public Tuote annaTuote(int i) {
        return tuotteet.anna(i);
    }
    

    public Kategoria annaKategoria(int i) {
        return kategoriat.anna(i);
    }
    

    /**
     * Etsitään tuotteeseen liittyviä kategorioita liitokset-luokasta
     * @param tuote tuote jonka kategorioita haetaan
     * @return palauta lista tuotteen kategorioista
     */
    public List<Kategoria> annaKategoriat(Tuote tuote) {
        List<Kategoria> loytyneet = new ArrayList<Kategoria>();
        List<Liitos> kat = liitokset.annaKategoriat(tuote); // palauttaa kategoriat, haetaan kategoriat-luokasta selitteet
        for (Liitos lts : kat) {
           loytyneet.add(kategoriat.anna(lts.getKatNro()));

        }
        return loytyneet;
    }
    
    
    public Collection<Kategoria> annaKaikkiKat() {
        return kategoriat.annaKaikki();
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        MyyntiPaikka myyntiPaikka = new MyyntiPaikka();

        Tuote potkuKelkka = new Tuote();
        Tuote potkuKelkka2 = new Tuote();
        potkuKelkka.rekisteroi();
        potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka2.rekisteroi();
        potkuKelkka2.taytaTuoteTiedoilla();

        
        try {
            myyntiPaikka.lisaa(potkuKelkka);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(potkuKelkka2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());//e.printStackTrace();
        }
        

        for (int i = 0; i < myyntiPaikka.getTuotteet(); i++) {
            Tuote tuote = myyntiPaikka.annaTuote(i);
            tuote.tulosta(System.out);
        }
        

    }

}
