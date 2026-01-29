/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.DatosDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeWaitingTimeView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class ChangeWaitingTimeController {
    
    private ChangeWaitingTimeView view;
    private DatosDAO datosDAO;
    
    public ChangeWaitingTimeController(ChangeWaitingTimeView view){
        this.view = view;
        this.datosDAO = new DatosDAO();
        setDefaultValue();
        this.view.getBtnChange().addActionListener(e -> changeWaitingTime());
        this.view.getBtnCancel().addActionListener(e -> cancelar());
    }
    
    public void setDefaultValue() {

        Integer datoObj = datosDAO.getWaitingTime();
        String unit = datosDAO.getUnitTime();

        int dato = (datoObj != null) ? datoObj : 0;
        if (unit == null || unit.trim().isEmpty()) unit = unit;

        String valor = dato + " " + unit;
        view.getTxtActTime().setText(valor);
    }

    
    public void changeWaitingTime() {

        int time = (int) view.getSpnTime().getValue();
        String unit = view.getCbxUnitTime().getSelectedItem().toString();

        int opt = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de cambiar el tiempo de espera a " + time + " " + unit + "?",
                "Confirmar cambio",
                JOptionPane.YES_NO_OPTION,
                3
        );

        if (opt != JOptionPane.YES_OPTION) {
            return;
        }

        datosDAO.updateWaitingTime(time);
        datosDAO.updateUnitTime(unit);

        JOptionPane.showMessageDialog(
                view,
                "Tiempo de espera actualizado correctamente",
                "Éxito",
                1
        );
        
        setDefaultValue();
    }

    public void cancelar(){
        view.dispose();
        new MenuView().setVisible(true); 
    }
    
}
