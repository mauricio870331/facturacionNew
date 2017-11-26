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
public class RolesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Roles> getRolesList() throws SQLException {
        List<Roles> list = new ArrayList<>();        
        try {
            sql = "{call listarRol()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Roles r = new Roles(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearRol(int id_rol, String nombre, String descripcion, String opc) throws SQLException {
        System.out.println("" + id_rol + " " + nombre + " " + descripcion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarRol(?,?)}";
            } else {
                sql = "{call actualizarRol(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_rol);
                clstm.setString(2, nombre);
                clstm.setString(3, descripcion);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, descripcion);
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
    public boolean eliminarRol(int id_rol) throws SQLException {
        boolean creado = false;       
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarRol(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_rol);
            ResultSet rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane

            creado = true;
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        } 
        return creado;
    }

}
