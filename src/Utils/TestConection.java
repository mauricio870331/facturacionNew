/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import App.Principal;

/**
 *
 * @author Mauricio Herrera
 */
public class TestConection implements Runnable {
    
    @Override
    public void run() {
        if (Utils.comprobarConexion()) {
            Principal.lblStatusConection.setText("Estado Conexion:  Conectado");
        } else {
            Principal.lblStatusConection.setText("Estado Conexion:  Desconectado");
        }
        Principal.lblPreloader.setVisible(false);
    }
    
}
