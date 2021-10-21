package fxHaksanna;

import javafx.application.Application;
import javafx.stage.Stage;
import kirppis.MyyntiPaikka;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author meikkupyrhonen
 * @version 16.9.2021
 *
 */
public class HaksannaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("HaksannaGUIView.fxml"));
            final Pane root = ldr.load();
            final HaksannaGUIController haksannaCtrl = (HaksannaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("haksanna.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Myyntipaikka");
            
            MyyntiPaikka myyntiPaikka = new MyyntiPaikka();
            haksannaCtrl.setMyyntiPaikka(myyntiPaikka);
            
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}