package ehu.isad.model;

public class Herrialdea {
    private String izena;
    private String bandera;

    public Herrialdea(String izena, String bandera) {
        this.izena = izena;
        this.bandera = bandera;
    }

    @Override
    public String toString() {
        return izena;
    }

    public String getIzena() {
        return izena;
    }

    public String getBandera() {
        return bandera;
    }
}
