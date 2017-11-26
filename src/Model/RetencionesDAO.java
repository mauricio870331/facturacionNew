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
import java.util.List;

/**
 *
 * @author Innova
 */
public class RetencionesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Retenciones> getRetencionesList(String empresa) throws SQLException {
        List<Retenciones> list = new ArrayList<>();        
        try {
            sql = "{call listarRetencion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, empresa);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Retenciones r = new Retenciones(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearRetencion(int id_retencion, String nombre, String descripcion, int tarifa, String transacion, String opc) throws SQLException {
        System.out.println("" + id_retencion + " " + nombre + " " + descripcion + " " + tarifa + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarRetencion(?,?,?,?)}";
            } else {
                sql = "{call actualizarRetencion(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_retencion);
                clstm.setString(2, nombre);
                clstm.setString(3, descripcion);
                clstm.setInt(4, tarifa);
                clstm.setString(5, transacion);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, descripcion);
                clstm.setInt(3, tarifa);
                clstm.setString(4, transacion);
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
    public boolean eliminarRetencion(int id_retencion) throws SQLException {
        boolean creado = false;
        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarRetencion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_retencion);
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
