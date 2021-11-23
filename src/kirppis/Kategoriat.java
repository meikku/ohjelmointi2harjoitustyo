package kirppis;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

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
    
    public Kategoria anna(int tunnusNro) {
        for (Kategoria kat : alkiot) {
            if (tunnusNro == kat.getTunnusNro()) return kat;
            
        }
        return null;
    }
    
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
     * Testataan kategoriat-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kategoriat kategoriat = new Kategoriat();
        Kategoria kulkuValine1 = new Kategoria();
        kulkuValine1.taytaKatTiedoilla();
        Kategoria kulkuValine2 = new Kategoria();
        kulkuValine2.taytaKatTiedoilla();
        Kategoria kulkuValine3 = new Kategoria();
        kulkuValine3.taytaKatTiedoilla();
        Kategoria kulkuValine4 = new Kategoria();
        kulkuValine4.taytaKatTiedoilla();
        
        
        
        
        kategoriat.lisaa(kulkuValine1);
        kategoriat.lisaa(kulkuValine2);
        kategoriat.lisaa(kulkuValine3);
        kategoriat.lisaa(kulkuValine4);
        
        System.out.println("======= Kategoriat testi =======");
    
        kategoriat.tulosta(System.out);
        

    }

}
