/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Innova
 */
public class LogsDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Logs> getLogsList() throws SQLException {
        List<Logs> list = new ArrayList<>();        
        try {
            sql = "{call listarLog()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Logs l = new Logs(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearLogs(int id_log, Date fecha, String id_usuario, String actividad, String id_empresa, String transacion, String opc) throws SQLException {
        System.out.println("" + id_log + " " + fecha + " " + id_usuario + " " + actividad + " " + id_empresa + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarLog(?,?,?,?,?)}";
            } else {
                sql = "{call actualizarLog(?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_log);
                clstm.setDate(2, (java.sql.Date) fecha);
                clstm.setString(3, id_usuario);
                clstm.setString(4, actividad);
                clstm.setString(5, id_empresa); 
                clstm.setString(6, transacion); 
            } else {
                clstm.setDate(1, (java.sql.Date) fecha);
                clstm.setString(2, id_usuario);
                clstm.setString(3, actividad);
                clstm.setString(4, id_empresa);  
                clstm.setString(5, transacion);
            }

            rs = clstm.executeQuery();
            String result = "";
            while (rs.next()) {
                result = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane

            creado = true;
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarLog(int id_log) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarLog(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_log);
            ResultSet rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane

            creado = true;
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return creado;
    }

}
