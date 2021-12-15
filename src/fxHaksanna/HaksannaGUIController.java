package fxHaksanna;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kirppis.Kategoria;
import kirppis.Liitos;
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
        valitseMyyntiPaikka();
    }
    @FXML void handleKategoriaKuvaukset() {
        naytaKategoriaKuvaukset();
    }
    
    @FXML void handleUusiKategoria() {
        uusiKat();
    }
    
    @FXML void handleMuokkaaKategoria() {
        muokkaaKategoria();
    }

    @FXML void handleLiitaKategoria() {
        liitaKategoria();
    }
    
    @FXML void handleAvaa() {
        avaa();
    }
    
    @FXML
    void handleTallenna() {
        tallenna();
    }

    @FXML private ListChooser<Tuote> chooserTuotteet;
    @FXML private ScrollPane panelTuote;
    @FXML private TextArea tuotteenKat;
    @FXML private ListChooser<Kategoria> chooserKategoriat;
    @FXML private TextField editNimi;
    @FXML private TextField editHinta;
    @FXML private TextField editKunto;
    @FXML private TextField editKuvaus;
    
// =========================================
    private MyyntiPaikka myyntiPaikka;
    private String myyntiPaikanNimi = "kirppis";
    private Tuote tuoteKohdalla;
    private Kategoria katKohdalla;
    private TextField[] edits;
     
    
    private void alusta() {
        panelTuote.setFitToHeight(true);
        panelTuote.setFitToWidth(true);
        chooserTuotteet.clear();
        chooserTuotteet.addSelectionListener(e -> naytaTuote());
        chooserKategoriat.clear();
        
        edits = new TextField[] { editNimi, editHinta, editKunto, editKuvaus };
    }
    
    private void liitaKategoria() {
        // ModalController.showModal(HaksannaGUIController.class.getResource("LisaaMuokkaaKategoria.fxml"), "Muokkaa kategorioita", null, "");
        tuotteenKategoria();
    }
    
    private void muokkaaTuote() {
        
        if (tuoteKohdalla == null) return;
        Tuote tuote;
        try {
            tuote = tuoteKohdalla.clone();
            tuote = TuoteDialogController.kysyTuote(null, tuoteKohdalla);
            if (tuote == null) return;
            myyntiPaikka.korvaaTaiLisaa(tuote);
            hae(tuote.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        // } catch (SailoException e) {
        //    e.printStackTrace();
        }
    }
    
    private void muokkaaKategoria() {
        katKohdalla = chooserKategoriat.getSelectedObject();
        if (katKohdalla == null) return;
        Kategoria kat;
        try {
            kat = katKohdalla.clone();
            kat = KatDialogController.kysyKat(null, katKohdalla);
            if (kat == null) return;
            myyntiPaikka.korvaaTaiLisaaKat(kat);
            hae(kat.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
    }
    
    private void lisaaTuote() {
        //ModalController.showModal(HaksannaGUIController.class.getResource("LisaaTuote.fxml"), "Lisää tuote", null, "");
        uusiTuote();
    }
    
    
    
    private void tuotteenKategoria() {
        tuotteenKat.setText("");
        katKohdalla = chooserKategoriat.getSelectedObject();
        tuoteKohdalla = chooserTuotteet.getSelectedObject();
        
        if (katKohdalla == null || tuoteKohdalla == null) return;
        
        
        Liitos uusi = new Liitos();
        uusi.rekisteroi(tuoteKohdalla.getTunnusNro(), katKohdalla.getTunnusNro());
        myyntiPaikka.lisaa(uusi);
        uusi.tulosta(System.out);
        naytaTuotteenKat();
    }
    
    private void naytaTuotteenKat() {
        
        tuotteenKat.setText("");
        if (tuoteKohdalla == null) return;
        tuoteKohdalla = chooserTuotteet.getSelectedObject();
        List<Kategoria> tuoteKat = myyntiPaikka.annaKategoriat(tuoteKohdalla);

        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(tuotteenKat)) {
            for (Kategoria kat : tuoteKat) {
                kat.tulosta(os);
            }
        }
        
    }
    
    private void haeKaikkiKat() {
        for (Kategoria kat : myyntiPaikka.annaKaikkiKat()) {
            
            chooserKategoriat.add(kat.getNimi(), kat);
        }
        
    }
    
    private void haeKat(int knro) {
         
        for (Kategoria kat : myyntiPaikka.annaKaikkiKat()) {
            if (kat.getTunnusNro() == knro)
            
            chooserKategoriat.add(kat.getNimi(), kat);
        }
        chooserKategoriat.setSelectedIndex(knro);
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
     * Lisätään myyntipaikkaan uusi kategoria
     */
    private void uusiKat() {
        Kategoria uusi = new Kategoria();
        uusi = KatDialogController.kysyKat(null, uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        // uusi.taytaKatTiedoilla(); // TODO: korvaa dialogilla aikanaan
        myyntiPaikka.lisaa(uusi);
        haeKat(uusi.getTunnusNro());
    }
    
    
    private void naytaTuote() {
        tuoteKohdalla = chooserTuotteet.getSelectedObject();
        if (tuoteKohdalla == null) return;        
        TuoteDialogController.naytaTuote(edits, tuoteKohdalla);
        naytaTuotteenKat();
    }
    

    /**
     * Lisätään myyntipaikkaan uusi tuote
     */
    protected void uusiTuote() {
        try {
            Tuote uusi = new Tuote();
            uusi = TuoteDialogController.kysyTuote(null, uusi);
            if (uusi == null) return;
            uusi.rekisteroi();
            myyntiPaikka.lisaa(uusi);
            hae(uusi.getTunnusNro());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
    }
    
//    private void setTitle(String title) {
//        ModalController.getStage(hakuehto).setTitle(title);
//    }
    
    /**
     * @param nimi tiedosto josta myyntipaikan tiedot luetaan
     * @return null jos onnistui, virhe jos tiedoston lukeminen ei onnistu
     */
    protected String lueTiedosto(String nimi) {
        myyntiPaikanNimi = nimi;
        try {
            myyntiPaikka.lueTiedostosta(nimi);
            hae(0);
            haeKaikkiKat();
            return null;
        } catch (SailoException e) {
            hae(0);
            haeKaikkiKat();
            String virhe = e.getMessage();
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
        
    }
    
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei 
     */
    public boolean avaa() {
        // ModalController.showModal(HaksannaGUIController.class.getResource("KaynnistysIkkuna.fxml"), "Myyntitilasto", null, "");
        String uusiNimi = KaynnistysIkkunaController.kysyNimi(null, myyntiPaikanNimi);
        if (uusiNimi == null || "".equals(uusiNimi)) return false;
        String aukeaa = lueTiedosto(uusiNimi); 
        if (aukeaa != null) avaa();
        return true;
    }
    
    private String tallenna() {
        try {
            myyntiPaikka.tallenna();
            return null;
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Tallennus ei onnistu " + e.getMessage());
            return e.getMessage();
        }
        
    }
    
    private void naytaRaportti() {
        ModalController.showModal(HaksannaGUIController.class.getResource("MyyntiTilasto.fxml"), "Raportti", null, "");
    }
    
    private void valitseMyyntiPaikka() {
        ModalController.showModal(HaksannaGUIController.class.getResource("KaynnistysIkkuna.fxml"), "Myyntitilasto", null, "");
    }
    
    private void naytaKategoriaKuvaukset() {
        Dialogs.showMessageDialog("Kategorioiden kuvaukset tulossa pian...");
    }  

    /**
     * Asetetetaan käytettävä myyntipaikka
     * @param myyntiPaikka jota käytetään
     */
    public void setMyyntiPaikka(MyyntiPaikka myyntiPaikka) {
        this.myyntiPaikka = myyntiPaikka;
    }
    
}
