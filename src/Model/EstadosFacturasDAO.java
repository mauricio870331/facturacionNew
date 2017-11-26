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
public class EstadosFacturasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<EstadosFacturas> getEstadosFacturasList() throws SQLException {
        List<EstadosFacturas> list = new ArrayList<>();        
        try {
            sql = "{call listarEstadoFactura()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                EstadosFacturas ef = new EstadosFacturas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(ef);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearEstadoFactura(int id_estado_factura, String nombre, String descripcion, String transacion, String opc) throws SQLException {
        System.out.println("" + id_estado_factura + " " + nombre + " " + descripcion + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarEstadoFactura(?,?,?)}";
            } else {
                sql = "{call actualizarEstadoFactura(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_estado_factura);
                clstm.setString(2, nombre);
                clstm.setString(2, descripcion);
                clstm.setString(3, transacion);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, descripcion);
                clstm.setString(3, transacion);
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
    public boolean eliminarEstadoFactura(int id_estado_factura) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarEstadoFactura(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_estado_factura);
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
