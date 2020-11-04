package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class top3Kud implements Initializable {
    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }


    @FXML
    private ImageView mgvw_logo;

    @FXML
    private Text txt_top;

    @FXML
    private ImageView mgvw_bantop1;

    @FXML
    private ImageView mgvw_bantop2;

    @FXML
    private ImageView mgvw_bantop3;

    @FXML
    private Text txt_top1;

    @FXML
    private Text txt_top2;

    @FXML
    private Text txt_top3;

    @FXML
    private Button btn_OK;

    @FXML
    void klikEgin(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void datuakJarri(){
        List<String> datuak = EurobisioaKud.getInstance().lortuTop3();

        String herrialdea = datuak.get(0);
        String[] zatiak = herrialdea.split(";");
        txt_top1.setText(zatiak[1]+ " - " + zatiak[2] + " puntu");
        mgvw_bantop1.setImage(this.irudiaLortu(zatiak[0]));

        herrialdea = datuak.get(1);
        zatiak = herrialdea.split(";");
        txt_top2.setText(zatiak[1]+ " - " + zatiak[2] + " puntu");
        mgvw_bantop2.setImage(this.irudiaLortu(zatiak[0]));

        herrialdea = datuak.get(2);
        zatiak = herrialdea.split(";");
        txt_top3.setText(zatiak[1]+ " - " + zatiak[2] + " puntu");
        mgvw_bantop3.setImage(this.irudiaLortu(zatiak[0]));

    }

    private Image irudiaLortu(String path){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+path+".png";
        try {
            return new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
