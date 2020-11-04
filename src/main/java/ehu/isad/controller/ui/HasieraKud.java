package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main) {
        this.main = main;
    }

    @FXML
    private Button btn_bozkatu;

    @FXML
    void klikEgin(ActionEvent event) {
        this.main.herrialdeaHautatuErakutsi();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
