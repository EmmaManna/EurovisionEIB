package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
}
