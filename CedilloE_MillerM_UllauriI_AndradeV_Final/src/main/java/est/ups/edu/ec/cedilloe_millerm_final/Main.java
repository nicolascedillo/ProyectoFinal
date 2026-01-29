/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package est.ups.edu.ec.cedilloe_millerm_final;

import est.ups.edu.ec.cedilloe_millerm_final.config.ConexionBDD;
import est.ups.edu.ec.cedilloe_millerm_final.controller.ArduinoController;
import est.ups.edu.ec.cedilloe_millerm_final.view.InicioView;
import est.ups.edu.ec.cedilloe_millerm_final.view.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mateo
 */
public class Main {

    public static void main(String[] args) {
        
        System.out.println("Iniciar Vista");
        iniciarVista();
        System.out.println("Conexion BDD");
        //coneccionBDD();
        System.out.println("Conexion Arduino");
        //conexionArduino();
        
    };
    
    public static void iniciarVista(){
        SwingUtilities.invokeLater(() -> {
            InicioView view = new InicioView();
            view.setVisible(true);
        });
    }
    
    public static void coneccionBDD(){
        ConexionBDD.getConexion();
    }
    
    public static void conexionArduino(){
        
        ArduinoController controller = new ArduinoController();
        controller.iniciar();
    }
}
