/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.model.Usuario;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ReadUserView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateo
 */
public class ReadUserController {
    
    private ReadUserView view;
    private UsuarioDAO usuarioDAO;
    
    public ReadUserController(ReadUserView view){
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        setDefaultValues();
        this.view.getBtnGoBack().addActionListener(e -> goBack());
    }
    
    public void setDefaultValues() {

        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();

        DefaultTableModel model = (DefaultTableModel) view.getTblUsers().getModel();
        model.setRowCount(0);

        for (Usuario u : usuarios) {
            model.addRow(new Object[]{
                u.getId(),
                u.getUser(),
                u.getPassword(),
                u.getKeyPass()
            });
        }
    }

    public void goBack(){
        view.dispose();
        new MenuView().setVisible(true); 
    }
}
