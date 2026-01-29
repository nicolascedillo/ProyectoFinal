/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import est.ups.edu.ec.cedilloe_millerm_final.view.UpdateUserView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class UpdateUserController {

    private final UpdateUserView view;
    private final UsuarioDAO usuarioDAO;

    public UpdateUserController(UpdateUserView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        this.view.getBtnSearchUsr().addActionListener(e -> setUserData());
        this.view.getBtnUpdate().addActionListener(e -> updateUser());
        this.view.getBtnCancel().addActionListener(e -> cancelar());
    }
    
    public void setUserData() {

        String user = view.getTxtUser().getText().trim();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Debe ingresar el usuario.",
                    "Error",
                    0);
            return;
        }

        String password = usuarioDAO.obtenerPasswordByUsername(user);

        if (password == null) {
            JOptionPane.showMessageDialog(view,
                    "Usuario no encontrado.",
                    "Error",
                    0);
            return;
        }
        
        view.getTxtPassword().setText(password);
        
        String ket_pass = usuarioDAO.obtenerKeyPassByUsername(user);

        if (ket_pass == null) {
            JOptionPane.showMessageDialog(view,
                    "Usuario no encontrado.",
                    "Error",
                    0);
            return;
        }

        view.getTxtKeyPass().setText(ket_pass);
    }
    
    public void updateUser() {

        String user = view.getTxtUser().getText().trim();
        String password = view.getTxtPassword().getText().trim();
        String keyPass = view.getTxtKeyPass().getText().trim();

    // VALIDACIONES
        if (user.isEmpty() || password.isEmpty() || keyPass.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "No deje campos vacíos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!keyPass.matches("^\\d{3,}$")) {
            JOptionPane.showMessageDialog(
                    view,
                    "Key_pass debe contener solo números y minimo 3 digitos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de actualizar los datos?\n\n"
                + "Usuario: " + user + "\n"
                + "Password: " + password + "\n"
                + "Key_pass: " + keyPass,
                "Confirmar actualización",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        boolean ok = usuarioDAO.actualizarUsuario(user, password, keyPass);

        if (ok) {
            JOptionPane.showMessageDialog(
                view,
                "Usuario actualizado correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                view,
                "No se pudo actualizar el usuario",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        view.getTxtUser().setText("");
        view.getTxtPassword().setText("");
        view.getTxtKeyPass().setText("");
    }

    public void cancelar(){
        view.dispose();
        new MenuView().setVisible(true); 
    }
}