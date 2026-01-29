/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mateo
 */
public class ConexionBDD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/control_acceso";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConexion(){
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
