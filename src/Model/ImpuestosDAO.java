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
public class ImpuestosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<Impuestos> getImpuestosList() throws SQLException {
        List<Impuestos> list = new ArrayList<>();        
        try {
            sql = "{call cargarImpuestos()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Impuestos i = new Impuestos(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getString(4));
                list.add(i);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearImpuesto(int id_impuesto, float porcentaje_impuesto, String detalle, String transacion, String opc) throws SQLException {
        System.out.println("" + id_impuesto + " " + porcentaje_impuesto + " " + detalle + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarImpuesto(?,?,?)}";
            } else {
                sql = "{call actualizarImpuesto(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_impuesto);
                clstm.setFloat(2, porcentaje_impuesto);
                clstm.setString(3, detalle);
                clstm.setString(4, transacion);
            } else {
                clstm.setFloat(1, porcentaje_impuesto);
                clstm.setString(2, detalle);
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
    public boolean eliminarImpuesto(int id_impuesto) throws SQLException {
        boolean creado = false;
        cn = Conexion.getConexion();
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarImpuesto(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_impuesto);
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


