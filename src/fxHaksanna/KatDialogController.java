package fxHaksanna;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kirppis.Kategoria;

/**
 * 
 * @author meikk
 * @version 11.12.2021
 *
 */
public class KatDialogController implements ModalControllerInterface<Kategoria>, Initializable {
    
    
    @FXML private TextField editKategoria;
    @FXML private TextField editKuvaus; 
    @FXML private Label labelVirhe;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    
    @Override
    public Kategoria getResult() {
        return katKohdalla;
    }

    @Override
    public void handleShown() {
        editKategoria.requestFocus();
    }
    
    @Override
    public void setDefault(Kategoria oletus) {
        katKohdalla = oletus;   
        naytaKategoria(katKohdalla);
    }
    
    @FXML private void handleOK() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        // katKohdalla = null; 
        ModalController.closeStage(labelVirhe);
    }

    
    // ====================================================================
    
    private Kategoria katKohdalla;
    
    private void alusta() {
//        editKategoria.setText(katKohdalla.getNimi());
//        editKuvaus.setText(katKohdalla.getKuvaus());
    }
    /**
     * Tyhjennetään tekstikentät
     */
    public void tyhjenna() {
        editKategoria.setText("");
        editKuvaus.setText("");
    }
    
    /**
     * Näytetään kategorian tiedot 
     * @param kat näytettävä kategoria
     */
    public void naytaKategoria(Kategoria kat) {
        if (kat == null) return;
        editKategoria.setText(kat.getNimi());
        editKuvaus.setText(kat.getKuvaus());
    }
    /**
     * Luodaan kysymisdialogi ja palautetaan sama tietue muutettuna
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return null jos painetaan cancel, muuten täytetty tietue
     */
    public static Kategoria kysyKat(Stage modalityStage, Kategoria oletus) {
        return ModalController.showModal(
                KatDialogController.class.getResource("LisaaMuokkaaKategoria.fxml"), 
                "Kategoria", modalityStage, oletus, null);
    }

}
