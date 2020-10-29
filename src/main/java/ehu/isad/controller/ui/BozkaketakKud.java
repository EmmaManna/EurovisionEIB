package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class BozkaketakKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private ImageView mgvw_bihotza;

    @FXML
    private Text txt_banatu;

    @FXML
    private Button btn_bozkatu;

    @FXML
    private TableColumn<?, ?> col_bandera;

    @FXML
    private TableColumn<?, ?> col_herrialdea;

    @FXML
    private TableColumn<?, ?> col_Artista;

    @FXML
    private TableColumn<?, ?> col_abestia;

    @FXML
    private TableColumn<?, ?> col_puntuak;

    @FXML
    void klikEgin(ActionEvent event) {
        main.top3Erakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void datuakJarri(Herrialdea herrialdea){
        this.txt_banatu.setText(herrialdea.getIzena()+ "k horrela banatu nahi ditu bere puntuak:");
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
