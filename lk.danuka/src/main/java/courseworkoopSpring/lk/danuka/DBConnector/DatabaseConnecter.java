package courseworkoopSpring.lk.danuka.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {
    public static Connection connectToDatabase(){
        Connection con = null;
        try {
            //con = DriverManager.getConnection(URL,USERNAME,PASSWORD);//get connection to database
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oopcw","root","");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database Error occurred");
        }
        return con;
    }
}
