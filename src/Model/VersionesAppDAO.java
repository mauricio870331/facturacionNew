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
public class VersionesAppDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<VersionesApp> getVersionesAppList() throws SQLException {
        List<VersionesApp> list = new ArrayList<>();
        try {
            sql = "{call listarVersionApp()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                VersionesApp va = new VersionesApp(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(va);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearVersionApp(int id_version, String nombre, String descripcion, String opc) throws SQLException {
        System.out.println("" + id_version + " " + nombre + " " + descripcion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarVersionApp(?,?)}";
            } else {
                sql = "{call actualizarVersionApp(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_version);
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
    public boolean eliminarVersionApp(int id_version) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarVersionApp(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_version);
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
