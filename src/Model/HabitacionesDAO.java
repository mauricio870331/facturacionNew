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
public class HabitacionesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<Habitaciones> getHabitacionesList() throws SQLException {
        List<Habitaciones> list = new ArrayList<>();        
        try {
            sql = "{call listarHabitacion()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Habitaciones h = new Habitaciones(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8));
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearHabitacion(int id_habitacion, String nombre, int cama_doble, int cama_sencilla, int total_camas, String transacion, int id_tipo_habitacion, boolean disponible, String opc) throws SQLException {
        System.out.println("" + id_habitacion + " " + nombre + " " + cama_doble + "" + cama_sencilla + "" + total_camas + "" + transacion + "" + id_tipo_habitacion + "" + disponible + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarHabitacion(?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarHabitacion(?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_habitacion);
                clstm.setString(2, nombre);
                clstm.setInt(3, cama_doble);
                clstm.setInt(4, cama_sencilla);
                clstm.setInt(5, total_camas);
                clstm.setString(6, transacion);
                clstm.setInt(7, id_tipo_habitacion);
                clstm.setBoolean(8, disponible);
            } else {
                clstm.setString(1, nombre);
                clstm.setInt(2, cama_doble);
                clstm.setInt(3, cama_sencilla);
                clstm.setInt(4, total_camas);
                clstm.setString(5, transacion);
                clstm.setInt(6, id_tipo_habitacion);
                clstm.setBoolean(7, disponible);
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
    public boolean eliminarHabitacion(int id_habitacion) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarHabitacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_habitacion);
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
