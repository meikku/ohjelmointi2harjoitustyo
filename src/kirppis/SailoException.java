package kirppis;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author meikk
 * @version 19.10.2021
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /** 
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa käytettävä viesti
     * @param viesti poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
