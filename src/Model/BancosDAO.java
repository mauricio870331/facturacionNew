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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Innova
 */
public class BancosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<Bancos> getBancosList() throws SQLException {
        List<Bancos> list = new ArrayList<>();
        try {
            sql = "{call listarBanco()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Bancos b = new Bancos(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearBanco(int id_banco, int id_cuenta, String nombre_cuenta, String numero_cuenta, float saldo, Date fecha, String descripcion, String id_empresa, String transacion, String opc) throws SQLException {
        System.out.println("" + id_banco + " " + id_cuenta + " " + nombre_cuenta + " " + numero_cuenta + " " + saldo + " " + fecha + " " + descripcion + "" + id_empresa + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarBanco(?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarBanco(?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_banco);
                clstm.setInt(2, id_cuenta);
                clstm.setString(3, nombre_cuenta);
                clstm.setString(4, numero_cuenta);
                clstm.setFloat(5, saldo);
                clstm.setDate(6, (java.sql.Date) fecha);
                clstm.setString(7, descripcion);
                clstm.setString(8, id_empresa);
                clstm.setString(9, transacion);

            } else {
                clstm.setInt(1, id_cuenta);
                clstm.setString(2, nombre_cuenta);
                clstm.setString(3, numero_cuenta);
                clstm.setFloat(4, saldo);
                clstm.setDate(5, (java.sql.Date) fecha);
                clstm.setString(6, descripcion);
                clstm.setString(7, id_empresa);
                clstm.setString(8, transacion);
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
    public boolean eliminarBanco(int id_banco) throws SQLException {
        boolean creado = false;

        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarBanco(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_banco);
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
