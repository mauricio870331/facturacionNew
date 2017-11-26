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
public class TiposDeHabitacionDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<TiposDeHabitacion> getTiposDeHabitacionList(String empresa) throws SQLException {
        List<TiposDeHabitacion> list = new ArrayList<>();        
        try {
            sql = "{call listarTipoDeHabitacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, empresa);
            rs = clstm.executeQuery();
            while (rs.next()) {
                TiposDeHabitacion th = new TiposDeHabitacion(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getString(7));
                list.add(th);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearTipoDeHabitacion(int id_tipo_habitacion, String nombre, float costo_persona, float costo_pareja, float costo_persona_adicional, String id_empresa, String transacion, String opc) throws SQLException {
        System.out.println("" + id_tipo_habitacion + "" + nombre + "" + costo_persona + "" + costo_pareja + "" + costo_persona_adicional + "" + id_empresa + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarTipoHabitacion(?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarTipoHabitacion(?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_tipo_habitacion);
                clstm.setString(2, nombre);
                clstm.setFloat(3, costo_persona);
                clstm.setFloat(4, costo_pareja);
                clstm.setFloat(5, costo_persona_adicional);
                clstm.setString(6, id_empresa);
                clstm.setString(7, transacion);
            } else {
                clstm.setString(1, nombre);
                clstm.setFloat(2, costo_persona);
                clstm.setFloat(3, costo_pareja);
                clstm.setFloat(4, costo_persona_adicional);
                clstm.setString(5, id_empresa);
                clstm.setString(6, transacion);
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
    public boolean eliminarTipoHabitacion(int id_tipo_habitacion) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarTipoHabitacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_tipo_habitacion);
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
