package fxHaksanna;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author meikk
 * @version 4.12.2021
 *
 */
public class KaynnistysIkkunaController implements ModalControllerInterface<String> {

    @FXML private TextField annaMyyntiPaikka;
    private String vastaus = null;

    @FXML void handleOK() {
        vastaus = annaMyyntiPaikka.getText();
        ModalController.closeStage(annaMyyntiPaikka);
    }


    @FXML void handleCancel() {
        ModalController.closeStage(annaMyyntiPaikka);
    }


    @Override
    public String getResult() {
        return vastaus;
    }


    @Override
    public void handleShown() {
        annaMyyntiPaikka.requestFocus();
    }


    @Override
    public void setDefault(String oletus) {
        annaMyyntiPaikka.setText(oletus);
    }
    

    /**
     * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus nimi jota käytetään oletuksena
     * @return null jos painetaan cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(KaynnistysIkkunaController.class.getResource("KaynnistysIkkuna.fxml"), "Myyntipaikka", modalityStage, oletus);
    }
}
