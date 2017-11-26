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
public class RecibosCajaDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    //Listar
    public List<RecibosCaja> getRecibosCajaList() throws SQLException {
        List<RecibosCaja> list = new ArrayList<>();      
        try {
            sql = "{call listarReciboCaja()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                RecibosCaja rc = new RecibosCaja(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4),
                        rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getString(11), rs.getInt(12));
                list.add(rc);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
    }

    //Crear y editar 
    //Crear y editar 
    public int crearReciboCaja(int id_recibo_caja, String id_factura, String consecutivo, Date fecha,
            String id_empresa, int id_cliente, float valor, String concepto, String cheque,
            int id_tipo_pago, String transacion, int id_hospedaje, String opc) throws SQLException {
        int result = 0;
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {
                sql = "{call insertarReciboCaja(?,?,?,?,?,?,?,?,?,?,?)}";
            } else {
                sql = "{call actualizarReciboCaja(?,?,?,?,?,?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_recibo_caja);
                clstm.setString(2, id_factura);
                clstm.setString(3, consecutivo);
                clstm.setString(4, df.format(fecha));
                clstm.setString(5, id_empresa);
                clstm.setInt(6, id_cliente);
                clstm.setFloat(7, valor);
                clstm.setString(8, concepto);
                clstm.setString(9, cheque);
                clstm.setInt(10, id_tipo_pago);
                clstm.setString(11, transacion);
                clstm.setInt(12, id_hospedaje);
            } else {
                clstm.setString(1, id_factura);
                clstm.setString(2, consecutivo);
                clstm.setString(3, df.format(fecha));
                clstm.setString(4, id_empresa);
                clstm.setInt(5, id_cliente);
                clstm.setFloat(6, valor);
                clstm.setString(7, concepto);
                clstm.setString(8, cheque);
                clstm.setInt(9, id_tipo_pago);
                clstm.setString(10, transacion);
                clstm.setInt(11, id_hospedaje);
            }
            rs = clstm.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
//                if (result > 0) {
//                    if (Utils.Utils.updateConsecutivoImprimible(id_empresa, 3)) {
//                        System.out.println("actualizado consecutivo");
//                    }
//                }
            }           
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        } 
        return result;
    }

    //Eliminar 
    public boolean eliminarReciboCaja(int id_recibo_caja) throws SQLException {
        boolean creado = false;       
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarReciboCaja(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_recibo_caja);
            ResultSet rs = clstm.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }          

            creado = true;
            cn.commit();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return creado;
    }

}
