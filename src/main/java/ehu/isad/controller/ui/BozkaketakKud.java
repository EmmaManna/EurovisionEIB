package ehu.isad.controller.ui;

import ehu.isad.EurovisionEIB;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class BozkaketakKud implements Initializable {

    private EurovisionEIB main;

    public void setMainApp(EurovisionEIB main){
        this.main = main;
    }

    private Integer puntuak = 0;

    private List<Ordezkaritza> ordezkaritza;

    private String unekoHerrialdea;

    @FXML
    private ImageView mgvw_logo;

    @FXML
    private ImageView mgvw_bihotza;

    @FXML
    private Text txt_banatu;

    @FXML
    private Text txt_warning;

    @FXML
    private Text txt_warning1;

    @FXML
    private Button btn_bozkatu;

    @FXML
    private TableView<Ordezkaritza> tbl_bozkatu;

    @FXML
    private TableColumn<Ordezkaritza, Image> col_bandera;

    @FXML
    private TableColumn<Ordezkaritza, Herrialdea> col_herrialdea;

    @FXML
    private TableColumn<Ordezkaritza, String> col_Artista;

    @FXML
    private TableColumn<Ordezkaritza, String> col_abestia;

    @FXML
    private TableColumn<Ordezkaritza, Integer> col_puntuak;

    @FXML
    void klikEgin(ActionEvent event) {
        List<Ordezkaritza> botoak = this.bozkatuaklortu();
        if(this.puntuak <= 5 && this.puntuak > 0 && this.guztiakPositibo(botoak)){
            for (int i = 0; i < botoak.size(); i++) {
                Ordezkaritza o = botoak.get(i);
                EurobisioaKud.getInstance().bozkaketaGorde(o.getHerrialdea().getIzena(), this.unekoHerrialdea, o.getPuntuak());
                EurobisioaKud.getInstance().puntuakEguneratu(o.getHerrialdea().getIzena(), o.getPuntuak());
            }
            main.top3Erakutsi();
        }
        else{
            txt_warning1.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_warning.setVisible(false);
        txt_warning1.setVisible(false);

        // Nola bistaratu gelaxkak (zutabearen arabera)
        // Get value from property of UserAccount.
        tbl_bozkatu.setEditable(true);
        col_abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        col_puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));
        col_Artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        col_herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));

        col_puntuak.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //Puntuak sartzen badira
        col_puntuak.setOnEditCommit(
                t -> {
                    txt_warning1.setVisible(false);
                    if(t.getNewValue()>t.getOldValue() && t.getOldValue()>=0){ //Puntuak eman
                        this.puntuak = this.puntuak+(t.getNewValue()-t.getOldValue());
                        this.warningKudeatu();
                    }
                    else if(t.getNewValue()<t.getOldValue() && t.getNewValue()>=0){ //Puntuak kendu
                        this.puntuak = this.puntuak-(t.getOldValue()-t.getNewValue());
                        this.warningKudeatu();
                    }
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setPuntuak(t.getNewValue());
                }
        );

        col_bandera.setCellValueFactory(new PropertyValueFactory<Ordezkaritza, Image>("bandera"));

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
        ordezkaritza = EurobisioaKud.getInstance().lortuOrdezkaritzak();
        ObservableList<Ordezkaritza> ordezkaritzak = FXCollections.observableArrayList(ordezkaritza);
        tbl_bozkatu.setItems(ordezkaritzak);

        //Uneko herrialdeari botoa propioa ematen ez utzi
        Callback<TableColumn<Ordezkaritza, Integer>, TableCell<Ordezkaritza, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.<Ordezkaritza, Integer>forTableColumn(new IntegerStringConverter());

        col_puntuak.setCellFactory(col -> {
            TableCell<Ordezkaritza, Integer> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    if (cell.getTableView().getSelectionModel().getSelectedItem().getHerrialdea().getIzena().equals(this.unekoHerrialdea)) {
                        cell.setEditable(false);
                    }else {
                        cell.setEditable(true);
                    }
                }
            });

            return cell ;
        });
    }

    public void datuakJarri(Herrialdea herrialdea){
        this.txt_banatu.setText(herrialdea.getIzena()+ "k horrela banatu nahi ditu bere puntuak:");
        this.bihotzaKargatu(herrialdea.getBandera());
        this.unekoHerrialdea = herrialdea.getIzena();
    }

    private void bihotzaKargatu(String izena){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+izena+"Bihotza.png";
        try {
            mgvw_bihotza.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Ordezkaritza> bozkatuaklortu(){
        List<Ordezkaritza> emaitza = new ArrayList<>();

        for(int i=0; i < this.ordezkaritza.size(); i++){
            if(this.ordezkaritza.get(i).getPuntuak() != 0){
                emaitza.add(this.ordezkaritza.get(i));
            }
        }
        return emaitza;
    }

    private Boolean guztiakPositibo(List<Ordezkaritza> o){
        for(int i=0; i < o.size(); i++){
            if(o.get(i).getPuntuak()<0){
               return false;
            }
        }
        return true;
    }

    private void warningKudeatu(){
        if(this.puntuak<=5){
            txt_warning.setVisible(false);
        }
        else{
            txt_warning.setVisible(true);
        }
    }
}
