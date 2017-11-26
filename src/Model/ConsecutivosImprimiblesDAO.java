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
public class ConsecutivosImprimiblesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<ConsecutivosImprimibles> getConsecutivosImprimiblesList() throws SQLException {
        List<ConsecutivosImprimibles> list = new ArrayList<>();
        try {
            sql = "{call listarConsecutivoImprimible()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ConsecutivosImprimibles ci = new ConsecutivosImprimibles(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                list.add(ci);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearConsecutivoImprimible(int id_consecutivo, String prefijo, int consecutivo, int id_tipo_imprimible, String id_empresa, String transacion, String opc) throws SQLException {
        String creado = "";
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarConsecutivoImprimible(?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarConsecutivoImprimible(?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_consecutivo);
                clstm.setString(2, prefijo);
                clstm.setInt(3, consecutivo);
                clstm.setInt(4, id_tipo_imprimible);
                clstm.setString(5, id_empresa);
                clstm.setString(6, transacion);
            } else {
                clstm.setInt(1, id_consecutivo);
                clstm.setString(2, prefijo);
                clstm.setInt(3, consecutivo);
                clstm.setInt(4, id_tipo_imprimible);
                clstm.setString(5, id_empresa);
                clstm.setString(6, transacion);
            }

            rs = clstm.executeQuery();
            String result = "";
            while (rs.next()) {
                creado = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane           
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarConsecutivoImprimible(int id_consecutivo) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarConsecutivoImprimible(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_consecutivo);
            ResultSet rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane

            creado = true;
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return creado;
    }

}
