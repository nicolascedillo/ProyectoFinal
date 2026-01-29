/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.dao;

import est.ups.edu.ec.cedilloe_millerm_final.config.ConexionBDD;
import est.ups.edu.ec.cedilloe_millerm_final.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo
 */
public class UsuarioDAO {
    
    public boolean existeUsuario(String user) {

        String sql = "SELECT 1 FROM usuarios WHERE user = ? LIMIT 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer validarLogin(String user, String pass) {

        String sql = "SELECT id FROM usuarios WHERE user = ? AND password = ? LIMIT 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer validarCredenciales(String user, String keyPass) {

        String sql = "SELECT id FROM usuarios WHERE user = ? AND key_pass = ? LIMIT 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, keyPass);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean actualizarPass(String user, String newPass) {

        String sql = "UPDATE usuarios SET password = ? WHERE user = ?";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPass);
            ps.setString(2, user);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean crearUsuario(String user, String pass, String keyPass) {

        String sql = "INSERT INTO usuarios (user, password, key_pass) VALUES (?, ?, ?)";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, keyPass);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String obtenerPasswordByUsername(String user) {

        String sql = "SELECT password FROM usuarios WHERE user = ? LIMIT 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String obtenerKeyPassByUsername(String user) {

        String sql = "SELECT key_pass FROM usuarios WHERE user = ? LIMIT 1";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("key_pass");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean actualizarUsuario(String user, String password, String keyPass) {

        String sql = "UPDATE usuarios SET password = ?, key_pass = ? WHERE user = ?";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, password);
            ps.setString(2, keyPass);
            ps.setString(3, user);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteUser(String user) {

        String sql = "DELETE FROM usuarios WHERE user = ?";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Usuario> obtenerUsuarios() {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, user, password, key_pass FROM usuarios";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String password = rs.getString("password");
                String keyPass = rs.getString("key_pass");

                lista.add(new Usuario(id, user, password, keyPass));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean validarKeyPass(int keyPass) {
        String sql = "SELECT 1 FROM usuarios WHERE key_pass = ?";
        try (Connection c = ConexionBDD.getConexion();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, keyPass);
            return ps.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> getAllUsernames() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT `user` FROM usuarios";

        try (Connection con = ConexionBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String u = rs.getString("user");
                if (u != null) {
                    users.add(u);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}