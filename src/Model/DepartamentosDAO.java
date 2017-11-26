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
public class DepartamentosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Departamentos> getDepartamentosList() throws SQLException {
        List<Departamentos> list = new ArrayList<>();        
        try {
            sql = "{call listarDepartamento()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Departamentos d = new Departamentos(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(d);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearDepartamento(int id_departamento, String nombre, String opc) throws SQLException {
        System.out.println("" + id_departamento + " " + nombre + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarDepartamento(?)}";
            } else {
                sql = "{call actualizarDepartamento(?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_departamento);
                clstm.setString(2, nombre);                
            } else {
                clstm.setString(1, nombre);                
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
    public boolean eliminarDepartamento(int id_departamento) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarDepartamento(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_departamento);
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
