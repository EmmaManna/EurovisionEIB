package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Top3 {
    private Integer puntuak;
    private Image bandera;
    private Herrialdea herrialdea;

    public Top3(Integer puntuak, Herrialdea herrialdea) {
        this.puntuak = puntuak;
        this.herrialdea = herrialdea;

        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+herrialdea.getBandera()+".png";
        try {
            this.bandera = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Integer getPuntuak() {
        return puntuak;
    }

    public Image getBandera() {
        return bandera;
    }

    public Herrialdea getHerrialdea() {
        return herrialdea;
    }

    public void setPuntuak(Integer puntuak) {
        this.puntuak = puntuak;
    }

    public void setBandera(Image bandera) {
        this.bandera = bandera;
    }

    public void setHerrialdea(Herrialdea herrialdea) {
        this.herrialdea = herrialdea;
    }
}
