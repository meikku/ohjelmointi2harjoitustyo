package fxHaksanna;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
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
    @FXML private TextField editKatKuvaus; 
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
        katKohdalla = null; 
        ModalController.closeStage(labelVirhe);
    }

    
    // ====================================================================
    
    private Kategoria katKohdalla;
    private TextField[] edits;
    
    private void alusta() {
        edits = new TextField[] { editKategoria, editKatKuvaus };
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosKategoriaan(k, edit));
        }
    }
//    /**
//     * Tyhjennetään tekstikentät
//     */
//    public void tyhjenna() {
//        editKategoria.setText("");
//        editKuvaus.setText("");
//    }
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    private void kasitteleMuutosKategoriaan(int k, TextField edit) {
        if (katKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
        case 1 : virhe = katKohdalla.setNimi(s); break;
        case 2 : virhe = katKohdalla.setKuvaus(s); break;
        default:
        }
        if (virhe == null) {
            Dialogs.setToolTipText(edit, "");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
            
        }
    }
    
    private void naytaKategoria(Kategoria kat) {
        naytaKategoria(edits, kat);
    }
    /**
     * Näytetään kategorian tiedot 
     * @param edits taulukko jossa tekstikenttiä
     * @param kat näytettävä kategoria
     */
    public static void naytaKategoria(TextField[] edits, Kategoria kat) {
        if (kat == null) return;
        edits[0].setText(kat.getNimi());
        edits[1].setText(kat.getKuvaus());
        
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
                "Kategoria", modalityStage, oletus);
    }

}
