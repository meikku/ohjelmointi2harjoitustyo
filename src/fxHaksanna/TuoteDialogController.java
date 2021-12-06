package fxHaksanna;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kirppis.Tuote;

/**
 * @author meikk
 * @version 6.12.2021
 *
 */
public class TuoteDialogController implements ModalControllerInterface<Tuote>, Initializable{

    @FXML private Label labelVirhe;
    @FXML private TextField editNimi;
    @FXML private TextField editHinta;
    @FXML private TextField editKunto;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Tuote getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Tuote oletus) {
        tuoteKohdalla = oletus;
        naytaTuote(tuoteKohdalla);
    }
    
    @FXML private void handlePoistaTuote() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleOK() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        ModalController.closeStage(labelVirhe);
    }
    
    // =====================================================================

    private Tuote tuoteKohdalla;
    
    private void naytaTuote(Tuote tuote) {
        if (tuote == null) return;
        editNimi.setText(tuote.getNimi());
    }
    
    /**
     * Luodaan tuotteen kysymisdialogi ja palautetaan sama tietue muutettuna 
     * TODO: korjattava toimimaan
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return null jos painetaan cancel, muuten täytetty tietue
     * 
     */
    public static Tuote kysyTuote(Stage modalityStage, Tuote oletus) {
     return ModalController.showModal(HaksannaGUIController.class.getResource("MuokkaaTuotetta.fxml"), "Muokkaa tuotetta", modalityStage, oletus);

    }

}
