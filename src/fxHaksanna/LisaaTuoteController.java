package fxHaksanna;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author meikkupyrhonen
 * @version 14.10.2021
 *
 */
public class LisaaTuoteController {
    @FXML void handlePeruuta() {
        peruuta();
    }

    @FXML void handleTallenna() {
        tallenna();
    }
// ------------------------------------
    
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan. Sitten kun osataan.");
    }
    private void peruuta() {
        Dialogs.showMessageDialog("Peruutetaan. Sitten kun osataan.");
    }
}
