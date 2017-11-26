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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Innova
 */
public class HospedajesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd 15:00:00");

    //Listar
    public List<Hospedajes> getHospedajesList() throws SQLException {
        List<Hospedajes> list = new ArrayList<>();
        try {
            sql = "{call listarHospedaje()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Hospedajes h = new Hospedajes(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDate(9),
                        rs.getString(10), rs.getString(11), rs.getFloat(12), rs.getString(13));
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearHospedaje(int id_hospedaje, int id_motivo, Date fecha_ingreso, Date fecha_salida, int id_habitacion, int id_cliente, int numero_adultos,
            int numero_ninos, Date fecha_registro, String id_factura, String recepcionista, Float valor_noche, String transacion, String opc) throws SQLException {
//        System.out.println(id_hospedaje + " " + fecha_ingreso + " " + " " + fecha_salida + " " + id_habitacion + " " + " " + id_cliente + " " + numero_personas + " " + " " + transacion + " " + opc);
        String creado = "";
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarHospedaje(?,?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarHospedaje(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_hospedaje);
                clstm.setInt(2, id_motivo);
                clstm.setString(3, ff.format(fecha_ingreso));
                clstm.setString(4, ff.format(fecha_salida));
                clstm.setInt(5, id_habitacion);
                clstm.setInt(6, id_cliente);
                clstm.setInt(7, numero_adultos);
                clstm.setInt(8, numero_ninos);
                clstm.setString(9, sd.format(fecha_registro));
                clstm.setString(10, id_factura);
                clstm.setString(11, recepcionista);
                clstm.setFloat(12, valor_noche);
                clstm.setString(13, transacion);
            } else {
                clstm.setInt(1, id_motivo);
                clstm.setString(2, ff.format(fecha_ingreso));
                if (fecha_salida == null) {
                    clstm.setString(3, null);
                } else {
                    clstm.setString(3, ff.format(fecha_salida));
                }
                clstm.setInt(4, id_habitacion);
                clstm.setInt(5, id_cliente);
                clstm.setInt(6, numero_adultos);
                clstm.setInt(7, numero_ninos);
                clstm.setString(8, sd.format(fecha_registro));
                clstm.setString(9, id_factura);
                clstm.setString(10, recepcionista);
                clstm.setFloat(11, valor_noche);
                clstm.setString(12, transacion);
            }
            rs = clstm.executeQuery();
            if (rs.next()) {
                creado = rs.getString(1);
            }
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarHospedaje(int id_hospedaje) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarHospedaje(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_hospedaje);
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

    public boolean updateList(ArrayList<String> consultas) {
        boolean result = false;
        try {
            cn.setAutoCommit(false);
            Iterator<String> it = consultas.iterator();
            while (it.hasNext()) {
                String next = it.next();
                sql = next;
                pstm = cn.prepareStatement(sql);
                int rowAfectedR = pstm.executeUpdate();
                if (rowAfectedR > 0) {
                    result = true;
                }
            }
            cn.commit();
        } catch (SQLException ex) {
            System.out.println("error " + ex);;
        }
        return result;
    }

}
