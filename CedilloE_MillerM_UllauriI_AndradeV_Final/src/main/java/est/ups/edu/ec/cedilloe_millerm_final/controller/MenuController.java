/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package est.ups.edu.ec.cedilloe_millerm_final.controller;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeAttemptsView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeOpenDoorTimeView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ChangeWaitingTimeView;
import est.ups.edu.ec.cedilloe_millerm_final.view.CreateUserView;
import est.ups.edu.ec.cedilloe_millerm_final.view.DeleteUserView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ForgotPassView;
import est.ups.edu.ec.cedilloe_millerm_final.view.InicioView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ReadLogsView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ReadUserView;
import est.ups.edu.ec.cedilloe_millerm_final.view.UpdateUserView;

/**
 *
 * @author Mateo
 */
public class MenuController {
    
    private final MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        
        //Usuario
        this.view.getBtnCreateUser().addActionListener(e -> createUser());
        this.view.getBtnReadUsers().addActionListener(e -> readUsers());
        this.view.getBtnUpdateUser().addActionListener(e -> updateUser());
        this.view.getBtnDeleteUser().addActionListener(e -> deleteUser());
        
        //Global
        this.view.getBtnChangeWaitingTime().addActionListener(e -> changeWaitingTime());
        this.view.getBtnChangeAttempts().addActionListener(e -> changeAttempts());
        this.view.getBtnChangeDoorTime().addActionListener(e -> changeOpenDoorTime());
        
        //Logs
        this.view.getBtnViewLogs().addActionListener(e -> readLogs());
        
        //Log Out
        this.view.getBtnLogOut().addActionListener(e -> logOut());
    }
    
    public void createUser(){
        view.dispose();
        new CreateUserView().setVisible(true); 
    }
    
    public void readUsers(){
        view.dispose();
        new ReadUserView().setVisible(true); 
    }
        
    public void updateUser(){
        view.dispose();
        new UpdateUserView().setVisible(true);   
    }      
    
    public void deleteUser(){
        view.dispose();
        new DeleteUserView().setVisible(true);  
    }
    
    public void changeWaitingTime(){
        view.dispose();
        new ChangeWaitingTimeView().setVisible(true);   
    }
    
    public void changeAttempts(){
        view.dispose();
        new ChangeAttemptsView().setVisible(true);    
    }
    
    public void changeOpenDoorTime(){
        view.dispose();
        new ChangeOpenDoorTimeView().setVisible(true);
    }
    
    public void readLogs(){
        view.dispose();
        new ReadLogsView().setVisible(true);   
    }
    
    public void logOut(){
        view.dispose();
        new InicioView().setVisible(true);
    }
}