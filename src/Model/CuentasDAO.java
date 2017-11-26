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
public class CuentasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<Cuentas> getCuentasList() throws SQLException {
        List<Cuentas> list = new ArrayList<>();        
        try {
            sql = "{call listarCuenta()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Cuentas c = new Cuentas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearCuenta(int id_cuenta, String nombre, String puc, String detalle, String transacion, String opc) throws SQLException {
        System.out.println("" + id_cuenta + " " + nombre + " " + puc + " " + detalle + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarCuenta(?,?,?,?)}";
            } else {
                sql = "{call actualizarCuenta(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_cuenta);
                clstm.setString(2, nombre);
                clstm.setString(3, puc);
                clstm.setString(4, detalle);
                clstm.setString(5, transacion);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, puc);
                clstm.setString(3, detalle);
                clstm.setString(4, transacion);
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
    public boolean eliminarCuenta(int id_cuenta) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarCuenta(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_cuenta);
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


