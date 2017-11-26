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
public class DescuentosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Descuentos> getDescuentosList() throws SQLException {
        List<Descuentos> list = new ArrayList<>();        
        try {
            sql = "{call listarDescuento()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Descuentos d = new Descuentos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                list.add(d);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearDescuento(int id_descuento, String nombre, String descripcion, int porcentaje_descuento, String transacion, String opc) throws SQLException {
        System.out.println("" + id_descuento + " " + nombre + " " + descripcion + " " + porcentaje_descuento + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarDescuento(?,?,?,?)}";
            } else {
                sql = "{call actualizarDescuento(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_descuento);
                clstm.setString(2, nombre);
                clstm.setString(3, descripcion);
                clstm.setInt(4, porcentaje_descuento);
                clstm.setString(5, transacion);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, descripcion);
                clstm.setInt(3, porcentaje_descuento);
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
    public boolean eliminarDescuento(int id_descuento) throws SQLException {
        boolean creado = false;
        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarDescuento(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_descuento);
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

