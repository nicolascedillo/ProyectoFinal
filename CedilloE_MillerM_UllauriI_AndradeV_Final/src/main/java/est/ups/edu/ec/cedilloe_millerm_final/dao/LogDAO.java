/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.dao;

import est.ups.edu.ec.cedilloe_millerm_final.config.ConexionBDD;
import est.ups.edu.ec.cedilloe_millerm_final.model.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mateo
 */
public class LogDAO {
    
    public List<Log> obtenerLogsPorRango(String startStr, String endStr) {

        List<Log> lista = new ArrayList<>();

        String sql = """
            SELECT 
                l.id,
                u.user AS usuario,
                DATE_FORMAT(l.fecha_hora, '%d-%m-%Y %H:%i:%s') AS fecha_formato,
                l.acceso
            FROM logs l
            JOIN usuarios u ON u.id = l.usuario_id
            WHERE l.fecha_hora BETWEEN 
                STR_TO_DATE(?, '%d-%m-%Y %H:%i:%s')
            AND STR_TO_DATE(?, '%d-%m-%Y %H:%i:%s')
            ORDER BY l.fecha_hora ASC
        """;

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, startStr);
            ps.setString(2, endStr);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Log(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("fecha_formato"),
                            rs.getString("acceso")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public void insertLogMistake() {

        String sql = """
            INSERT INTO logs (usuario_id, acceso)
            VALUES (3, 'FAIL')
        """;

        try (Connection con = ConexionBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertLogSuccessful(int keyPass) {

        String sqlUser = "SELECT id FROM usuarios WHERE key_pass = ?";
        String sqlLog  = 
            """
                INSERT INTO logs 
                    (usuario_id, acceso)
                VALUES 
                    (?, 'OK')
            """;

        try (Connection con = ConexionBDD.getConexion();
             PreparedStatement psUser = con.prepareStatement(sqlUser)) {

            psUser.setInt(1, keyPass);
            ResultSet rs = psUser.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");

                try (PreparedStatement psLog = con.prepareStatement(sqlLog)) {
                    psLog.setInt(1, userId);
                    psLog.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
