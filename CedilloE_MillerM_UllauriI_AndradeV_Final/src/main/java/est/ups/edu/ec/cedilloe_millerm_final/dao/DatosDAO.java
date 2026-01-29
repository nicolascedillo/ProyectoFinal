/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.dao;

import est.ups.edu.ec.cedilloe_millerm_final.config.ConexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mateo
 */
public class DatosDAO {
    
    public int getWaitingTime() {

        String sql = "SELECT waiting_time FROM datos WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("waiting_time");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getUnitTime() {

        String sql = "SELECT unit_time FROM datos WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String unit = rs.getString("unit_time");
                return (unit == null || unit.trim().isEmpty()) ? "seg" : unit;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "seg";
    }

    public boolean updateWaitingTime(int time) {
        String sql = "UPDATE datos SET waiting_time = ? WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, time);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateUnitTime(String unit) {
        String sql = "UPDATE datos SET unit_time = ? WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, unit);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAttempts() {
        String sql = "SELECT attempts FROM datos WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("attempts");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean updateAttempts(int attempts) {
        String sql = "UPDATE datos SET attempts = ? WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attempts);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int getWaitingTimeDoor() {

        String sql = "SELECT open_door_time FROM datos WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("open_door_time");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getUnitTimeDoor() {

        String sql = "SELECT unit_door_time FROM datos WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String unit = rs.getString("unit_door_time");
                return (unit == null || unit.trim().isEmpty()) ? "seg" : unit;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "seg";
    }

    public boolean updateWaitingTimeDoor(int time) {
        String sql = "UPDATE datos SET open_door_time = ? WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, time);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUnitTimeDoor(String unit) {
        String sql = "UPDATE datos SET unit_door_time = ? WHERE id = 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, unit);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
