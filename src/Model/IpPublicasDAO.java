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
public class IpPublicasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<IpPublicas> getIpPublicasList() throws SQLException {
        List<IpPublicas> list = new ArrayList<>();       
        try {
            sql = "{call listarIpPublicas()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                IpPublicas ip = new IpPublicas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(ip);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearIpPublicas(int id_ip, String id_empresa, String ip_publica, String transacion, String opc) throws SQLException {
        
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarIpPublicas(?,?,?)}";
            } else {
                sql = "{call actualizarIpPublicas(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_ip);
                clstm.setString(2, id_empresa);
                clstm.setString(3, ip_publica);
                clstm.setString(4, transacion);
            } else {
                clstm.setString(1, id_empresa);
                clstm.setString(2, ip_publica);
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
    public boolean eliminarIpPublicas(int id_ip) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarIpPublicas(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_ip);
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


