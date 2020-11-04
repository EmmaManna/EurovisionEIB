package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Datuak;
import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PuntuazioakKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    private List<Datuak> puntuazioa;

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private Button btn_bozkatu;

    @FXML
    private TableView<Datuak> tbl_bozkatu;

    @FXML
    private TableColumn<Datuak, Image> col_bandera;

    @FXML
    private TableColumn<Datuak, Herrialdea> col_herrialdea;

    @FXML
    private TableColumn<Datuak, String> col_Artista;

    @FXML
    private TableColumn<Datuak, String> col_abestia;

    @FXML
    private TableColumn<Datuak, Integer> col_puntuak;

    @FXML
    void klikEgin(ActionEvent event) {
        this.main.herrialdeaHautatuErakutsi();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nola bistaratu gelaxkak (zutabearen arabera)
        // Get value from property of UserAccount.
        col_abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        col_puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));
        col_Artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        col_herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));

        col_puntuak.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        col_bandera.setCellValueFactory(new PropertyValueFactory<Datuak, Image>("bandera"));

        col_bandera.setCellFactory(p -> new TableCell<>() {
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
        puntuazioa = EurobisioaKud.getInstance().lortuPuntuazioak();
        ObservableList<Datuak> herrialdePuntuak = FXCollections.observableArrayList(puntuazioa);
        tbl_bozkatu.setItems(herrialdePuntuak);
    }
}
