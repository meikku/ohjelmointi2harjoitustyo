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
    private Tuotteet tuotteet = new Tuotteet();
    private Kategoriat kategoriat = new Kategoriat();
    private Liitokset liitokset = new Liitokset();
    String hakemisto = "kirppis";
    
    /**
     * Lisätään uusi jäsen
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     */
    public void lisaa(Tuote tuote) throws SailoException{
       tuotteet.lisaa(tuote);
    }
    
    /**
     * Poistetaan tuote tuotteet-taulukosta
     * @param tuote tuote joka poistetaan
     */
    public void poista(Tuote tuote) {
        tuotteet.poista(tuote.getTunnusNro());
    }

    /**
     * @param tuote joka korvaa vanhan tai lisätään uutena
     */
    public void korvaaTaiLisaa(Tuote tuote) {
        tuotteet.korvaaTaiLisaa(tuote);
    }

    /**
     * Lisätään uusi kategoria
     * @param kat lisättävä kategoria
     */
    public void lisaa(Kategoria kat) {
        kategoriat.lisaa(kat);
    }
    
    /**
     * @param kat kategoria joka korvaa vanhan tai lisätään uutena
     */
    public void korvaaTaiLisaaKat(Kategoria kat) {
        kategoriat.korvaaTaiLisaa(kat);
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
    
    /**
     * @param s hakuehto jolla etsitään
     * @return lista tuotteista jotka vastaavat hakuehtoa
     */
    public Collection<Tuote> etsi(String s) {
       return tuotteet.etsi(s);
    }

    /**
     * Antaa myyntipaikan i:nnen kategorian
     * @param i kategorialistan indeksi
     * @return palauttaa indeksin kohdalta löytyvän kategorian
     */
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
    
    
    /**
     * Palauttaa listan kategorioista
     * @return lista kategorioista
     */
    public Collection<Kategoria> annaKaikkiKat() {
        return kategoriat.annaKaikki();
    }
    
    
    
    /**
     * @param nimi tiedoston hakemiston nimi
     * @throws SailoException jos tiedoston lukeminen ei onnistu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        // tuotteet.lueTiedostosta(nimi);
        tuotteet = new Tuotteet(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        kategoriat = new Kategoriat();
        liitokset = new Liitokset();
        
        // setTiedosto(nimi);
        tuotteet.lueTiedostosta(nimi);
        kategoriat.lueTiedostosta(nimi);
        liitokset.lueTiedostosta(nimi);
    }
    
    /**
     * Tallennetaan kirppiksen tiedot tiedostoon.
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            tuotteet.tallenna(hakemisto); 
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        try {
            kategoriat.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        try {
            liitokset.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        if (!"".equals(virhe) ) throw new SailoException(virhe);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        MyyntiPaikka myyntiPaikka = new MyyntiPaikka();
        try {
            myyntiPaikka.lueTiedostosta("kirppis");
        } catch (SailoException e) {
            e.printStackTrace();
        }

        Tuote potkuKelkka = new Tuote();
        Tuote potkuKelkka2 = new Tuote();
        potkuKelkka.rekisteroi();
        potkuKelkka.taytaTuoteTiedoilla();
        potkuKelkka2.rekisteroi();
        potkuKelkka2.taytaTuoteTiedoilla();
        
        Kategoria kulkuneuvo1 = new Kategoria();
        Kategoria kulkuneuvo2 = new Kategoria();
        kulkuneuvo1.rekisteroi();
        kulkuneuvo2.rekisteroi();
        kulkuneuvo1.taytaKatTiedoilla();
        kulkuneuvo2.taytaKatTiedoilla();

        
        try {
            myyntiPaikka.lisaa(potkuKelkka);
            myyntiPaikka.lisaa(potkuKelkka2);
            myyntiPaikka.lisaa(kulkuneuvo1);
            myyntiPaikka.lisaa(kulkuneuvo2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());//e.printStackTrace();
        }
        

        for (int i = 0; i < myyntiPaikka.getTuotteet(); i++) {
            Tuote tuote = myyntiPaikka.annaTuote(i);
            tuote.tulosta(System.out);
        }
    }


}
