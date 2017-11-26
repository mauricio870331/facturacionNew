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
public class RetencionesEmpresasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<RetencionesEmpresas> getRetencionEmpresaList() throws SQLException {
        List<RetencionesEmpresas> list = new ArrayList<>();
        try {
            sql = "{call listarDepartamento()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                RetencionesEmpresas re = new RetencionesEmpresas(rs.getInt(1), rs.getInt(2), rs.getString(3));
                list.add(re);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearRetencionEmpresa(int id_retencion_empresa, int id_retencion, String id_empresa, String opc) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarRetencionEmpresa(?,?)}";
            } else {
                sql = "{call actualizarRetencionEmpresa(?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_retencion_empresa);
                clstm.setInt(2, id_retencion);
                clstm.setString(3, id_empresa);
            } else {
                clstm.setInt(1, id_retencion);
                clstm.setString(2, id_empresa);
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
    public boolean eliminarRetencionEmpresa(int id_retencion_empresa) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarRetencionEmpresa(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_retencion_empresa);
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
