package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Innova
 */
public class FacturasAnuladasDAO {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    CallableStatement clstm;
    String sql;
    ResultSet rs;
    SimpleDateFormat sa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Listar 
    public List<FacturasAnuladas> getFacturasAnuladasList() throws SQLException {
        List<FacturasAnuladas> list = new ArrayList<>();        
        try {
            sql = "{call listarFacturaAnulada()}";
            clstm = cn.prepareCall(sql);
            rs = clstm.executeQuery();
            while (rs.next()) {
                FacturasAnuladas fa = new FacturasAnuladas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6));
                list.add(fa);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            cn.close();
        }
        return list;
    }

    //Crear y editar 
    public boolean crearFacturaAnulada(int id_factura_anulada, String id_factura, String concepto, String id_usuario, Date fecha, String transacion, String opc) throws SQLException {
//        System.out.println("" + id_empresa + " " + nombre_razon + " " + direccion + " " + telefono + " " + correo + " " + celular + " " + regimen_comun + " " + logo + " " + sitio_web + " " + id_ciudad + " " + estado + " " + opc);
        boolean creado = false;
        FileInputStream fis = null;
        File img1 = null;       
        try {
            cn.setAutoCommit(false);
            if (opc.equals("create")) {

                sql = "{call insertarFacturaAnulada(?,?,?,?,?)}";
            } else {
                sql = "{call actualizarFacturaAnulada(?,?,?,?,?,?)}";
            }
            clstm = cn.prepareCall(sql);
            if (opc.equals("update")) {
                clstm.setInt(1, id_factura_anulada);
                clstm.setString(2, id_factura);
                clstm.setString(3, concepto);
                clstm.setString(4, id_usuario);
                clstm.setString (5, sa.format(fecha));
                clstm.setString(6, transacion);                
            } else {                
                clstm.setString(1, id_factura);
                clstm.setString(2, concepto);
                clstm.setString(3, id_usuario);
                clstm.setString (4, sa.format(fecha));
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

    //Eliminar actividades economicas
    public boolean eliminarFacturaAnulada(int id_factura_anulada) throws SQLException {
        boolean creado = false;        
        try {
            cn.setAutoCommit(false);
            sql = "{call eliminarFacturaAnulada(?)}";
            clstm = cn.prepareCall(sql);
            clstm.setInt(1, id_factura_anulada);
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

