/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package est.ups.edu.ec.cedilloe_millerm_final.config;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Mateo
 */

public class ConexionArduino {

    private SerialPort port;
    private InputStream in;
    private OutputStream out;

    public ConexionArduino(String portName, int baudRate) {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(baudRate);
        port.setComPortTimeouts(
            SerialPort.TIMEOUT_READ_SEMI_BLOCKING,
            1000,
            0
        );
    }

    public boolean abrir() {
        if (port.openPort()) {
            in = port.getInputStream();
            out = port.getOutputStream();
            return true;
        }
        return false;
    }

    public void cerrar() {
        if (port != null && port.isOpen()) {
            port.closePort();
        }
    }

    public void enviar(String msg) throws Exception {
        out.write((msg + "\n").getBytes());
        out.flush();
    }
    
    public String leerLinea() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        try {
            while ((c = in.read()) != -1) {
                if (c == '\n') break;
                if (c != '\r')
                    sb.append((char) c);
            }
            if (sb.length() == 0)
                return null;
            return sb.toString();
        } catch (SerialPortTimeoutException ex) {
            return null;
        }
    }
    
    public boolean estaAbierto() {
        return port != null && port.isOpen();
    }
}