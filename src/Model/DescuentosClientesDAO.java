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
public class DescuentosClientesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<DescuentosClientes> getDescuentosClientesList() throws SQLException {
        List<DescuentosClientes> list = new ArrayList<>();
        try {
            sql = "{call listarDescuentoCliente()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                DescuentosClientes dc = new DescuentosClientes(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                list.add(dc);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearDescuentoCliente(int id_descuentos_clientes, int id_cliente, int id_descuento, String transacion, String opc) throws SQLException {
        System.out.println("" + id_descuentos_clientes + "" + id_cliente + " " + id_descuento + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarDescuentoCliente(?,?,?)}";
            } else {
                sql = "{call actualizarDescuentoCliente(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_descuentos_clientes);
                clstm.setInt(2, id_cliente);
                clstm.setInt(3, id_descuento);
                clstm.setString(4, transacion);
            } else {
                clstm.setInt(1, id_cliente);
                clstm.setInt(2, id_descuento);
                clstm.setString(3, transacion);
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
    public boolean eliminarDescuentoCliente(int id_descuentos_clientes) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarDescuentoCliente(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_descuentos_clientes);
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
