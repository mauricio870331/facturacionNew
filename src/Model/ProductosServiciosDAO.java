/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
public class ProductosServiciosDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<ProductosServicios> getProductosServiciosList(String empresa) throws SQLException {
        List<ProductosServicios> list = new ArrayList<>();
        try {
            sql = "{call listarProductoServicio(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, empresa);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ProductosServicios ps = new ProductosServicios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6), rs.getBinaryStream(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getBoolean(11), rs.getString(12), rs.getString(13), rs.getString(14));
                list.add(ps);
            }
        } catch (SQLException e) {
            System.out.println("error de sql " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearProductosServicios(int id_producto_servicio, String nombre, String referencia, String descripcion, float costo_unidad,
            float precio_venta, String ruta, String garantia, int id_unidad_medida, int stock, boolean servicio, String codigo_barra, String transacion, String id_empresa, String opc) throws SQLException {
        System.out.println("" + id_producto_servicio + " " + nombre + " " + referencia + " " + descripcion + " " + costo_unidad + " " + precio_venta + " " + ruta
                + " " + garantia + " " + id_unidad_medida + " " + stock + " " + servicio + " " + codigo_barra + " " + transacion + " " + id_empresa + " " + opc);
        boolean creado = false;
        FileInputStream fis = null;
        File img1 = null;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarProductoServicio(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarProductoServicio(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_producto_servicio);
                clstm.setString(2, nombre);
                clstm.setString(3, referencia);
                clstm.setString(4, descripcion);
                clstm.setFloat(5, costo_unidad);
                clstm.setFloat(6, precio_venta);
                if (!ruta.equals("")) {
                    img1 = new File(ruta);
                    fis = new FileInputStream(img1);
                    clstm.setBinaryStream(7, fis, (int) img1.length());
                } else {
                    clstm.setBinaryStream(7, null);
                }
                clstm.setString(8, garantia);
                clstm.setInt(9, id_unidad_medida);
                clstm.setInt(10, stock);
                clstm.setBoolean(11, servicio);
                clstm.setString(12, codigo_barra);
                clstm.setString(13, transacion);
                clstm.setString(14, id_empresa);
            } else {
                clstm.setString(1, nombre);
                clstm.setString(2, referencia);
                clstm.setString(3, descripcion);
                clstm.setFloat(4, costo_unidad);
                clstm.setFloat(5, precio_venta);
                if (!ruta.equals("")) {
                    img1 = new File(ruta);
                    fis = new FileInputStream(img1);
                    clstm.setBinaryStream(6, fis, (int) img1.length());
                } else {
                    clstm.setBinaryStream(6, null);
                }
                clstm.setString(7, garantia);
                clstm.setInt(8, id_unidad_medida);
                clstm.setInt(9, stock);
                clstm.setBoolean(10, servicio);
                clstm.setString(11, codigo_barra);
                clstm.setString(12, transacion);
                clstm.setString(13, id_empresa);
            }
            rs = clstm.executeQuery();
            String result = "";
            while (rs.next()) {
                result = rs.getString(1);
            }
            System.out.println("result = " + result);//pendiente mostrar en joptionpane
            creado = true;
            cn.commit();
        } catch (FileNotFoundException | SQLException e) {
            System.out.println("error " + e);
        }
        return creado;
    }

    //Eliminar actividades economicas
    public boolean eliminarProductoServicio(int id_producto_servicio) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarProductoServicio(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_producto_servicio);
            rs = clstm.executeQuery();
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

}
