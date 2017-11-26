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
public class TerminosDePagoDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<TerminosDePago> getTerminoPagoList() throws SQLException {
        List<TerminosDePago> list = new ArrayList<>();        
        try {
            sql = "{call listarTerminoPago()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                TerminosDePago tp = new TerminosDePago(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(tp);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearTerminoPago(int id_termino_pago, String nombre, String transacion, String opc) throws SQLException {
        System.out.println("" + id_termino_pago + " " + nombre + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarTerminoPago(?,?)}";
            } else {
                sql = "{call actualizarTerminoPago(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_termino_pago);
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
    public boolean eliminarTerminoPago(int id_termino_pago) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarTerminoPago(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_termino_pago);
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
