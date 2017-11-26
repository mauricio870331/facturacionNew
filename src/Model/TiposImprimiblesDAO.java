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
public class TiposImprimiblesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<TiposImprimibles> getTipoImprimibleList() throws SQLException {        
        List<TiposImprimibles> list = new ArrayList<>();        
        try {             
            sql = "{call listarTipoImprimible()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {                 
                TiposImprimibles ti = new TiposImprimibles(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(ti);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearTipoImprimible(int id_tipo_imprimible, String nombre, String transacion, String opc) throws SQLException {
        System.out.println("" + id_tipo_imprimible + " " + nombre + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarTipoImprimible(?,?)}";
            } else {
                sql = "{call actualizarTipoImprimible(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_tipo_imprimible);
                clstm.setString(2, nombre);  
                clstm.setString(3, transacion); 
            } else {
                clstm.setString(1, nombre); 
                clstm.setString(2, transacion); 
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
    public boolean eliminarTipoImprimible(int id_tipo_imprimible) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarTipoImprimible(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_tipo_imprimible);
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

