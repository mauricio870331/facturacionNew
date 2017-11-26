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
public class CategoriasPagosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<CategoriasPagos> getCategoriasPagosList() throws SQLException {
        List<CategoriasPagos> list = new ArrayList<>();        
        try {
            sql = "{call listarCategoriaPago()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                CategoriasPagos cp = new CategoriasPagos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(cp);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearCategoriaPago(int id_categoria_pago, String nombre, String detalle, String transacion, String opc) throws SQLException {
        System.out.println("" + id_categoria_pago + " " + nombre + " " + detalle + " " + transacion + " " + opc);
        boolean creado = false;       
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarCategoriaPago(?,?,?)}";
            } else {
                sql = "{call actualizarCategoriaPago(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_categoria_pago);
                clstm.setString(2, nombre);
                clstm.setString(3, detalle);
                clstm.setString(4, detalle);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, detalle);
                clstm.setString(3, detalle);
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
    public boolean eliminarCategoriaPago(int id_categoria_pago) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarCategoriaPago(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_categoria_pago);
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

