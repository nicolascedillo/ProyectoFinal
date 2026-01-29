/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.view.InicioView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ForgotPassView;
import est.ups.edu.ec.cedilloe_millerm_final.view.SingUpView;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class InicioController {

    private final InicioView view;
    private final UsuarioDAO usuarioDAO;

    public InicioController(InicioView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();

        this.view.getBtnLogIn().addActionListener(e -> login());
        this.view.getBtnSingUp().addActionListener(e -> abrirSignUp());
        this.view.getBtnForgotPassword().addActionListener(e -> forgotPassword());
    }

    private void login() {
        String user = view.getTxtUser().getText().trim();
        String pass = new String(view.getTxtPassword().getText());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos los campos", "Informacion", 1);
            return;
        }

        try {
            Integer id = usuarioDAO.validarLogin(user, pass);

            if (id != null) {
                view.dispose();
                new MenuView().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(view, "Credenciales incorrectas", "Error", 0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error",0);
        }
    }

    private void abrirSignUp() {
        view.dispose();
        new SingUpView().setVisible(true); 
    }

    private void forgotPassword() {
        view.dispose();
        new ForgotPassView().setVisible(true); 
    }
}
