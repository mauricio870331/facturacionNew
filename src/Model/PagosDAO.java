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
import java.util.List;

/**
 *
 * @author Innova
 */
public class PagosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    //Listar
    public List<Pagos> getPagosList() throws SQLException {
        List<Pagos> list = new ArrayList<>();
        try {
            sql = "{call listarPagos()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Pagos p = new Pagos(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getFloat(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getInt(15), rs.getString(16), rs.getString(17), rs.getInt(18));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearPagos(int id_pago, int id_cliente, int id_cuenta, Date fecha, String observaciones, String nota_egreso,
            int id_tipo_pago, int id_comprobante_egreso, int id_recibo_caja, float valor, int id_factura_proveedor, String id_factura,
            String cuatro_digitos, String voucher, String transacion, int id_franquicia, String recepcionista, int id_hospedaje, String opc) throws SQLException {
//        System.out.println("" + id_pago + " " + id_cliente + " " + id_cuenta + " " + fecha + "" + observaciones + " " + nota_egreso
//                + " " + id_tipo_pago + " " + id_comprobante_egreso + " " + id_recibo_caja + " " + valor + " " + id_factura_proveedor + " " + id_factura + 
//                " " + cuatro_digitos + " " + voucher + " " + transacion + " " + opc);
        String creado = "";
        System.out.println("transacion  " + transacion);
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarPagos(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarPagos(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_pago);
                clstm.setInt(2, id_cliente);
                clstm.setInt(3, id_cuenta);
                clstm.setString(4, df.format(fecha));
                clstm.setString(5, observaciones);
                clstm.setString(6, nota_egreso);
                clstm.setInt(7, id_tipo_pago);
                if (id_comprobante_egreso > 0) {
                    clstm.setInt(8, id_comprobante_egreso);
                } else {
                    clstm.setString(8, null);
                }
                clstm.setInt(9, id_recibo_caja);
                clstm.setFloat(10, valor);
                if (id_comprobante_egreso > 0) {
                    clstm.setInt(11, id_factura_proveedor);
                } else {
                    clstm.setString(11, null);
                }
                clstm.setString(12, id_factura);
                clstm.setString(13, cuatro_digitos);
                clstm.setString(14, voucher);
                if (id_franquicia > 0) {
                    clstm.setInt(15, id_franquicia);
                } else {
                    clstm.setString(15, null);
                }
                clstm.setString(16, transacion);
                clstm.setString(17, recepcionista);
                clstm.setInt(18, id_hospedaje);
            } else {
                clstm.setInt(1, id_cliente);
                clstm.setInt(2, id_cuenta);
                clstm.setString(3, df.format(fecha));
                clstm.setString(4, observaciones);
                clstm.setString(5, nota_egreso);
                clstm.setInt(6, id_tipo_pago);
                if (id_comprobante_egreso > 0) {
                    clstm.setInt(7, id_comprobante_egreso);
                } else {
                    clstm.setString(7, null);
                }
                clstm.setInt(8, id_recibo_caja);
                clstm.setFloat(9, valor);
                if (id_comprobante_egreso > 0) {
                    clstm.setInt(10, id_factura_proveedor);
                } else {
                    clstm.setString(10, null);
                }
                clstm.setString(11, id_factura);
                clstm.setString(12, cuatro_digitos);
                clstm.setString(13, voucher);
                clstm.setString(14, transacion);
                if (id_franquicia > 0) {
                    clstm.setInt(15, id_franquicia);
                } else {
                    clstm.setString(15, null);
                }
                clstm.setString(16, recepcionista);
                clstm.setInt(17, id_hospedaje);
            }
            rs = clstm.executeQuery();
            while (rs.next()) {
                creado = rs.getString(1);
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
            creado = e.getMessage();
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarPagos(int id_pago) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarPagos(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_pago);
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
