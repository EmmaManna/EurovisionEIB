package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private Text txt_aukeratu;

    @FXML
    private ComboBox<Herrialdea> cmbx_herrialdeak;

    @FXML
    private Button btn_OK;

    @FXML
    private Text txt_warning;

    @FXML
    void aukeratu(ActionEvent event) {
        this.txt_warning.setVisible(false);
    }

    @FXML
    void klikEgin(ActionEvent event) {
        if(cmbx_herrialdeak.getValue()!=null){
            Herrialdea herrialdea = cmbx_herrialdeak.getValue();
            String bandera = EurobisioaKud.getInstance().bozkatuDu(herrialdea.getIzena());
            if(bandera!=null){ //Jada bozkatu du
                System.out.println("Jada bozkatu du");

            }
            else{ //Oraindik ez du bozkatu
                System.out.println("Oraindik ez du bozkatu");
            }
            main.erroreaErakutsi(herrialdea);
        }
        else{
            txt_warning.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Herrialdea> herrialdeLista = EurobisioaKud.getInstance().lortuHerrialdeak();
        ObservableList<Herrialdea> herrialdeak = FXCollections.observableArrayList(herrialdeLista);
        cmbx_herrialdeak.setItems(herrialdeak);

        txt_warning.setVisible(false);
    }
}

