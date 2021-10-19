/**
 * 
 */
package kirppis;

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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
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
            tuotteet.lisaa(potkuKelkka2);
            tuotteet.lisaa(potkuKelkka2);
            tuotteet.lisaa(potkuKelkka2);
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
        }
        
        System.out.println("============= Tuotteet testi ================");
        
        for (int i = 0; i < tuotteet.getLkm(); i++) {
            Tuote tuote = tuotteet.anna(i);
            System.out.println("Tuote indeksi: " + i);
            tuote.tulosta(System.out);
        }
    }

}
