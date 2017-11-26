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
public class ComprobantesEgresoDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<ComprobantesEgreso> getComprobantesEgresoList() throws SQLException {
        List<ComprobantesEgreso> list = new ArrayList<>();        
        try {
            sql = "{call listarComprobanteEgreso()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ComprobantesEgreso ce = new ComprobantesEgreso(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getFloat(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12));
                list.add(ce);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearComprobanteEgreso(int id_comprobante_egreso, int consecutivo, int id_ciudad, Date fecha, float valor, String concepto, String beneficiario,
            String id_empresa, int id_tipo_pago, int id_entidad_bancaria, String cheque, String transacion, String opc) throws SQLException {
        System.out.println("" + id_comprobante_egreso + " " + consecutivo + " " + id_ciudad + " " + fecha + " " + valor + " " + concepto + " " + beneficiario + " " + id_empresa + " " + id_tipo_pago + " " + id_entidad_bancaria + " " + cheque + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarCiudad(?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarCiudad(?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_comprobante_egreso);
                clstm.setInt(2, consecutivo);
                clstm.setInt(3, id_ciudad);
                clstm.setDate(4, (java.sql.Date) fecha);
                clstm.setFloat(5, valor);
                clstm.setString(6, concepto);
                clstm.setString(7, beneficiario);
                clstm.setString(8, id_empresa);
                clstm.setInt(9, id_tipo_pago);
                clstm.setInt(10, id_entidad_bancaria);
                clstm.setString(11, cheque);
                clstm.setString(12, transacion);
            } else {
                clstm.setInt(1, consecutivo);
                clstm.setInt(2, id_ciudad);
                clstm.setDate(3, (java.sql.Date) fecha);
                clstm.setFloat(4, valor);
                clstm.setString(5, concepto);
                clstm.setString(6, beneficiario);
                clstm.setString(7, id_empresa);
                clstm.setInt(8, id_tipo_pago);
                clstm.setInt(9, id_entidad_bancaria);
                clstm.setString(10, cheque);
                clstm.setString(11, transacion);
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
    public boolean eliminarComprobanteEgreso(int id_comprobante_egreso) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarComprobanteEgreso(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_comprobante_egreso);
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

