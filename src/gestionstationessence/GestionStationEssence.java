package gestionstationessence;

import Connexion.ConnexionDB;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.SQLException;
import vue.AcceuilPrincipale;

public class GestionStationEssence {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FlatAnimatedLafChange.showSnapshot();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        FlatMacLightLaf.setup();
        ConnexionDB.loadConnexion();
        AcceuilPrincipale acceuil = new AcceuilPrincipale();
        acceuil.show();

    }

}
