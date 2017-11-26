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
public class FacturasProveedoresDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<FacturasProveedor> getFacturasProveedorList() throws SQLException {
        List<FacturasProveedor> list = new ArrayList<>();
        try {
            sql = "{call listarFacturasProveedor()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                FacturasProveedor fp = new FacturasProveedor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getFloat(7), rs.getString(8));
                list.add(fp);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearFacturasProveedor(int id_factura_proveedor, String id_proveedor, String observaciones, String numero_factura, Date fecha_factura,
            Date fecha_vencimiento, float valor, String transacion, String opc) throws SQLException {
        System.out.println("" + id_factura_proveedor + " " + id_proveedor + " " + observaciones + "" + numero_factura
                + " " + fecha_factura + " " + fecha_vencimiento + "" + valor + " " + transacion + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarFacturaProveedor(?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarFacturaProveedor(?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_factura_proveedor);
                clstm.setString(2, id_proveedor);
                clstm.setString(3, observaciones);
                clstm.setString(4, numero_factura);
                clstm.setDate(5, (java.sql.Date) fecha_factura);
                clstm.setDate(6, (java.sql.Date) fecha_vencimiento);
                clstm.setFloat(7, valor);
                clstm.setString(8, transacion);
            } else {
                clstm.setString(1, id_proveedor);
                clstm.setString(2, observaciones);
                clstm.setString(3, numero_factura);
                clstm.setDate(4, (java.sql.Date) fecha_factura);
                clstm.setDate(5, (java.sql.Date) fecha_vencimiento);
                clstm.setFloat(6, valor);
                clstm.setString(7, transacion);
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
    public boolean eliminarFacturaProveedor(int id_factura_proveedor) throws SQLException {
        boolean creado = false;
        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarFacturaProveedor(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_factura_proveedor);
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
