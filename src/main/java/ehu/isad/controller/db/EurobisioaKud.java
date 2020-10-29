package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EurobisioaKud {

    private static final EurobisioaKud instance = new EurobisioaKud();

    public static EurobisioaKud getInstance() {
        return instance;
    }

    private EurobisioaKud() { }

    public List<Herrialdea> lortuHerrialdeak(){
        String query = "select izena, bandera from Herrialde"; //WHERE urtea=currentYear()
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Herrialdea> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                System.out.println(izena + ":" + bandera);
                emaitza.add(new Herrialdea(izena,bandera));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }


}
