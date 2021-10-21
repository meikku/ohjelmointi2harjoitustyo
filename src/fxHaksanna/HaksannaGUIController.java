package fxHaksanna;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import kirppis.MyyntiPaikka;
import kirppis.SailoException;
import kirppis.Tuote;
/**
 * @author meikkupyrhonen
 * @version 16.9.2021
 *
 */
public class HaksannaGUIController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML void handleLisaaKategoria() {
        lisaaKategoria();
    }

    @FXML void handleMuokkaaTuote() {
        muokkaaTuote();
    }

    @FXML void handleLisaaTuote() {
        lisaaTuote();
    }
    @FXML void handleRaportti() {
        naytaRaportti();
    }
    @FXML void handleMyyntiPaikka() {
        vaihdaMyyntiPaikkaa();
    }
    @FXML void handleKategoriaKuvaukset() {
        naytaKategoriaKuvaukset();
    }
    @FXML private ListChooser<Tuote> chooserTuotteet;
    @FXML private ScrollPane panelTuote;
    
// -----------------------------
    private MyyntiPaikka myyntiPaikka;
    
    private TextArea areaTuote = new TextArea(); // TODO: poista myöhemmin
    
    private void alusta() {
        panelTuote.setContent(areaTuote);
        areaTuote.setFont(new Font("Courier New", 12));
        panelTuote.setFitToHeight(true);
        chooserTuotteet.clear();
        chooserTuotteet.addSelectionListener(e -> naytaTuote());
        
    }
    
    private void lisaaKategoria() {
        ModalController.showModal(HaksannaGUIController.class.getResource("LisaaMuokkaaKategoria.fxml"), "Muokkaa kategorioita", null, "");
    }
    
    private void muokkaaTuote() {
        ModalController.showModal(HaksannaGUIController.class.getResource("MuokkaaTuotetta.fxml"), "Muokkaa tuotetta", null, "");
    }
    private void lisaaTuote() {
        //ModalController.showModal(HaksannaGUIController.class.getResource("LisaaTuote.fxml"), "Lisää tuote", null, "");
        uusiTuote();
    }
    private void naytaRaportti() {
        ModalController.showModal(HaksannaGUIController.class.getResource("MyyntiTilasto.fxml"), "Raportti", null, "");
    }
    private void vaihdaMyyntiPaikkaa() {
        ModalController.showModal(HaksannaGUIController.class.getResource("KaynnistysIkkuna.fxml"), "Myyntitilasto", null, "");
    }
    private void naytaKategoriaKuvaukset() {
        Dialogs.showMessageDialog("Kategorioiden kuvaukset tulossa pian...");
    }
    
    private void naytaTuote() {
        Tuote tuoteKohdalla = chooserTuotteet.getSelectedObject();
        
        if (tuoteKohdalla == null) return;
        
        areaTuote.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTuote)) {
                tuoteKohdalla.tulosta(os);
        }
    }
    private void hae(int tnro) {
        chooserTuotteet.clear();
        
        int index = 0;
        for (int i = 0; i < myyntiPaikka.getTuotteet(); i++) {
            Tuote tuote = myyntiPaikka.annaTuote(i);
            if (tuote.getTunnusNro() == tnro) index = i;
            chooserTuotteet.add(tuote.getNimi(), tuote);
        }
        chooserTuotteet.setSelectedIndex(index); // tästä tulee muutosviesti
    }
    /*
     * Lisätään myyntipaikkaan uusi tuote
     */
    private void uusiTuote() {
        Tuote uusi = new Tuote();
        uusi.rekisteroi();
        uusi.taytaTuoteTiedoilla(); // TODO: korvaa dialogilla aikanaan
        
        try {
            myyntiPaikka.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae(uusi.getTunnusNro());
    }

    /**
     * Asetetetaan käytettävä myyntipaikka
     * @param myyntiPaikka jota käytetään
     */
    public void setMyyntiPaikka(MyyntiPaikka myyntiPaikka) {
        this.myyntiPaikka = myyntiPaikka;
    }
    
}
