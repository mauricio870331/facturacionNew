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
public class NacionalidadesDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar
    public List<Nacionalidades> getNacionalidadList() throws SQLException {
        List<Nacionalidades> list = new ArrayList<>();        
        try {
            sql = "{call listarNacionalidad()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Nacionalidades n = new Nacionalidades(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
        return list;
    }

    //Crear y editar 
    public boolean crearCiudad(int id_nacionalidad, String codigo, String pais, String transacion, String opc) throws SQLException {
//        System.out.println("" + id_ciudad + " " + nombre + " " + id_departamento + " " + opc);
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarNacionalidad(?,?,?)}";
            } else {
                sql = "{call actualizarNacionalidad(?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_nacionalidad);
                clstm.setString(2, codigo);
                clstm.setString(3, pais);
                clstm.setString(4, transacion);
            } else {               
                clstm.setString(1, codigo);
                clstm.setString(2, pais);
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
    public boolean eliminarNacionalidad(int id_nacionalidad) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarNacionalidad(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_nacionalidad);
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


