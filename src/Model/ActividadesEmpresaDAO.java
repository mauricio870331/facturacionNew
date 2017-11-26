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
public class ActividadesEmpresaDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<ActividadesEmpresas> getActividadesEmpresasList() throws SQLException {
        List<ActividadesEmpresas> list = new ArrayList<>();        
        try {
            sql = "{call listarActividadEmpresa()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ActividadesEmpresas av = new ActividadesEmpresas(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                list.add(av);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar
    public boolean crearActividadEmpresa(int id_actividad_empresa, String id_empresa, int id_actividad_economica, String transacion, String opc) throws SQLException {
        System.out.println("" + id_actividad_empresa + "" + id_empresa + " " + id_actividad_economica + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarActividadEmpresa(?,?,?)}";
            } else {
                sql = "{call actualizarActividadEmpresa(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_actividad_empresa);
                clstm.setString(2, id_empresa);
                clstm.setInt(3, id_actividad_economica);
                clstm.setString(4, transacion);
            } else {
                clstm.setString(1, id_empresa);
                clstm.setInt(2, id_actividad_economica);
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
    public boolean eliminarActividadEmpresa(String id_actividad_empresa) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarActividadEmpresa(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_actividad_empresa);
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
