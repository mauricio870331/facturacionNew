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
public class LicenciasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Licencias> getLicenciasList() throws SQLException {
        List<Licencias> list = new ArrayList<>();
        try {
            sql = "{call listarLicencia()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Licencias l = new Licencias(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearLicencia(String id_licencia, Date fecha, String transacion, int id_suscripcion, int estaciones, String opc) throws SQLException {
        System.out.println("" + id_licencia + " " + fecha + " " + transacion + " " + id_suscripcion + " " + estaciones + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarLicencia(?,?,?,?,?)}";
            } else {
                sql = "{call actualizarLicencia(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_licencia);
                clstm.setDate(2, (java.sql.Date) fecha);
                clstm.setString(3, transacion);
                clstm.setInt(4, id_suscripcion);
                clstm.setInt(5, estaciones);
            } else {
                clstm.setString(1, id_licencia);
                clstm.setDate(2, (java.sql.Date) fecha);
                clstm.setString(3, transacion);
                clstm.setInt(4, id_suscripcion);
                clstm.setInt(5, estaciones);
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
    public boolean eliminarLicencia(String id_licencia) throws SQLException {
        boolean creado = false;

        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarLicencia(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_licencia);
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
