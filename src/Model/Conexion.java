package Model;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection con = null;
    public static String TipoCon = "";

    //patron singleton
    public static Connection getConexion() {
        try {
            if (con == null) {
//                 String url = "jdbc:mysql://www.expresopalmira.com.co:3306/expresop_pymesapp?autoReconnect=true";
//                String pwd = "S1st3m4s";
//                String usr = "expresop_user";
                
                String url = "jdbc:mysql://127.0.0.1:3306/codigo_pymesapp?autoReconnect=true";
                String pwd = "123";
                String usr = "codigo";
//                if (TipoCon.equals("remota")) {
//                String url = "jdbc:mysql://67.210.244.206:3306/codigo_pymesapp?noAccessToProcedureBodies=true&autoReconnect=true";
//                String pwd = "P}3LdHI^+WMV";
//                String usr = "codigo_proyectos";
//                }
                String driver = "com.mysql.jdbc.Driver"; //el driver varia segun la DB que usemos
                //&useInformationSchema=true

                Class.forName(driver);
                //con = DriverManager.getConnection("jdbc:mysql://localhost/codigo_proyectos", "codigo_proyectos", "P}3LdHI^+WMV");
                con = DriverManager.getConnection(url, usr, pwd);
                System.out.println("conectado");
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }

    public static Connection closeConexion() {
        con = null;
        return con;
    }
}
