package kirppis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.platform.engine.support.descriptor.CompositeTestSource;

/**
 * Pitää yllä rekisteriä, jossa liitetään tuotteiden 
 *  ja niihin liittyvien kategorioiden id:t toisiinsa.                                                 
 * Lukee ja kirjoittaa id-tiedot tiedostoon.                        
 * Osaa etsiä ja lajitella.  
 * @author meikk
 * @version 4.11.2021
 *
 */
public class Liitokset {
    
    private Collection<Liitos> alkiot = new LinkedList<Liitos>();
    
    /**
     * @param lts Liitos joka tuodaan
     */
    public void lisaa(Liitos lts) {
        alkiot.add(lts);
    }
    
    /**
     * tulostetaan Liitosten tiedot
     */
    public void tulosta() {
        for (Liitos lts : alkiot) {
            lts.tulosta(System.out);
        }
    }
    
    /**
     * Etsitään kategoriaan kuuluvat tuotteet
     * @param kat kategoria, jonka tuotteita etsitään
     * @return lista löytyneistä
     * @example
     * <pre name="test">
     *  Kategoria kat = new Kategoria();
     *  kat.taytaKatTiedoilla();
     *  kat.rekisteroi;
     *  annaTuotteet(kat) === 
     * </pre>
     */
    public List<Liitos> annaTuotteet(Kategoria kat) {
        List<Liitos> loydetyt = new ArrayList<Liitos>();
        for (Liitos lts : alkiot) 
            if (lts.getKatNro() == kat.getTunnusNro())
                loydetyt.add(lts);  
        return loydetyt;
    }
    
    /**
     * Etsitään tuotteeseen liittyvät kategoriat
     * @param tuote tuote, jonka kategorioita etsitään
     * @return lista löytyneistä
     */
    public List<Liitos> annaKategoriat(Tuote tuote) {
        List<Liitos> loydetyt = new ArrayList<Liitos>();
        for (Liitos lts : alkiot)
            if (lts.getTuoteNro() == tuote.getTunnusNro())
                loydetyt.add(lts);
        return loydetyt;
    }

    /**
     * Testataan liitokset-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Liitokset liitokset = new Liitokset();
        Liitos a = new Liitos();
        Liitos b = new Liitos();
        Liitos c = new Liitos();
        
        a.rekisteroi(1, 2);
        b.rekisteroi(3, 1);        
        c.rekisteroi(2, 2);
        
        liitokset.lisaa(a);
        liitokset.lisaa(b);
        liitokset.lisaa(c);
        
        System.out.println("======== Liitokset testi ========\n");

        liitokset.tulosta();
        Kategoria kulkuValine = new Kategoria();
        kulkuValine.taytaKatTiedoilla();
        kulkuValine.rekisteroi();
        
        System.out.println("\nEtsitään tuotteet: ");
        List<Liitos> liitokset2 = liitokset.annaTuotteet(kulkuValine);
        
        for (Liitos lts : liitokset2) {
            lts.tulosta(System.out);
        }
        
        

        
    }
    
    
   

}
