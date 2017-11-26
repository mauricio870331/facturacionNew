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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Innova
 */
public class RetencionesPagosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<RetencionesPagos> getRetencionesCompradoresList() throws SQLException {
        List<RetencionesPagos> list = new ArrayList<>();
        try {
            sql = "{call listarRetencionPago()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                RetencionesPagos rp = new RetencionesPagos(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5));
                list.add(rp);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearRetencionPago(int id_retencion_pagos, String if_factura, int id_retencion, float valor, String transacion, String opc) throws SQLException {
        System.out.println("" + id_retencion_pagos + " " + if_factura + " " + " " + id_retencion + " " + valor + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarRetencionPago(?,?,?,?)}";
            } else {
                sql = "{call actualizarRetencionPago(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_retencion_pagos);
                clstm.setString(2, if_factura);
                clstm.setInt(3, id_retencion);
                clstm.setFloat(4, valor);
                clstm.setString(5, transacion);
            } else {
                clstm.setString(1, if_factura);
                clstm.setInt(2, id_retencion);
                clstm.setFloat(3, valor);
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
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    public boolean crearRetencionPago(ArrayList<RetencionesPagos> list, String transaccion) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            list.forEach((l) -> {
                sql = "{call insertarRetencionPago(?,?,?,?)}";
                try {
                    clstm = cn.prepareCall(sql);
                    clstm.setString(1, l.getFactura().getIdFactura());
                    clstm.setInt(2, l.getRetencion().getIdRetencion());
                    clstm.setString(3, transaccion);
                    clstm.setFloat(4, l.getValor());                    
                    rs = clstm.executeQuery();
                    String result = "";
                    if (rs.next()) {
                        result = rs.getString(1);
                    }
                    System.out.println("result = " + result);//pendiente mostrar en joptionpane   
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            });
            cn.commit();
            creado = true;
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar
    public boolean eliminarRetencionPago(int id_retencion_pagos) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarRetencionPago(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_retencion_pagos);
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
