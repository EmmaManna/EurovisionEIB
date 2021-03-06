/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ehu.isad;

import ehu.isad.controller.ui.*;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EurovisionEIB extends Application {

    private Parent EurobisioaUI;
    private Parent HerrialdeaHautatuUI;
    private Parent ErroreaUI;
    private Parent BozkaketaUI;
    private Parent Top3UI;
    private Parent PuntuazioaUI;

    private Stage stage;

    private Scene sceneHas;
    private Scene sceneHerrialdeHautatu;
    private Scene sceneErrorea;
    private Scene sceneBozkaketa;
    private Scene sceneTop3;
    private Scene scenePuntuazioa;

    private HasieraKud hasieraKud;
    private HerrialdeaHautatuKud herrialdeaHautatuKud;
    private ErroreaKud erroreaKud;
    private BozkaketakKud bozkaketakKud;
    private top3Kud top3Kud;
    private PuntuazioakKud puntuazioakKud;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("EUROVISION EIB");
        this.ikonoaJarri("");
        stage.setScene(sceneHas);
        stage.show();
    }

    private void ikonoaJarri(String izena){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+izena+"Bihotza.png";
        try {
            if(stage.getIcons().size()>0){
                stage.getIcons().remove(0);
            }
            stage.getIcons().add(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loaderEurobisioa = new FXMLLoader(getClass().getResource("/hasiera.fxml"));
        EurobisioaUI = (Parent) loaderEurobisioa.load();
        hasieraKud = loaderEurobisioa.getController();
        hasieraKud.setMainApp(this);
        sceneHas = new Scene(EurobisioaUI);

        FXMLLoader loaderHarrialdeaHautatu = new FXMLLoader(getClass().getResource("/herrialdeaHautatu.fxml"));
        HerrialdeaHautatuUI = (Parent) loaderHarrialdeaHautatu.load();
        herrialdeaHautatuKud = loaderHarrialdeaHautatu.getController();
        herrialdeaHautatuKud.setMainApp(this);
        sceneHerrialdeHautatu = new Scene(HerrialdeaHautatuUI);

        FXMLLoader loaderErrorea = new FXMLLoader(getClass().getResource("/errorea.fxml"));
        ErroreaUI = (Parent) loaderErrorea.load();
        erroreaKud = loaderErrorea.getController();
        erroreaKud.setMainApp(this);
        sceneErrorea = new Scene(ErroreaUI);

        FXMLLoader loaderBozkaketa = new FXMLLoader(getClass().getResource("/bozkaketak.fxml"));
        BozkaketaUI = (Parent) loaderBozkaketa.load();
        bozkaketakKud = loaderBozkaketa.getController();
        bozkaketakKud.setMainApp(this);
        sceneBozkaketa = new Scene(BozkaketaUI);

        FXMLLoader loaderTop3 = new FXMLLoader(getClass().getResource("/top3.fxml"));
        Top3UI = (Parent) loaderTop3.load();
        top3Kud = loaderTop3.getController();
        top3Kud.setMainApp(this);
        sceneTop3 = new Scene(Top3UI);

        FXMLLoader loaderPuntuazioa = new FXMLLoader(getClass().getResource("/puntuazioak.fxml"));
        PuntuazioaUI = (Parent) loaderPuntuazioa.load();
        puntuazioakKud = loaderPuntuazioa.getController();
        puntuazioakKud.setMainApp(this);
        scenePuntuazioa = new Scene(PuntuazioaUI);
    }


    public void herrialdeaHautatuErakutsi(){
        stage.setTitle("PARTE HARTZAILEAK");
        this.ikonoaJarri("");
        stage.setScene(sceneHerrialdeHautatu);
        stage.show();
    }

    public void erroreaErakutsi(Herrialdea herrialdea){
        stage.setTitle(herrialdea.getIzena().toUpperCase()+"REN INGURUKO INFORMAZIOA");
        this.ikonoaJarri(herrialdea.getBandera());
        erroreaKud.datuakJarri(herrialdea);
        stage.setScene(sceneErrorea);
        stage.show();
    }

    public void bozkatzekoPantailaErakutsi(Herrialdea herrialdea){
        stage.setTitle(herrialdea.getIzena().toUpperCase()+"REN BOZKAKETA PANELA");
        this.ikonoaJarri(herrialdea.getBandera());
        bozkaketakKud.setUnekoHerrialdea(herrialdea.getIzena());
        bozkaketakKud.datuakJarri(herrialdea);
        stage.setScene(sceneBozkaketa);
        stage.show();
    }

    public void top3Erakutsi(){
        stage.setTitle("LEHENENGO HIRU POSTUAK");
        this.ikonoaJarri("");
        stage.setScene(sceneTop3);
        top3Kud.datuakJarri();
        stage.show();
    }

    public void puntuazioakErakutsi(){
        stage.setTitle("SAILKAPEN OROKORRA");
        this.ikonoaJarri("");
        puntuazioakKud.datuakKargatu();
        stage.setScene(scenePuntuazioa);
        stage.show();
    }

}
