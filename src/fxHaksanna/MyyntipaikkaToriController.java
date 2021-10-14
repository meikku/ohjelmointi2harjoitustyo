package fxHaksanna;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;

/**
 * @author meikkupyrhonen
 * @version 13.10.2021
 *
 */
public class MyyntipaikkaToriController {


    @FXML void handleLisaaKategoria() {
        lisaaKategoria();
    }

    @FXML void handleMuokkaaTuote() {
        muokkaaTuote();
    }

    @FXML void handlelLisaaTuote() {
        lisaaTuote();
    }
// -----------------------------
    private void lisaaKategoria() {
        Dialogs.showMessageDialog("Lisätään kategoria. Ei toimi vielä.");
    }
    
    private void muokkaaTuote() {
        ModalController.showModal(HaksannaGUIController.class.getResource("MuokkaaTuotetta.fxml"), "Muokkaa tuotetta", null, "");
    }
    private void lisaaTuote() {
        ModalController.showModal(HaksannaGUIController.class.getResource("LisaaTuote.fxml"), "Lisää tuote", null, "");
    }
}


