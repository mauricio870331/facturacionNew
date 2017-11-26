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
public class DetalleFacturaProveedorDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<DetalleFacturaProveedor> getDetalleFacturaProveedorList() throws SQLException {
        List<DetalleFacturaProveedor> list = new ArrayList<>();
        try {
            sql = "{call listarDetalleFacturaProveedor()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                DetalleFacturaProveedor dfp = new DetalleFacturaProveedor(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7),
                        rs.getString(8));
                list.add(dfp);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearDetalleFacturaProveedor(int id_dfp, int id_factura_proveedor, float valor, int id_categoria_pago, int id_impuesto, int cantidad, String observaciones,
            String transacion, String opc) throws SQLException {
        System.out.println("" + id_dfp + " " + id_factura_proveedor + " " + valor + " " + id_categoria_pago + " " + id_impuesto + " " + cantidad + "" + observaciones + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarDetalleFacturaProveedor(?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarDetalleFacturaProveedor(?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_dfp);
                clstm.setInt(2, id_factura_proveedor);
                clstm.setFloat(3, valor);
                clstm.setInt(4, id_categoria_pago);
                clstm.setInt(5, id_impuesto);
                clstm.setInt(6, cantidad);
                clstm.setString(7, observaciones);
                clstm.setString(8, transacion);

            } else {
                clstm.setInt(1, id_factura_proveedor);
                clstm.setFloat(2, valor);
                clstm.setInt(3, id_categoria_pago);
                clstm.setInt(4, id_impuesto);
                clstm.setInt(5, cantidad);
                clstm.setString(6, observaciones);
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
    public boolean eliminarDetalleFacturaProveedor(int id_dfp) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarDetalleFacturaProveedor(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_dfp);
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
