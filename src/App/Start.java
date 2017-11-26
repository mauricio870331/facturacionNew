/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;
//import Model.*;
//import Controllers.*;

import Controllers.LoginController;
import Controllers.PrincipalController;
import Controllers.getPrincipalController;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Mauricio
 */
public class Start {

//    private static CheckList ch = null;
//    private static Conexion cn = null;
//    private static Login lg = null;
    private static Principal pr = null;

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        try {
            Properties props = new Properties();
            props.put("logoString", "PymesApp");
            AcrylLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("error  " + e);
        }
        pr = GetPrincipal.getPrincipal();
        pr.setTitle("PymesApp Versión 0.6 compilación 05032017");
        pr.setLocationRelativeTo(null);
        pr.setVisible(true);
        PrincipalController principalController = getPrincipalController.PrincipalController();

    }

}
