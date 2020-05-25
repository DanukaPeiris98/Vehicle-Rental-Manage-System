import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {

    private static final String USERNAME = "root";//username of database
    private static final String PASSWORD = "";//password of database
    private static final String URL = "jdbc:mysql://localhost:3306/oopcw";//url of database


    public static Connection connectToDatabase(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);//get connection to database
            //con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oopcw","root","");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database Error occurred");
        }
        return con;
    }
}
