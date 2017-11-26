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
public class AppEmpresasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<AppEmpresas> getAppEmpresasList() throws SQLException {
        List<AppEmpresas> list = new ArrayList<>();
        try {
            sql = "{call listarAppEmpresa()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                AppEmpresas av = new AppEmpresas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(av);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearAppEmpresa(int id_app_vendedor, String version_instalada, String id_licencia, String id_empresa, String transacion, String opc) throws SQLException {
        System.out.println("" + id_app_vendedor + " " + version_instalada + " " + id_licencia + " " + id_empresa + " " + transacion + " " + opc);
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarAppEmpresa(?,?,?,?.?)}";
            } else {
                sql = "{call actualizarAppEmpresa(?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_app_vendedor);
                clstm.setString(2, version_instalada);
                clstm.setString(3, id_licencia);
                clstm.setString(4, id_empresa);
                clstm.setString(5, transacion);
            } else {
                clstm.setInt(1, id_app_vendedor);
                clstm.setString(2, version_instalada);
                clstm.setString(3, id_licencia);
                clstm.setString(4, id_empresa);
                clstm.setString(5, transacion);
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
    public boolean eliminarAppEmpresa(int id_app_vendedor) throws SQLException {
        boolean creado = false;       
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarAppEmpresa(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_app_vendedor);
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
