/**
 * 
 */
package kirppis;
    

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
    
    /**
     * Lisätään uusi jäsen
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     */
    public void lisaa(Tuote tuote) throws SailoException{
       tuotteet.lisaa(tuote);
    }
    
    /**
     * @return tuotteiden lukumäärä
     */
    public int getTuotteet() {
       return this.tuotteet.getLkm(); 
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
