/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Utils.Utils.getNombreProducto;
import static Utils.Utils.rs;
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
public class DetalleFacturaCotizacionDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<DetalleFacturaCotizacion> getDetallesFacturaCotizacionList() throws SQLException {
        List<DetalleFacturaCotizacion> list = new ArrayList<>();
        try {
            sql = "{call listarDetalleFacturaCotizacion()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                DetalleFacturaCotizacion dfc = new DetalleFacturaCotizacion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getFloat(8));
                list.add(dfc);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Listar 
    public List<DetalleFacturaCotizacion> getDetallesFacturaCotizacionListByIdFactura(String idFactura) throws SQLException {
        List<DetalleFacturaCotizacion> list = new ArrayList<>();
        try {
            sql = "{call listDetalleFactura(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, idFactura);
            rs = clstm.executeQuery();
            while (rs.next()) {
                float val = (float) 1.0;
                if (rs.getInt(3) > 0) {
                    val = rs.getFloat(6) * (float) rs.getInt(3);
                }
                DetalleFacturaCotizacion dfc = new DetalleFacturaCotizacion(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), Utils.Utils.getImpuesto(rs.getInt(5), rs.getFloat(6),
                        rs.getFloat(7)), rs.getFloat(6), getNombreProducto(rs.getString(4),
                        rs.getInt(2)),
                        rs.getFloat(7), (val- rs.getFloat(7)), rs.getInt(5), rs.getString(8));
                list.add(dfc);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearDetalleFacturaCotizacion(int id_detalle, int id_producto_servicio,
            int cantidad, String id_factura, int id_impuesto, float valor,
            String transacion, float descuento, String opc) throws SQLException {

        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarDetalleFacturaCotizacion(?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarDetalleFacturaCotizacion(?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_detalle);
                clstm.setInt(2, id_producto_servicio);
                clstm.setInt(3, cantidad);
                clstm.setString(4, id_factura);
                clstm.setInt(5, id_impuesto);
                clstm.setString(6, transacion);
                clstm.setFloat(7, valor);
                clstm.setFloat(8, descuento);
            } else {
                clstm.setInt(1, id_producto_servicio);
                clstm.setInt(2, cantidad);
                clstm.setString(3, id_factura);
                clstm.setInt(4, id_impuesto);
                clstm.setString(5, transacion);
                clstm.setFloat(6, valor);
                clstm.setFloat(7, descuento);
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

    //Eliminar 
    public boolean eliminarDetalleFacturaCotizacion(int id_detalle) throws SQLException {
        boolean creado = false;

        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarDetalleFacturaCotizacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_detalle);
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
