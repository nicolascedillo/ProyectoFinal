/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.CreateUserView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class CreateUserController {
    
    private final CreateUserView view;
    private final UsuarioDAO usuarioDAO;

    public CreateUserController(CreateUserView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        this.view.getBtnCreate().addActionListener(e -> crearUsuario());
        this.view.getBtnCancel().addActionListener(e -> cancelar());
        
    }
    
    public void crearUsuario() {

        String user = view.getTxtUser().getText().trim();
        String pass = view.getTxtPassword().getText().trim();
        String keyPass = view.getTxtKeyPass().getText().trim();

        if (user.isEmpty() || pass.isEmpty() || keyPass.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Debe completar todos los campos",
                    "Error",
                    0
            );
            return;
        }

        boolean existe = usuarioDAO.existeUsuario(user);

        if (existe) {
            JOptionPane.showMessageDialog(
                    view,
                    "El usuario ya existe.",
                    "Advertencia",
                    2
            );
            return;
        }
        
        if (!keyPass.matches("^\\d{3,}$")) {
            JOptionPane.showMessageDialog(
                    view,
                    "Key_pass debe contener solo números y minimo 3 digitos",
                    "Error",
                    0
            );
            return;
        }

        int opt = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de crear el usuario \"" + user + "\"?",
                "Confirmar creación",
                JOptionPane.YES_NO_OPTION,
                3
        );

        if (opt != JOptionPane.YES_OPTION) {
            return;
        }

        boolean ok = usuarioDAO.crearUsuario(user, pass, keyPass);

        if (ok) {
            JOptionPane.showMessageDialog(
                    view,
                    "Usuario creado correctamente",
                    "Éxito",
                    1
            );

            view.dispose();
            new MenuView().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(
                    view,
                    "No se pudo crear el usuario",
                    "Error",
                    0
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
