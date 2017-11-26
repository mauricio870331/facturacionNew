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
public class ClientesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    //Listar
    public List<Clientes> getClientesList(String param) throws SQLException {
        List<Clientes> list = new ArrayList<>();
        try {
            sql = "{call listarCliente()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getBoolean(11), rs.getBoolean(12), rs.getString(13), rs.getString(14), rs.getInt(15), rs.getString(16), rs.getInt(17), rs.getDate(18));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public String crearCliente(String id_cliente, String nombre_razon, String identificacion, String direccion, int id_ciudad, String correo, String telefono1, String telefono2,
            String fax, String celular, boolean cliente, boolean proveedor, String observaciones, String id_empresa, int id_tipo_identificaion, String transacion, int id_nacionalidad, Date fecha_nacimiento, String opc) throws SQLException {
        String result = "";
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_cliente);
                clstm.setString(2, nombre_razon);
                clstm.setString(3, identificacion);
                clstm.setString(4, direccion);
                clstm.setInt(5, id_ciudad);
                clstm.setString(6, correo);
                clstm.setString(7, telefono1);
                clstm.setString(8, telefono2);
                clstm.setString(9, fax);
                clstm.setString(10, celular);
                clstm.setBoolean(11, cliente);
                clstm.setBoolean(12, proveedor);
                clstm.setString(13, observaciones);
                clstm.setString(14, id_empresa);
                clstm.setInt(15, id_tipo_identificaion);
                clstm.setString(16, transacion);
                clstm.setInt(17, id_nacionalidad);
                if (fecha_nacimiento == null) {
                    clstm.setString(18, null);
                } else {
                    clstm.setString(18, sd.format(fecha_nacimiento));
                }
            } else {
                clstm.setString(1, nombre_razon);
                clstm.setString(2, identificacion);
                clstm.setString(3, direccion);
                clstm.setInt(4, id_ciudad);
                clstm.setString(5, correo);
                clstm.setString(6, telefono1);
                clstm.setString(7, telefono2);
                clstm.setString(8, fax);
                clstm.setString(9, celular);
                clstm.setBoolean(10, cliente);
                clstm.setBoolean(11, proveedor);
                clstm.setString(12, observaciones);
                clstm.setString(13, id_empresa);
                clstm.setInt(14, id_tipo_identificaion);
                clstm.setString(15, transacion);
                clstm.setInt(16, id_nacionalidad);
                if (fecha_nacimiento == null) {
                    clstm.setString(17, null);
                } else {
                    clstm.setString(17, sd.format(fecha_nacimiento));
                }
            }
            rs = clstm.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    //Eliminar 
    public boolean eliminarCliente(int id_cliente) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarCliente(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_cliente);
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

    public List<Clientes> getCompradorByVendedor(String id_empresa) throws SQLException {
        List<Clientes> list = new ArrayList<>();

        try {
            sql = "Select identificacion, nombre_razon from clientes where cliente = 1 and id_empresa = '" + id_empresa + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes(rs.getString(1), rs.getString(2));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

}
