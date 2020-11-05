package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Top3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

public class top3Kud implements Initializable {
    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    private List<Top3> datuak;

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private Text txt_top;

    @FXML
    private Button btn_OK;

    @FXML
    private TableView<Top3> tbl_top3;

    @FXML
    private TableColumn<Top3, Image> clmn_bandera;

    @FXML
    private TableColumn<Top3, Herrialdea> clmn_herrialdea;

    @FXML
    private TableColumn<Top3, Integer> clmn_puntuak;

    @FXML
    void klikEgin(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nola bistaratu gelaxkak (zutabearen arabera)
        // Get value from property of UserAccount.
        clmn_puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));
        clmn_herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));

        clmn_puntuak.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        clmn_bandera.setCellValueFactory(new PropertyValueFactory<Top3, Image>("bandera"));

        clmn_bandera.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(15);
                    imageview.setFitWidth(20);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                    // tbData.refresh();
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });

        //add your data to the table here.
        this.datuakJarri();
    }

    public void datuakJarri(){
        datuak = EurobisioaKud.getInstance().lortuTop3();
        ObservableList<Top3> top3 = FXCollections.observableArrayList(datuak);
        tbl_top3.setItems(top3);
    }
}
