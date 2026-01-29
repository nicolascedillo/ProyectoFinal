/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.DatosDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeOpenDoorTimeView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class ChangeOpenDoorTimeController {
    
    private ChangeOpenDoorTimeView view;
    private DatosDAO datosDAO;
    
    public ChangeOpenDoorTimeController(ChangeOpenDoorTimeView view){
        this.view = view;
        this.datosDAO = new DatosDAO();
        setDefaultValue();
        this.view.getBtnCancelDoor().addActionListener(e -> cancelar());
        this.view.getBtnChangeDoor().addActionListener(e -> changeWaitingTime());
    }
    
    public void setDefaultValue() {

        Integer datoObj = datosDAO.getWaitingTimeDoor();
        String unit = datosDAO.getUnitTimeDoor();

        int dato = (datoObj != null) ? datoObj : 0;
        if (unit == null || unit.trim().isEmpty()) unit = unit;

        String valor = dato + " " + unit;
        view.getTxtActTimeDoor().setText(valor);
    }
    
    public void changeWaitingTime() {

        int time = (int) view.getSpnTimeDoor().getValue();
        String unit = view.getCbxUnitTimeDoor().getSelectedItem().toString();

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

        datosDAO.updateWaitingTimeDoor(time);
        datosDAO.updateUnitTimeDoor(unit);

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
