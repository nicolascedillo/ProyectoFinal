/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package est.ups.edu.ec.cedilloe_millerm_final.controller;

import est.ups.edu.ec.cedilloe_millerm_final.config.ConexionArduino;
import est.ups.edu.ec.cedilloe_millerm_final.dao.UsuarioDAO;
import est.ups.edu.ec.cedilloe_millerm_final.dao.DatosDAO;
import est.ups.edu.ec.cedilloe_millerm_final.dao.LogDAO;

/**
 *
 * @author Mateo
 */

public class ArduinoController {

    private ConexionArduino arduino;
    private UsuarioDAO usuarioDAO;
    private DatosDAO datosDAO;
    private LogDAO logsDAO;

    private int intentosRestantes;

    public ArduinoController() {
        arduino = new ConexionArduino("COM4", 9600);
        //COM4 compu miller
        usuarioDAO = new UsuarioDAO();
        datosDAO = new DatosDAO();
        logsDAO = new LogDAO();
        intentosRestantes = datosDAO.getAttempts();
    }

    public void iniciar() {

        if (!arduino.abrir()) {
            System.out.println("No se pudo abrir el puerto");
            return;
        }

        System.out.println("Conectado a Arduino");
        System.out.println("Esperando codigos...");

        new Thread(() -> {

            while (arduino.estaAbierto()) {
                try {
                    String dato = arduino.leerLinea();

                    if (dato == null || dato.isBlank()) continue;
                    if (!dato.startsWith("CODE:")) continue;

                    int codigo = Integer.parseInt(dato.substring(5).trim());
                    System.out.println("Codigo recibido: " + codigo);

                    boolean valido = usuarioDAO.validarKeyPass(codigo);

                    if (valido) {
                        logsDAO.insertLogSuccessful(codigo);
                        
                        int tiempoPuerta = convertirAMilisegundos(
                                datosDAO.getWaitingTimeDoor(),
                                datosDAO.getUnitTimeDoor()
                        );
                        arduino.enviar("OK:" + tiempoPuerta);
                        
                        Thread.sleep(tiempoPuerta);
                        arduino.enviar("RESET");

                        intentosRestantes = datosDAO.getAttempts();
                    } else {
                        logsDAO.insertLogMistake();
                        intentosRestantes--;
                        arduino.enviar("ERR:" + intentosRestantes);

                        if (intentosRestantes <= 0) {
                            int tiempoBloqueoMs = convertirAMilisegundos(
                                    datosDAO.getWaitingTime(),
                                    datosDAO.getUnitTime()
                            );
                            arduino.enviar("LOCK:" + (tiempoBloqueoMs / 1000));
                            intentosRestantes = datosDAO.getAttempts();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int convertirAMilisegundos(int valor, String unidad) {
        if (unidad == null) return valor * 1000;

        return switch (unidad.toLowerCase()) {
            case "seg" -> valor * 1000;
            case "min" -> valor * 60_000;
            case "hr"  -> valor * 3_600_000;
            case "day" -> valor * 86_400_000;
            default -> valor * 1000;
        };
    }
}