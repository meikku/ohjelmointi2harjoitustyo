package fxHaksanna;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kirppis.Tuote;
import kirppis.MyyntiPaikka;

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
    @FXML private TextField editKuvaus;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @Override
    public Tuote getResult() {
        return tuoteKohdalla;
    }

    @Override
    public void handleShown() {
        editNimi.requestFocus();
    }

    @Override
    public void setDefault(Tuote oletus) {
        tuoteKohdalla = oletus;
        naytaTuote(tuoteKohdalla);
    }
    
    @FXML private void handlePoistaTuote() {
        poistaTuote();
    }
    
    @FXML private void handleOK() {
        if ( tuoteKohdalla != null && tuoteKohdalla.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        tuoteKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
    // =====================================================================

    private Tuote tuoteKohdalla;
    private TextField[] edits;
    private MyyntiPaikka myyntiPaikka;
    
    private void alusta() {
        edits = new TextField[] { editNimi, editHinta, editKunto, editKuvaus };
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased( e -> kasitteleMuutosTuotteeseen(k, edit));
        }
        myyntiPaikka = new MyyntiPaikka();
    }
    
    private void poistaTuote() {
       if (tuoteKohdalla == null) return;
       myyntiPaikka.poista(tuoteKohdalla);
       ModalController.closeStage(labelVirhe);
    }
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    private void kasitteleMuutosTuotteeseen(int k, TextField edit) {
        if (tuoteKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
        case 1 : virhe = tuoteKohdalla.setNimi(s); break;
        case 2 : virhe = tuoteKohdalla.setHinta(s); break;
        case 3 : virhe = tuoteKohdalla.setKunto(s); break;
        case 4 : virhe = tuoteKohdalla.setKuvaus(s); break;
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

    
    private void naytaTuote(Tuote tuote) {
        naytaTuote(edits, tuote);
    }
    
    /**
     * Näytetään tuotteen tiedot TextField komponentteihin
     * @param edits taulukko jossa tekstikenttiä
     * @param tuote näytettävä tuote
     */
    public static void naytaTuote(TextField[] edits, Tuote tuote) {
        if (tuote == null) return;
        edits[0].setText(tuote.getNimi());
        edits[1].setText(tuote.getHinta());
        edits[2].setText(tuote.getKunto());
        edits[3].setText(tuote.getKuvaus());
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
