/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
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
public class EmpresasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;

    //Listar 
    public List<Empresas> getEmpresasList() throws SQLException {
        List<Empresas> list = new ArrayList<>();
        try {
            sql = "{call listarEmpresa()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                Empresas v = new Empresas(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getBinaryStream(8),
                        rs.getString(9),
                        rs.getInt(10), rs.getBoolean(11), rs.getString(12), rs.getString(13), "");
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    public boolean crearVendedor(String id_empresa, String nombre_razon, String direccion, String telefono, String correo, String celular, boolean regimen_comun, String logo, String sitio_web, int id_ciudad, boolean estado, String info, String opc) throws SQLException {
//        System.out.println("" + id_empresa + " " + nombre_razon + " " + direccion + " " + telefono + " " + correo + " " + celular + " " + regimen_comun + " " + logo + " " + sitio_web + " " + id_ciudad + " " + estado + " " + opc);
        boolean creado = false;
        FileInputStream fis = null;
        File img1 = null;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarEmpresa(?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarEmpresa(?,?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setString(1, id_empresa);
                clstm.setString(2, nombre_razon);
                clstm.setString(3, direccion);
                clstm.setString(4, telefono);
                clstm.setString(5, correo);
                clstm.setString(6, celular);
                clstm.setBoolean(7, regimen_comun);
                if (!logo.equals("")) {
                    img1 = new File(logo);
                    fis = new FileInputStream(img1);
                    clstm.setBinaryStream(8, fis, (int) img1.length());
                } else {
                    clstm.setBinaryStream(8, null);
                }
                clstm.setString(9, sitio_web);
                clstm.setInt(10, id_ciudad);
                clstm.setBoolean(11, estado);
                clstm.setString(12, info);
            } else {
                clstm.setString(1, nombre_razon);
                clstm.setString(2, direccion);
                clstm.setString(3, telefono);
                clstm.setString(4, correo);
                clstm.setString(5, celular);
                clstm.setBoolean(6, regimen_comun);
                clstm.setString(7, logo);
                clstm.setString(8, sitio_web);
                clstm.setInt(9, id_ciudad);
                clstm.setBoolean(10, estado);
                clstm.setString(11, info);
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

    //Eliminar actividades economicas
    public boolean eliminarEmpresa(String id_empresa) throws SQLException {
        boolean creado = false;
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarEmpresa(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setString(1, id_empresa);
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
