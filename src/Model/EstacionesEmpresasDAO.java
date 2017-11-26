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
public class EstacionesEmpresasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<EstacionesEmpresas> getEstacionesVendedoresList() throws SQLException {
        List<EstacionesEmpresas> list = new ArrayList<>();       
        try {
            sql = "{call listarEstacionVendedor()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                EstacionesEmpresas ev = new EstacionesEmpresas(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(ev);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearEstacionVendedor(int id, int id_app_vendedor, String identificador_estacion, String transacion, String opc) throws SQLException {
        System.out.println("" + id + " " + id_app_vendedor + " " + identificador_estacion + " " + transacion + " " +opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarEstacionVendedor(?,?,?)}";
            } else {
                sql = "{call actualizarEstacionVendedor(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id);
                clstm.setInt(2, id_app_vendedor);    
                clstm.setString(3, identificador_estacion); 
                clstm.setString(4, transacion); 
            } else {
                clstm.setInt(1, id_app_vendedor);    
                clstm.setString(2, identificador_estacion); 
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
    public boolean eliminarEstacionVendedor(int id) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarEstacionDepartamento(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id);
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
