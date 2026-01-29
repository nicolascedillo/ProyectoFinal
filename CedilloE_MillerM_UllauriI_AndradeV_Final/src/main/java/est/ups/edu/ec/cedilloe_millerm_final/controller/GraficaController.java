package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.dao.*;
import est.ups.edu.ec.cedilloe_millerm_final.model.*;
import est.ups.edu.ec.cedilloe_millerm_final.view.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficaController {

    private final GraficaView view;
    private final LogDAO logDAO;
    private final UsuarioDAO usuarioDAO;
    private final SimpleDateFormat formato;

    public GraficaController(GraficaView view) {
        this.view = view;
        this.logDAO = new LogDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        view.getBtnCancel().addActionListener(e -> volver());
    }

    public void generarGrafica(Date ini, Date fin) {
        if (ini == null || fin == null || ini.after(fin)) return;
        mostrar(crearDataset(formato.format(ini), formato.format(fin)));
    }

    private DefaultCategoryDataset crearDataset(String ini, String fin) {

        Map<String, int[]> conteo = new LinkedHashMap<>();

        for (Log l : logDAO.obtenerLogsPorRango(ini, fin)) {
            String u = Optional.ofNullable(l.getUsuario())
                    .filter(s -> !s.isBlank())
                    .orElse("<sin usuario>").trim();

            conteo.putIfAbsent(u, new int[2]);

            String a = Optional.ofNullable(l.getAcceso()).orElse("").toUpperCase();
            if (a.contains("OK")) conteo.get(u)[0]++;
            else if (a.contains("FAIL")) conteo.get(u)[1]++;
        }

        List<String> usuarios = new ArrayList<>();
        usuarioDAO.obtenerUsuarios().forEach(u -> {
            if (u.getUser() != null && !u.getUser().isBlank())
                usuarios.add(u.getUser().trim());
        });
        if (usuarios.isEmpty()) usuarios.addAll(conteo.keySet());

        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        usuarios.forEach(u -> {
            int[] v = conteo.getOrDefault(u, new int[2]);
            ds.addValue(v[0], "OK", u);
            ds.addValue(v[1], "FAIL", u);
        });

        return ds;
    }

    private void mostrar(DefaultCategoryDataset ds) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Logs por Usuario", "Usuarios", "Intentos",
                ds, PlotOrientation.VERTICAL, true, true, false
        );

        CategoryPlot p = chart.getCategoryPlot();
        p.setBackgroundPaint(new Color(240, 240, 240));
        p.setRangeGridlinePaint(Color.GRAY);
        p.setOutlineVisible(false);

        CategoryAxis x = p.getDomainAxis();
        x.setLowerMargin(0);
        x.setUpperMargin(0);
        x.setCategoryMargin(0);
        x.setTickMarksVisible(false);
        x.setAxisLineVisible(false);

        StackedBarRenderer r = (StackedBarRenderer) p.getRenderer();
        r.setSeriesPaint(0, new Color(79, 179, 79));
        r.setSeriesPaint(1, new Color(220, 50, 50));
        r.setMaximumBarWidth(0.12);
        r.setShadowVisible(false);
        r.setDrawBarOutline(false);
        
        r.setDefaultItemLabelsVisible(true);
        r.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        r.setDefaultItemLabelFont(new Font("Arial", Font.BOLD, 12));
        r.setDefaultItemLabelPaint(Color.BLACK);


        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(900, 500));

        SwingUtilities.invokeLater(() -> {
            JPanel c = view.getPanelChart();
            c.removeAll();
            c.setLayout(new BorderLayout());
            c.add(panel, BorderLayout.CENTER);
            c.revalidate();
            c.repaint();
        });
    }

    private void volver() {
        view.dispose();
        new MenuView().setVisible(true);
    }
}