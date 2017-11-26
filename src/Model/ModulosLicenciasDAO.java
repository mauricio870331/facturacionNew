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
public class ModulosLicenciasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    
    
     //Listar modulos
    public List<ModulosLicencias> getModulos(String usuario) throws SQLException {
        List<ModulosLicencias> modulos = new ArrayList<>();        
        try {
            sql = "{call cargarModulos(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, usuario);          
            rs = clstm.executeQuery();
            while (rs.next()) {
                modulos.add(new ModulosLicencias(rs.getInt(1)));                
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return modulos;
    }
    
    

    //Listar 
    public List<ModulosLicencias> getModulosLicenciasList() throws SQLException {
        List<ModulosLicencias> list = new ArrayList<>();        
        try {
            sql = "{call listarDepartamento()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                ModulosLicencias ml = new ModulosLicencias(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(ml);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearModuloLicencia(int id, int id_modulo, String id_licencia, String transacion, String opc) throws SQLException {
        System.out.println("" + id + " " + id_modulo + " " + id_licencia + " " + transacion + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarModuloLicencia(?,?,?)}";
            } else {
                sql = "{call actualizarModuloLicencia(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id);
                clstm.setInt(2, id_modulo);   
                clstm.setString(3, id_licencia);
                clstm.setString(4, transacion);
                
            } else {
                clstm.setInt(1, id_modulo);   
                clstm.setString(2, id_licencia); 
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
    public boolean eliminarModuloLicencia(int id) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarModuloLicencia(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id);
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
