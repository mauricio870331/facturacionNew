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
public class UsuariosAppDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public UsuariosApp getUsuariosApp(String usuario, String Password) throws SQLException {
        UsuariosApp user = null;        
        try {
            sql = "{call existUser(?,?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, usuario);
            clstm.setString(2, Password);
            rs = clstm.executeQuery();
            while (rs.next()) {
                user = new UsuariosApp(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("error pollo" + e);
        }
        return user;
    }

    //Listar 
    public List<UsuariosApp> getUsuariosAppList() throws SQLException {
        List<UsuariosApp> list = new ArrayList<>();        
        try {
            sql = "{call listarUsuarioApp()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                UsuariosApp ua = new UsuariosApp(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
                list.add(ua);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearUsuarioApp(String id_usuario, int id_tipo_identificacion, String id_empresa, int id_rol, String password, String nombres, String apellidos, int id_estado_usuario, String transacion, String opc) throws SQLException {
        System.out.println("" + id_usuario + " " + id_tipo_identificacion + " " + id_empresa + " " + id_rol + " " + password + " " + nombres + " " + apellidos + " " + id_estado_usuario + " " + transacion + " " + opc);
        String creado = "";        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarUsuarioApp(?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarUsuarioApp(?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_usuario);
                clstm.setInt(2, id_tipo_identificacion);
                clstm.setString(3, id_empresa);
                clstm.setInt(4, id_rol);
                clstm.setString(5, password);
                clstm.setString(6, nombres);
                clstm.setString(7, apellidos);
                clstm.setInt(8, id_estado_usuario);
                clstm.setString(9, transacion);
            } else {
                clstm.setString(1, id_usuario);
                clstm.setInt(2, id_tipo_identificacion);
                clstm.setString(3, id_empresa);
                clstm.setInt(4, id_rol);
                clstm.setString(5, password);
                clstm.setString(6, nombres);
                clstm.setString(7, apellidos);
                clstm.setInt(8, id_estado_usuario);
                clstm.setString(9, transacion);
            }
            rs = clstm.executeQuery();
            if (rs.next()) {
                creado = rs.getString(1);
            }
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarUsuarioApp(String id_usuario) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarUsuarioApp(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_usuario);
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
