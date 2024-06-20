package Connexion;
import java.sql.Connection;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tahiana
 */
public class ConnexionDB {
     public static Connection conn = null;
     
    
    public static void loadConnexion() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java_data", "root", "");
            if(conn != null)
            {
                System.out.println("Connexion is stable");
            }
            else
            {
                System.out.println("Connexion is failed");
            }
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "error in database loading " +e);
        }
    }
}
