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
public class CotizacionesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Cotizaciones> getCotizacionesList() throws SQLException {
        List<Cotizaciones> list = new ArrayList<>();        
        try {
            sql = "{call listarCotizacion()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Cotizaciones c = new Cotizaciones(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            cn.close();
        }
        return list;
    }

    //Crear y editar 
    public boolean crearCotizaciones(String id_cotizacion, Date fecha, String id_comprador, String id_vendedor, String id_usuario, int validez_dias, String opc) throws SQLException {
        System.out.println("" + id_cotizacion + " " + fecha + " " + id_comprador + " " + id_vendedor + " " + id_usuario + " " + validez_dias + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarCotizacion(?,?,?,?,?)}";
            } else {
                sql = "{call actualizarCotizacion(?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_cotizacion);
                clstm.setDate(2, (java.sql.Date) fecha);
                clstm.setString(3, id_comprador);
                clstm.setString(4, id_vendedor);
                clstm.setString(5, id_usuario);
                clstm.setInt(6, validez_dias);                
            } else {
                clstm.setDate(1, (java.sql.Date) fecha);
                clstm.setString(2, id_comprador);
                clstm.setString(3, id_vendedor);
                clstm.setString(4, id_usuario);
                clstm.setInt(5, validez_dias);                  
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
    public boolean eliminarCotizacion(String id_cotizacion) throws SQLException {
        boolean creado = false;
        cn = Conexion.getConexion();
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarCotizacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_cotizacion);
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
