/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Principal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Innova
 */
public class ResolucionesFacturasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    //Listar 
    public List<ResolucionesFacturas> getResolucionesFacturasList() throws SQLException {
        List<ResolucionesFacturas> list = new ArrayList<>();        
        try {
            sql = "{call listarResolucionFactura()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ResolucionesFacturas rf = new ResolucionesFacturas(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getString(8), rs.getString(9));
                list.add(rf);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearResolucionesFactura(String id_resolucion, Date fecha, String prefijo, int consecutivo_inicial, int consecutivo_final, int id_tipo_resolucion, boolean activo, String id_empresa, String transacion, String opc) throws SQLException {
        String result = "";        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarResolucionFactura(?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarResolucionFactura(?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_resolucion);
                clstm.setString(2, df.format(fecha));
                clstm.setString(3, prefijo);
                clstm.setInt(4, consecutivo_inicial);
                clstm.setInt(5, consecutivo_final);
                clstm.setInt(6, id_tipo_resolucion);
                clstm.setBoolean(7, activo);
                clstm.setString(8, id_empresa);
                clstm.setString(9, transacion);
            } else {
                clstm.setString(1, id_resolucion);
                clstm.setString(2, df.format(fecha));
                clstm.setString(3, prefijo);
                clstm.setInt(4, consecutivo_inicial);
                clstm.setInt(5, consecutivo_final);
                clstm.setInt(6, id_tipo_resolucion);
                clstm.setBoolean(7, activo);
                clstm.setString(8, id_empresa);
                clstm.setString(9, transacion);
            }
            rs = clstm.executeQuery();
            if (rs.next()) {
                Utils.Utils.deleteNumberBill(Principal.idVendedor.getText(), 1);
                result = rs.getString(1);

            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane           
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return result;
    }

    //Eliminar 
    public boolean eliminarResolucionFactura(String id_resolucion) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarResolucionFactura(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_resolucion);
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
