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
public class UnidadesMedidaDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<UnidadesMedida> getUnidadesMedidaList() throws SQLException {
        List<UnidadesMedida> list = new ArrayList<>();        
        try {
            sql = "{call listarUnidadMedida()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                UnidadesMedida um = new UnidadesMedida(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(um);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearUnidadMedida(int id_unidad_medida, String nombre, String descripcion, String opc) throws SQLException {
        System.out.println("" + id_unidad_medida + " " + nombre + " " + descripcion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarUnidadMedida(?,?)}";
            } else {
                sql = "{call actualizarUnidadMedida(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_unidad_medida);
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
    public boolean eliminarUnidadMedida(int id_unidad_medida) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarUnidadMedida(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_unidad_medida);
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

