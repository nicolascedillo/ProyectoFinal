/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.model;

import java.time.LocalDateTime;

/**
 *
 * @author Mateo
 */
public class Log {

    private int id;
    private String usuario;
    private String fechaHora;
    private String acceso;

    public Log(int id, String usuario, String fechaHora, String accion) {
        this.id = id;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.acceso = accion;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getAcceso() {
        return acceso;
    }
}