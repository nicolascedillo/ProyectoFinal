/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.DatosDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeAttemptsView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class ChangeAttemptsController {
    
    private ChangeAttemptsView view;
    private DatosDAO datosDAO;
    
    public ChangeAttemptsController(ChangeAttemptsView view){
        this.view = view;
        this.datosDAO = new DatosDAO();
        setDefaultAttempts();
        this.view.getBtnChange().addActionListener(e -> changeAttempts());
        this.view.getBtnCancel().addActionListener(e -> cancelar());
    }

    public void setDefaultAttempts() {

        int attempts = datosDAO.getAttempts();
        String intentos = String.valueOf(attempts);

        view.getTxtActAttempts().setText(intentos);
    }
    
    public void changeAttempts() {

        int valor = (int) view.getSpnNewAttempts().getValue();

        int opt = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de cambiar el número de intentos a " + valor + "?",
                "Confirmar cambio",
                JOptionPane.YES_NO_OPTION,
                3
        );

        if (opt != JOptionPane.YES_OPTION) {
            return;
        }

        datosDAO.updateAttempts(valor);

        JOptionPane.showMessageDialog(
                view,
                "Número de intentos actualizado correctamente",
                "Successful",
                1
        );
        setDefaultAttempts();
    }

    public void cancelar(){
        view.dispose();
        new MenuView().setVisible(true); 
    }
}
