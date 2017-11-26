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
public class FacturasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DetalleFacturaCotizacionDAO detallefactraDao;

    //Listar 
    public List<Facturas> getFacturasList(String idEmpresa) throws SQLException {
        List<Facturas> list = new ArrayList<>();
        try {
            sql = "{call ListarFacturasN2(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, idEmpresa);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Facturas f = new Facturas();
                f.setIdFactura(rs.getString(1));
                f.setFecha(rs.getDate(2));
                f.getResolucion().setIdResolucion(rs.getString(3));
                f.getComprador().setIdCliente(rs.getInt(4));
                f.setSubtotal(rs.getFloat(5));
                f.setIva(rs.getFloat(6));
                f.setTotal(rs.getFloat(7));
                f.getEstado().setIdEstadoFactura(rs.getInt(8));
                f.getVendedor().setIdEmpresa(rs.getString(9));
                f.getUsuario().setIdUsuario(rs.getString(10));
                f.setValidezDias(rs.getInt(11));
                f.setVencimiento(rs.getDate(12));
                f.setNota(rs.getString(13));
                f.setTransacion(rs.getString(14));
                f.getEstado().setNombre(rs.getString(15));
                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Listar 
    public List<Facturas> getCotizacionesList(String idEmpresa) throws SQLException {
        List<Facturas> list = new ArrayList<>();
        try {
            sql = "{call listarCotizacion(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, idEmpresa);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Facturas f = new Facturas(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7),
                        rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getDate(12),
                        rs.getString(13), rs.getString(14), rs.getFloat(15), rs.getFloat(16));
                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearFacturas(String id_factura, Date fecha, String id_resolucion, int id_comprador, float subtotal, float iva, float total,
            int id_estado_factura, String id_vendedor, String id_usuario, int id_terminos_pago, Date vencimiento,
            String nota, String transacion, String opc, ArrayList<DetalleFacturaCotizacion> listDetalleFacturaCotizacion, int updateImprimible) throws SQLException {
//        System.out.println("" + id_factura + " " + fecha + " " + id_resolucion + " " + id_comprador + "  " + subtotal + " " + iva + " " + total
//                + " " + id_estado_factura + " " + id_vendedor + " " + id_usuario + "" + id_terminos_pago + " "
//                + " " + vencimiento + " " + nota + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarFactura(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarFactura(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_factura);
            clstm.setString(2, sd.format(fecha));
            clstm.setString(3, id_resolucion);
            clstm.setInt(4, id_comprador);
            clstm.setFloat(5, subtotal);
            clstm.setFloat(6, iva);
            clstm.setFloat(7, total);
            clstm.setInt(8, id_estado_factura);
            clstm.setString(9, id_vendedor);
            clstm.setString(10, id_usuario);
            clstm.setInt(11, id_terminos_pago);
            clstm.setString(12, df.format(vencimiento));
            clstm.setString(13, nota);
            clstm.setString(14, transacion);
            rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
                if (result.equals("Factura creada con exito")) {
                    detallefactraDao = new DetalleFacturaCotizacionDAO();
                    listDetalleFacturaCotizacion.forEach((DetalleFacturaCotizacion detalle) -> {
                        try {
                            detallefactraDao.crearDetalleFacturaCotizacion(0, detalle.getItem().getIdProductoServicio(),
                                    (int) detalle.getCantidad(), id_factura, detalle.getId_Impuesto(),
                                    detalle.getValor(), transacion, detalle.getDescuento(), "create");
                        } catch (SQLException ex) {
                            System.out.println("error " + ex);
                        }
                    });
                    if (Utils.Utils.updateConsecutivoImprimible(id_vendedor, updateImprimible)) {
                        System.out.println("actualizado consecutivo");
                    }
                }
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane
            creado = true;
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error factura dato" + e);
        }
        return creado;
    }

    //Eliminar 
    public boolean eliminarFactura(String id_factura) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarFactura(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_factura);
            ResultSet rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
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
    public boolean anularFacturas(String idfactura, String concepto, String Userlog, String idEmpresa) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call anularFactura(?,?,?,?,?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, idfactura);
            clstm.setString(2, concepto);
            clstm.setString(3, Userlog);
            clstm.setString(4, sd.format(new Date()));
            clstm.setString(5, Utils.Utils.getTransaccion(idEmpresa, "facturas_anuladas"));
            ResultSet rs2 = clstm.executeQuery();
            String result = "";
            if (rs2.next()) {
                result = rs2.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane
            creado = true;
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return creado;
    }

}
