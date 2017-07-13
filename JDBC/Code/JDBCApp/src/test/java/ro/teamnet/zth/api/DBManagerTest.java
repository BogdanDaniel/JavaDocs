package ro.teamnet.zth.api;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert.*;
import ro.teamnet.zth.api.database.DBManager;

import static org.junit.Assert.assertEquals;
import static ro.teamnet.zth.api.database.DBManager.checkConnection;


/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */

public class DBManagerTest {


    @Test
    public void testGetConnection(){
        String IP = "localhost";
        String PORT = "1521";
        String USER = "sys as sysdba";
        String PASS = "admin";
        String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
        String CONNECTION_STRING = "jdbc:oracle:thin:@" + IP + ":" + PORT + ":xe";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING,USER,PASS);
        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }
    @Test
    public void testCheckConnection() throws  Exception{
        String IP = "localhost";
        String PORT = "1521";
        String USER = "sys as sysdba";
        String PASS = "admin";
        String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
        String CONNECTION_STRING = "jdbc:oracle:thin:@" + IP + ":" + PORT + ":xe";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING,USER,PASS);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        checkConnection(conn);
    }
}
