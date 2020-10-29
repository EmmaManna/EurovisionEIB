package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ErroreaKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    @FXML
    private ImageView mgvw_bihotza;

    @FXML
    private Text txt_jadaBozkatu;

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private Button btn_OK;

    @FXML
    void klikEgin(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void datuakJarri(Herrialdea herrialdea){
        this.txt_jadaBozkatu.setText(herrialdea.getIzena()+ "k jada banatu ditu bere puntuak");
        this.bihotzaKargatu(herrialdea.getBandera());
    }

    private void bihotzaKargatu(String izena){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+izena+"Bihotza.png";
        try {
            mgvw_bihotza.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

