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
            sql = "Select * from empresas";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
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
    public boolean crearVendedor(String id_empresa, String nombre_razon, String direccion, String telefono, String correo, String celular, boolean regimen_comun, String logo, String sitio_web, int id_ciudad, boolean estado, String info, String opc, boolean ajustealpeso) throws SQLException {
//        System.out.println("" + id_empresa + " " + nombre_razon + " " + direccion + " " + telefono + " " + correo + " " + celular + " " + regimen_comun + " " + logo + " " + sitio_web + " " + id_ciudad + " " + estado + " " + opc);
        boolean creado = false;
        FileInputStream fis = null;
        File img1 = null;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "insert into empresas values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "update empresas set nombre_razon = ?, direccion = ?,"
                        + " telefono = ?, correo=?, celular =?, regimen_comun = ?, logo=?,"
                        + " sitio_web = ?, id_ciudad=?, estado=?, info=?, ajustar_peso=? "
                        + "where id_empresa = ?";
            }
            pstm = cn.prepareStatement(sql);
            if (opc.equals("update")) {                
                pstm.setString(1, nombre_razon);
                pstm.setString(2, direccion);
                pstm.setString(3, telefono);
                pstm.setString(4, correo);
                pstm.setString(5, celular);
                pstm.setBoolean(6, regimen_comun);
                if (!logo.equals("")) {
                    img1 = new File(logo);
                    fis = new FileInputStream(img1);
                    pstm.setBinaryStream(7, fis, (int) img1.length());
                } else {
                    pstm.setBinaryStream(7, null);
                }
                pstm.setString(8, sitio_web);
                pstm.setInt(9, id_ciudad);
                pstm.setBoolean(10, estado);
                pstm.setString(11, info);
                pstm.setBoolean(12, ajustealpeso);
                pstm.setString(13, id_empresa);
            } else {
                pstm.setString(1, id_empresa);
                pstm.setString(2, nombre_razon);
                pstm.setString(3, direccion);
                pstm.setString(4, telefono);
                pstm.setString(5, correo);
                pstm.setString(6, celular);
                pstm.setBoolean(7, regimen_comun);
                if (!logo.equals("")) {
                    img1 = new File(logo);
                    fis = new FileInputStream(img1);
                    pstm.setBinaryStream(8, fis, (int) img1.length());
                } else {
                    pstm.setBinaryStream(8, null);
                }
                pstm.setString(9, sitio_web);
                pstm.setInt(10, id_ciudad);
                pstm.setBoolean(11, estado);
                pstm.setString(12, "");
                pstm.setString(13, info);
                pstm.setBoolean(14, ajustealpeso);
            }

            pstm.executeUpdate();
            creado = true;
            cn.commit();
        } catch (FileNotFoundException | SQLException e) {
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
