/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.LogDAO;
import est.ups.edu.ec.cedilloe_millerm_final.model.Log;
import est.ups.edu.ec.cedilloe_millerm_final.view.GraficaView;
import est.ups.edu.ec.cedilloe_millerm_final.view.MenuView;
import est.ups.edu.ec.cedilloe_millerm_final.view.ReadLogsView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateo
 */

public class ReadLogsController {

    private ReadLogsView view;
    private final LogDAO logDAO;
    private Date startDate;
    private Date endDate;
    
    public ReadLogsController(ReadLogsView view) {
        this.view = view;
        this.logDAO = new LogDAO();

        this.view.getBtnGoBack().addActionListener(e -> goBack());
        this.view.getBtnSearch().addActionListener(e -> searchLog());
        this.view.getBtnGrafica().addActionListener(e -> grafica());
    }
    
    public ReadLogsController(){
        this.logDAO = new LogDAO();
    }

    public void searchLog() {
        
        startDate = view.getJdDate_start().getDate();
        endDate = view.getJdDate_end().getDate();

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione Start y End.",
                    "Error",
                    0
            );
            return;
        }

        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(
                    view,
                    "Start no puede ser mayor que End.",
                    "Error",
                    0
            );
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String startStr = sdf.format(startDate);
        String endStr = sdf.format(endDate);

        System.out.println("START: " + startStr);
        System.out.println("END  : " + endStr);

        List<Log> logs = logDAO.obtenerLogsPorRango(startStr, endStr);

        DefaultTableModel model = (DefaultTableModel) view.getTblLogs().getModel();
        model.setRowCount(0);

        for (Log l : logs) {
            model.addRow(new Object[]{
                l.getId(),
                l.getUsuario(),
                l.getFechaHora(),
                l.getAcceso()
            });
        }

        if (logs.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "No se encontraron logs en el rango seleccionado.",
                    "Sin resultados",
                    1
            );
        }
    }
    
    public Date getStartDate() {
        return startDate == null ? null : new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return endDate == null ? null : new Date(endDate.getTime());
    }

    public void goBack() {
        view.dispose();
        new MenuView().setVisible(true);
    }

    private void grafica() {

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(view, "Pulse en la 🔍 antes de ver la gráfica.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GraficaView grafView = new GraficaView();
        grafView.setVisible(true);

        try {
            grafView.getController().generarGrafica(getStartDate(), getEndDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "No se pudo generar la gráfica: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        view.dispose();
    }
}