/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.model;

/**
 *
 * @author Mateo
 */
public class Usuario {
    
    private int id;
    private String user;
    private String password;
    private String keyPass;
    
    public Usuario(){}

    public Usuario(int id, String user, String password, String keyPass) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.keyPass = keyPass;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public String getKeyPass() { return keyPass; }
    public void setKeyPass(String keyPass) { this.keyPass = keyPass; }
       
}