/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.DeleteUserView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class DeleteUserController {
    
    private DeleteUserView view;
    private final UsuarioDAO usuarioDAO;

    public DeleteUserController(DeleteUserView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        
        this.view.getBtnSearchUsr().addActionListener(e -> setUserData());
        this.view.getBtnDelete().addActionListener(e -> deleteUser());
        this.view.getBtnCancel1().addActionListener(e -> cancelar());
    }
    
    public void setUserData() {

        String user = view.getTxtUser1().getText().trim();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Ingrese el usuario para buscar.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String password = usuarioDAO.obtenerPasswordByUsername(user);
        String keyPass  = usuarioDAO.obtenerKeyPassByUsername(user);

        if (password == null || keyPass == null) {
            JOptionPane.showMessageDialog(view,
                    "Usuario no encontrado en la base de datos.",
                    "No encontrado",
                    JOptionPane.ERROR_MESSAGE);

            view.getTxtPassword().setText("");
            view.getTxtKeyPass().setText("");
            view.getTxtUser().setText("");
            return;
        }

        view.getTxtUser().setText(user);        
        view.getTxtPassword().setText(password);
        view.getTxtKeyPass().setText(keyPass);
    }

    public void deleteUser() {

        String user = view.getTxtUser().getText().trim();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Primero busque el usuario",
                    "Sin usuario cargado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de eliminar el usuario: " + user + " ?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean ok = usuarioDAO.deleteUser(user);

        if (ok) {
            JOptionPane.showMessageDialog(view,
                    "Usuario eliminado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            view.getTxtUser().setText("");
            view.getTxtPassword().setText("");
            view.getTxtKeyPass().setText("");
            view.getTxtUser1().setText("");

            view.dispose();
            new MenuView().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(view,
                    "No se pudo borrar. Puede que el usuario ya no exista.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cancelar(){
        view.dispose();
        new MenuView().setVisible(true); 
    }
}
