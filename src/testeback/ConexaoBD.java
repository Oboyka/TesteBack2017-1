package testeback;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cil.2473288447
 */
public class ConexaoBD {
    public Connection connection = null;
    private final String drv = "com.mysql.jdbc.Driver";
    private final String db = "testeback";
    private final String location = "jdbc:mysql://localhost:3306/"+db;
    private final String login = "root";
    private final String pass = "root";
    
    public boolean getConnection(){
        try{
            Class.forName(drv);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(location, login, pass);
            return true;
        } catch (ClassNotFoundException e){
            System.out.println(":( " + e.toString());
            return false;
        } catch (SQLException e){
            System.out.println(":( " + e.toString());
            return false;
        }
    }
}
