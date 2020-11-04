package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ordezkaritza {
    private String artista;
    private Herrialdea herrialdea;
    private String abestia;
    private int puntuak;
    private Image bandera;

    public Ordezkaritza(String artista, Herrialdea herrialdea, String abestia) {
        this.artista = artista;
        this.herrialdea = herrialdea;
        this.abestia = abestia;
        this.puntuak = 0;

        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+herrialdea.getBandera()+".png";
        try {
            this.bandera = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getArtista() {
        return artista;
    }

    public Herrialdea getHerrialdea() {
        return herrialdea;
    }

    public String getAbestia() {
        return abestia;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public Image getBandera() {
        return bandera;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setHerrialdea(Herrialdea herrialdea) {
        this.herrialdea = herrialdea;
    }

    public void setAbestia(String abestia) {
        this.abestia = abestia;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }

    public void setBandera(Image bandera) {
        this.bandera = bandera;
    }
}
