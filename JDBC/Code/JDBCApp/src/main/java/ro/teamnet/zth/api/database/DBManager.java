package ro.teamnet.zth.api.database;

import javax.sound.midi.Soundbank;
import java.sql.*;

/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */
public class DBManager {

    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    public DBManager(){
        throw new UnsupportedOperationException();
    }

    private static void registerDriver(){
        try{
            Class.forName(DBProperties.DRIVER_CLASS);
        }catch(ClassNotFoundException ex){
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
       try {
          conn = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
       }catch(SQLException ex){
            ex.printStackTrace();
        }


        return conn;
    }

    public static void checkConnection(Connection connection){
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
            if(rs != null)
                System.out.println("Conectat!");

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }
}
