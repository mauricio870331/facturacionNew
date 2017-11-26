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
public class EntidadesBancariasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<EntidadesBancarias> getEntidadesBancariasList() throws SQLException {
        List<EntidadesBancarias> list = new ArrayList<>();
        try {
            sql = "{call listarEntidadesBancarias()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                EntidadesBancarias eb = new EntidadesBancarias(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getString(5));
                list.add(eb);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearEntidadBancaria(int id_entidad_bancaria, String entidad, int codigo_banco, boolean cuenta_nacional, String transacion, String opc) throws SQLException {
        System.out.println("" + id_entidad_bancaria + " " + entidad + " " + codigo_banco + "" + cuenta_nacional + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarEntidadBancaria(?,?,?,?)}";
            } else {
                sql = "{call actualizarEntidadBancaria(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_entidad_bancaria);
                clstm.setString(2, entidad);
                clstm.setInt(3, codigo_banco);
                clstm.setBoolean(4, cuenta_nacional);
                clstm.setString(5, transacion);
            } else {
                clstm.setString(1, entidad);
                clstm.setInt(2, codigo_banco);
                clstm.setBoolean(3, cuenta_nacional);
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
        } finally {
            clstm.close();
            rs.close();
            Conexion.closeConexion();
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarEntidadBancaria(int id_entidad_bancaria) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarEntidadBancaria(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_entidad_bancaria);
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
