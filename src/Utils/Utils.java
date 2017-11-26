/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import App.CheckList;
import Model.Clientes;
import Model.Conexion;
import Model.Facturas;
import Model.Habitaciones;
import Model.Hospedajes;
import Model.Pagos;
import Model.TiposDeHabitacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.Socket;
import java.net.URL;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mauricio Herrera
 */
public class Utils {

    public static Connection cn = Conexion.getConexion();
    public static PreparedStatement pstm;
    public static String sql;
    public static ResultSet rs;
    public static SimpleDateFormat formatF = new SimpleDateFormat("YYYY-MM-dd");
    public static SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public static DecimalFormat formateador = new DecimalFormat("###,###");

    //Listar 
    public static int getIdCiudad(String Parms) throws SQLException {
        int id_ciu = 0;
        try {
            sql = "select id_ciudad from ciudades where nombre = '" + Parms + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id_ciu = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return id_ciu;
    }

    public static boolean comprobarConexion() {
        boolean response = false;
        String dirWeb = "www.google.com";
        int puerto = 80;
        try {
            Socket s = new Socket(dirWeb, puerto);
            if (s.isConnected()) {
                response = true;
            }
        } catch (IOException e) {
            System.out.println("error " + e);
        }
        return response;
    }

    public static boolean getIP() {
        boolean response = false;
        String publicIP = "<No es posible resolver la direccion IP>";
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            publicIP = in.readLine();
            in.close();
            response = true;
            CheckList.lblIp.setText("IP PUBLICA:    " + publicIP);
        } catch (IOException ex) {
            System.out.println("error " + ex);
        }
        return response;
    }

    public static boolean getMac() throws IOException {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network.getHardwareAddress() != null) {
                byte[] mac = network.getHardwareAddress();
                System.out.print("Current MAC address : ");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }

                CheckList.lblMac.setText("MAC ADDRESS:    " + sb.toString());
            }

        } catch (UnknownHostException | SocketException e) {
            System.out.println("error " + e);
        }
        return false;
    }

    public static void createLicence(String key) {
        Properties propiedades = new Properties();
        OutputStream salida = null;
        try {
            salida = new FileOutputStream("src/Licence/License.properties");
            // asignamos los valores a las propiedades
            propiedades.setProperty("KeyNumber", "prueba");
            propiedades.setProperty("AppVersion", "1.0");
            // guardamos el archivo de propiedades en la carpeta de aplicación
            propiedades.store(salida, null);
        } catch (IOException io) {
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                }
            }

        }
    }

    public static List<String> readLicence() throws FileNotFoundException, IOException {
        ArrayList<String> datos = new ArrayList<>();
        Properties propiedades = new Properties();
        InputStream entrada = null;
        try {
            entrada = new FileInputStream("src/Licence/License.properties");
            // cargamos el archivo de propiedades
            propiedades.load(entrada);
            // obtenemos las propiedades y las imprimimos
            datos.add(propiedades.getProperty("KeyNumber"));
            datos.add(propiedades.getProperty("AppVersion"));
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        return datos;
    }

    public static String getTransaccion(String idVendedor, String Tabla) throws SQLException {
        int trans = 0;
        String transaccion = "";
        try {
            sql = "select substring_index(transacion, '_', -1) from " + Tabla + " where transacion like '" + idVendedor + "%' order by transacion desc limit 1";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                trans = Integer.parseInt(rs.getString(1));
                trans = trans + 1;
                transaccion = idVendedor + "_" + trans;
            } else {
                trans = trans + 1;
                transaccion = idVendedor + "_" + trans;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return transaccion;
    }

    public static String requerimientosFactura(String id_empresa, int tipoImprimible) throws SQLException {
        StringBuilder datos = new StringBuilder();
        datos.append("");
        try {
            sql = "select e.regimen_comun, r.activo, ci.prefijo, ci.consecutivo, r.id_resolucion, r.fecha, r.consecutivo_final  from empresas e, consecutivos_imprimibles ci, resoluciones_facturas r "
                    + "where e.id_empresa = r.id_empresa AND e.id_empresa = ci.id_empresa AND ci.id_tipo_imprimible = " + tipoImprimible + " AND e.id_empresa ='" + id_empresa + "' ORDER BY r.fecha DESC LIMIT 1";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos.append(rs.getInt(1)).append(",").append(rs.getInt(2)).append(",").append(rs.getString(3)).append(",").append(rs.getInt(4)).append(",").append(rs.getString(5));
                if (new Date().getTime() < addYears(rs.getDate(6), 2).getTime()) {
                    datos.append(",").append("vigente_fecha");
                    if (rs.getInt(4) < rs.getInt(7)) {
                        datos.append(",").append("vigente_numeracion");
                    } else {
                        datos.append(",").append("vencida_numeracion");
                    }
                } else {
                    datos.append(",").append("vencida_fecha");
                }
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos.toString();
    }

    public static Date addYears(Date fecha, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe	
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public static String isProducto(int idProSer) throws SQLException {
        StringBuilder datos = new StringBuilder();
        datos.append("");
        try {
            sql = "select nombre, precio_venta, stock, producto, id_producto_servicio, id_unidad_medida from productos_servicios where id_producto_servicio = " + idProSer;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos.append(rs.getString(1)).append(",").append(rs.getFloat(2)).append(",").append(rs.getInt(3)).append(",").append(rs.getInt(4)).append(",").append(rs.getInt(5)).append(",").append(rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos.toString();
    }

    public static boolean updateConsecutivoImprimible(String id_empresa, int tipoImprimible) throws SQLException {
        boolean update = false;
        try {
            sql = "update consecutivos_imprimibles set consecutivo = consecutivo+1 where id_empresa = '" + id_empresa + "' and id_tipo_imprimible =" + tipoImprimible;
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static float getImpuesto(int id_impuesto, float valor, float descuento) throws SQLException {
        float resultImpuesto = 0;
        try {
            sql = "select porcentaje_impuesto from impuestos where id_impuesto = " + id_impuesto;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                float porcentaje = (rs.getFloat(1) / (float) 100);
                resultImpuesto = ((valor - descuento) * porcentaje);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return resultImpuesto;

    }

    public static void generateReporte(Facturas factura, JRDataSource datasource, String Status, String addStatus) throws SQLException, JRException {
        try {
            JasperDesign jd = JRXmlLoader.load("src/Reports/previewOk.jrxml");
            //parametros de entrada
            Map parametros = new HashMap();
            parametros.clear();
//            parametros.put("logo", Utils.class.getResourceAsStream("/Reports/HotelLasVictoriasLogo.png"));
            parametros.put("logo", getLogo(factura.getVendedor().getIdEmpresa()));
            parametros.put("resolucion", factura.getResolucion().getIdResolucion());
            parametros.put("fecha", factura.getFecha());
            parametros.put("vence", factura.getVencimiento());
            parametros.put("subtotal", factura.getSubtotal());
            parametros.put("descTotal", factura.getDescuentoTotal());
            float descuentos = factura.getDescuentoTotal();
            if (descuentos == 0.0) {
                descuentos = getDescuentos(factura.getIdFactura());
            }
            parametros.put("descTotal", descuentos);
            parametros.put("reajuste", (factura.getSubtotal() - descuentos + factura.getIva()) - factura.getTotal());
            parametros.put("iva", factura.getIva());
            parametros.put("total", factura.getTotal());
            String[] d = null;
            parametros.put("idEmpresa", factura.getVendedor().getIdEmpresa());
            parametros.put("nota", factura.getNota());
            //Obtener el nobre de la empresa a travez del id empresa
//            parametros.put("estadoFactura", estadoFactura(factura.getEstado().getIdEstadoFactura()));
            // consultar estado factura de acuerdo al estado *estadoFactura           
            if (factura.getComprador().getIdentificacion().equals("")) {
                String part[] = getIdentificacionNombre(factura.getComprador().getIdCliente()).split(" ");
                d = getDireccionTelefonoCliente(part[0], true).split(",");
                switch (d[3]) {
                    case "C.C":
                        parametros.put("identCliente", part[0]);
                        break;
                    case "C.E":
                        parametros.put("identCliente", part[0]);
                        break;
                    case "NIT":
                        String nit = part[0].subSequence(0, part[0].length() - 1).toString();
                        String digitoDeVerificacion = part[0].subSequence(part[0].length() - 1, part[0].length()).toString();
                        parametros.put("identCliente", nit + "-" + digitoDeVerificacion);
                        break;
                    case "P.P":
                        parametros.put("identCliente", part[0]);
                        break;
                }
            } else {
                d = getDireccionTelefonoCliente(factura.getComprador().getIdentificacion(), true).split(",");
                switch (d[3]) {
                    case "C.C":
                        parametros.put("identCliente", factura.getComprador().getIdentificacion());
                        break;
                    case "C.E":
                        parametros.put("identCliente", factura.getComprador().getIdentificacion());
                        break;
                    case "NIT":
                        String nit = factura.getComprador().getIdentificacion().subSequence(0, factura.getComprador().getIdentificacion().length() - 1).toString();
                        String digitoDeVerificacion = factura.getComprador().getIdentificacion().subSequence(factura.getComprador().getIdentificacion().length() - 1, factura.getComprador().getIdentificacion().length()).toString();
                        parametros.put("identCliente", nit + "-" + digitoDeVerificacion);
                        break;
                    case "P.P":
                        parametros.put("identCliente", factura.getComprador().getIdentificacion());
                        break;
                }
            }
            String[] d2 = getDireccionTelefonoEmpresa(factura.getVendedor().getIdEmpresa()).split(",");
            parametros.put("nombEmpresa", d2[0]);
            String[] d3 = getDatosResolucion(factura.getResolucion().getIdResolucion()).split(",");
            if (factura.getComprador().getNombreRazon().equals("")) {
                parametros.put("nomCliente", d[2]);
            } else {
                parametros.put("nomCliente", factura.getComprador().getNombreRazon());
            }
            parametros.put("lblFacturaVenta", "");
            parametros.put("cons", "");
            parametros.put("dRes", "");
            parametros.put("dirCliente", d[0]);
            parametros.put("telCliente", d[1]);
            parametros.put("TipoDoc", d[3]);
            parametros.put("dirEmpresa", d2[1]);
            parametros.put("webEmpresa", d2[2]);
            parametros.put("telEmpresa", d2[3]);
            parametros.put("InfoCuentaEmpresa", d2[4]);
            parametros.put("Status", Status);
            parametros.put("texto", "");
            parametros.put("habitacion", "Sin Información");
            parametros.put("personas", "Sin Información");
            parametros.put("fec_llegada", "Sin Información");
            parametros.put("fec_salida", "Sin Información");

            String datoshabi = getInfoHospedajeByFactura(factura.getIdFactura());
            if (!datoshabi.equals("")) {
                String[] datos = datoshabi.split(",");
                parametros.put("habitacion", datos[0]);
                parametros.put("personas", "A: " + datos[1] + " N: " + datos[2]);
                parametros.put("fec_llegada", datos[3]);
                parametros.put("fec_salida", datos[4]);
            }
            if (isRegimenComun(factura.getVendedor().getIdEmpresa())) {
                parametros.put("regimen", "Regimen Común");
            } else {
                parametros.put("regimen", "Regimen Simplificado");
            }
            parametros.put("innova", "");
            switch (Status) {
                case "COTIZACIÓN":
                    parametros.put("lblFacturaVenta", "Cotización");
                    parametros.put("cons", "No " + factura.getIdFactura());
                    parametros.put("dRes", "");
                    parametros.put("texto", "La cotización es válida por 30 días calendario. No tendrá efecto después de este periodo. Los precios pueden variar sin previo aviso.");
                    parametros.put("innova", "Esta Cotización fue impresa por el Software PymesApp. Propiedad de: Codigo Innova S.A.S. Nit 900695790-7");
                    break;
                case "COPIA":
                    if (d3.length > 1) {
                        parametros.put("lblFacturaVenta", "Factura de Venta");
                        parametros.put("dRes", "FACTURA EMITIDA POR COMPUTADOR" + " Numeración Autorizada por la DIAN según documento oficial No. " + d3[0] + " de " + d3[1] + " desde " + d3[2] + " " + d3[3] + " hasta " + d3[2] + " " + d3[4]);
                        parametros.put("texto", "ESTA FACTURA DE VENTA SE ASIMILA EN SUS EFECTOS A LA LETRA DE CAMBIO SEGÚN ART. 774 DEL COD. DE COMERCIO."
                                + " AL ACEPTAR ESTA FACTURA AUTORIZO(AMOS) SER CONSULTADO(S) Y REPORTADO(S) A LAS CENTRALES DE RIESGO EN CASO DE MORA EN EL PAGO. "
                                + "EL INCUMPLIMIENTO AL PAGO CAUSARA INTERESES DE MORA A LA TASA MAXIMA LEGAL PERMIIDA");
                        parametros.put("innova", "Esta Factura fue impresa por el Software PymesApp. Propiedad de: Codigo Innova S.A.S. Nit 900695790-7");
                    } else {
                        parametros.put("dRes", "");
                        parametros.put("texto", "La cotización es válida por 30 días calendario. No tendrá efecto después de este periodo. Los precios pueden variar sin previo aviso.");
                        parametros.put("lblFacturaVenta", "Cotización");
                        parametros.put("innova", "Esta Cotización fue impresa por el Software PymesApp. Propiedad de: Codigo Innova S.A.S. Nit 900695790-7");
                    }
                    parametros.put("cons", "No " + factura.getIdFactura());
//                            + "EN EL CASO DE SOFTWARE Y HARDWARE / LA GARANTÍA ESTA DADA POR EL FABRICANTE. "
//                            + "Esta factura es indispensable para cualquier reclamación, no se aceptan devoluciones. "
//                            + "El comprador declara haber recibido el producto y/o servicio antes enumeradas a completa satisfacción.
                    Status = addStatus;
                    break;
                case "FACTURA DE VENTA":
                    parametros.put("texto", "ESTA FACTURA DE VENTA SE ASIMILA EN SUS EFECTOS A LA LETRA DE CAMBIO SEGÚN ART. 774 DEL COD. DE COMERCIO."
                            + " AL ACEPTAR ESTA FACTURA AUTORIZO(AMOS) SER CONSULTADO(S) Y REPORTADO(S) A LAS CENTRALES DE RIESGO EN CASO DE MORA EN EL PAGO. "
                            + "EL INCUMPLIMIENTO AL PAGO CAUSARA INTERESES DE MORA A LA TASA MAXIMA LEGAL PERMIIDA.");
                    parametros.put("lblFacturaVenta", "Factura de Venta");
                    parametros.put("dRes", "FACTURA EMITIDA POR COMPUTADOR" + " Numeración Autorizada por la DIAN según documento oficial No. " + d3[0] + " de " + d3[1] + " desde " + d3[2] + " " + d3[3] + " hasta " + d3[2] + " " + d3[4]);
                    parametros.put("cons", "No " + factura.getIdFactura());
                    Status = addStatus;
                    parametros.put("innova", "Esta Factura fue impresa por el Software PymesApp. Propiedad de: Codigo Innova S.A.S. Nit 900695790-7");
                    break;
            }
            Status = (addStatus.equals("")) ? "Estado De Cuenta" : addStatus;
            parametros.put("Status", Status);
            //fin parametros de entrada
            JasperReport jasperRep = JasperCompileManager.compileReport(jd);
            JasperPrint JasPrint = JasperFillManager.fillReport(jasperRep, parametros, datasource);
            JasperViewer jv = new JasperViewer(JasPrint, false);
            jv.setVisible(true);
            jv.setTitle("Factura de Venta");
        } catch (JRException ex) {
            System.out.println("Error jasper: " + ex);
        }
    }

    public static String getDatosResolucion(String resolucion) throws SQLException {
        String datos = "";
        try {
            sql = "select id_resolucion, fecha, prefijo, consecutivo_inicial, consecutivo_final from  resoluciones_facturas where id_resolucion = '" + resolucion + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    public static boolean getExisteResolucion(String idEmpresa) throws SQLException {
        boolean respuesta = false;
        try {
            sql = "select count(*) from  resoluciones_facturas where id_empresa = '" + idEmpresa + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return respuesta;
    }

    public static int getIdCliente(String identificacion, boolean cliente) throws SQLException {
        int result = -1;
        String and = " and cliente = 1";
        if (!cliente) {
            and = " and proveedor = 1";
        }
        try {
            sql = "select id_cliente from clientes where identificacion = '" + identificacion + "'" + and;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static String getDireccionTelefonoCliente(String identificacion, boolean cliente) throws SQLException {
        String datos = "";
        String and = " and cliente = 1";
        if (!cliente) {
            and = " and proveedor = 1";
        }
        try {
            sql = "select c.direccion, c.celular, c.nombre_razon, t.descripcion from clientes c, tipos_de_identificacion t "
                    + "where identificacion = '" + identificacion + "'" + and + " AND t.id_tipo_identificacion = c.id_tipo_identificacion";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    public static String getDireccionTelefonoEmpresa(String identificacion) throws SQLException {
        String datos = "";
        try {
            sql = "select nombre_razon, direccion, sitio_web, telefono, celular, info from empresas where id_empresa = '" + identificacion + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + " - " + rs.getString(5) + "," + rs.getString(6);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    public static boolean isRegimenComun(String identificacion) throws SQLException {
        boolean result = false;
        try {
            sql = "select regimen_comun from empresas where id_empresa = '" + identificacion + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static String estadoFactura(int idEstado) throws SQLException {
        String result = "";
        try {
            sql = "select nombre from estados_facturas where id_estado_factura = " + idEstado;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static ArrayList<String> getDatosHabitacion() throws SQLException {
        ArrayList<String> datos = new ArrayList();
        try {
            StringBuilder sb;
            sql = "select * from tipos_de_habitacion";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sb = new StringBuilder();
                sb.append(rs.getInt(1)).append(",").append(rs.getString(2)).append(",").append(rs.getFloat(3)).append(",");
                sb.append(rs.getFloat(4)).append(",").append(rs.getFloat(5)).append(",").append(rs.getString(6));
                datos.add(sb.toString());
            }
            sb = null;
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    public static boolean deleteNumberBill(String id_empresa, int idTipo) throws SQLException {
        boolean result = false;
        try {
            sql = "delete from consecutivos_imprimibles where id_empresa= '" + id_empresa + "' and id_tipo_imprimible = " + idTipo;
            pstm = cn.prepareStatement(sql);
            if (pstm.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static boolean desactivarResolucion(String id_empresa) throws SQLException {
        boolean result = false;
        try {
            sql = "update resoluciones_facturas set activo = 0 where id_empresa= '" + id_empresa + "' and activo = 1";
            pstm = cn.prepareStatement(sql);
            if (pstm.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static String getResolucionActiva(String empresa) throws SQLException {
        String result = "";
        try {
            sql = "select concat (id_resolucion, ',', prefijo, ',', consecutivo_inicial, ',', consecutivo_final)  from resoluciones_facturas where id_empresa  = '" + empresa + "' and activo = 1";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static boolean validarClave(String usuario, String clave) throws SQLException {
        boolean result = false;
        try {
            sql = "select count(*) from usuarios_app where id_usuario= '" + usuario + "' and password = '" + clave + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static boolean actualizarClave(String id_usuario, String clave) throws SQLException {
        boolean result = false;
        try {
            sql = "update usuarios_app set password = '" + clave + "' where id_usuario= '" + id_usuario + "'";
            pstm = cn.prepareStatement(sql);
            if (pstm.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return result;
    }

    public static String getNextConsecutivo(String id_empresa, int tipoImprimible) throws SQLException {
        String consecutivo = "";
        try {
            sql = "select case when prefijo = '' then consecutivo else concat(prefijo, ' ', consecutivo) end as consecutivo from consecutivos_imprimibles where id_empresa = '" + id_empresa + "' and id_tipo_imprimible = " + tipoImprimible;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                consecutivo = rs.getString(1).trim();
            }
            if (consecutivo.equals("") && tipoImprimible == 3) {
                sql = "insert into consecutivos_imprimibles (prefijo,consecutivo,id_tipo_imprimible,id_empresa,transacion) values ('RC', '1', 3, '" + id_empresa + "','" + id_empresa + "_1')";
                pstm = cn.prepareStatement(sql);
                pstm.executeUpdate();
                consecutivo = "RC 1";
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return consecutivo;
    }

    public static float getRetencionesByFactura(String idFactura) throws SQLException {
        float retenciones = 0;
        try {
            sql = "select SUM(valor) from retenciones_pagos where id_factura = '" + idFactura + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                retenciones = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return retenciones;
    }

    public static float getAbonos(String idFactura, int idHospedaje) throws SQLException {
        float abonos = 0;
        String and = "";
        if (idHospedaje > 0) {
            and = " where id_hospedaje = " + idHospedaje + " and id_factura = '" + idFactura + "'";
        } else {
            and = " where id_factura = '" + idFactura + "'";
        }
        try {
            sql = "select SUM(valor) from pagos " + and + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                abonos = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return abonos;
    }

    public static float getDescuentos(String idFactura) throws SQLException {
        float descuentos = 0;
        try {
            sql = "select SUM(descuento) from detalle_factura_cotizacion where id_factura = '" + idFactura + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                descuentos = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return descuentos;
    }

    public static float getTotalFactura(String idFactura) throws SQLException {
        float total = 0;
        try {
            sql = "select total from facturas where id_factura = '" + idFactura + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                total = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return total;
    }

    public static String getEstadoById(int id_estado_factura) throws SQLException {
        String nomEstado = "";
        System.out.println("estado factura");
        try {
            sql = "select nombre from estados_facturas where id_estado_factura = " + id_estado_factura + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                nomEstado = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return nomEstado;
    }

    public static boolean updateEstadoFactura(String id_factura, int idEstado) throws SQLException {
        boolean update = false;
        try {
            sql = "update facturas set id_estado_factura = " + idEstado + " where id_factura = '" + id_factura + "'";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static void generateReciboCaja(int idReciboCaja, String idEmpresa, String idFactura, String Copia) throws SQLException, JRException {
        try {
            String retenciones = "";
            JasperDesign jd = JRXmlLoader.load("src/Reports/reciboDeCaja.jrxml");
            //parametros de entrada
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("logo", Utils.class.getResourceAsStream("/Reports/HotelLasVictoriasLogo.png"));
            parametros.put("Bg", Copia);
            if (!idFactura.equals("")) {
                retenciones = getTotalRetencionByIdFactura(idFactura);
            }
            parametros.put("retenciones", (retenciones.equals("") ? "Sin retenciones" : retenciones));
            //probalo
            String query = "SELECT e.id_empresa, e.nombre_razon as nom_empresa, "
                    + "t.descripcion as tipoIdentificacion, "
                    + "e.direccion, e.telefono, e.correo, e.sitio_web, "
                    + "case when c.id_tipo_identificacion = 1 then FORMAT(c.identificacion, 0) "
                    + "when c.id_tipo_identificacion = 3 then CONCAT(SUBSTRING(c.identificacion, 1,LENGTH(c.identificacion)-1),'-',SUBSTRING(c.identificacion, LENGTH(c.identificacion),1)) "
                    + "else c.identificacion end as identificacion, "
                    + "c.nombre_razon as nom_cliente, "
                    + "c.direccion as dir_liente, ciu.nombre, rc.consecutivo, "
                    + "rc.fecha, rc.valor, cuent.nombre as nom_cuenta, "
                    + "tp.nombre as metodo_pago, rc.concepto, "
                    + "c.celular, "
                    + "case when tp.nombre = 'Cheque' then rc.cheque "
                    + "when tp.nombre = 'Tarjeta crédito' then CONCAT((select nombre from franquicias where id_franquicia = p.id_franquicia),' ',p.cuatro_digitos,' Voucher: ',p.voucher)"
                    + "when tp.nombre = 'Tarjeta débito'  then CONCAT(p.cuatro_digitos,' Voucher: ',p.voucher) end as infoMetodoPago "
                    + "from recibos_caja rc, empresas e, "
                    + "pagos p, clientes c, ciudades ciu, cuentas cuent, "
                    + "tipos_de_pagos tp , tipos_de_identificacion t "
                    + "where rc.id_recibo_caja = " + idReciboCaja + " "
                    + "and p.id_recibo_caja = rc.id_recibo_caja "
                    + "and e.id_empresa = rc.id_empresa "
                    + "AND e.id_empresa = '" + idEmpresa + "' "
                    + "AND c.id_cliente = p.id_cliente "
                    + "AND c.id_ciudad = ciu.id_ciudad "
                    + "AND cuent.id_cuenta = p.id_cuenta "
                    + "AND rc.id_tipo_pago = tp.id_tipo_pago "
                    + "AND c.id_tipo_identificacion = t.id_tipo_identificacion";
            JRDesignQuery nuevaC = new JRDesignQuery();
            nuevaC.setText(query);
            jd.setQuery(nuevaC);
            JasperReport jasperRep = JasperCompileManager.compileReport(jd);
            JasperPrint JasPrint = JasperFillManager.fillReport(jasperRep, parametros, Conexion.getConexion());
            JasperViewer jv = new JasperViewer(JasPrint, false);
            jv.setVisible(true);
            jv.setTitle("Recibo De Caja");
        } catch (JRException ex) {
            System.out.println("Error jasper: " + ex);
        }
    }

    public static String getIdentificacionNombre(int idCliente) throws SQLException {
        String datos = "";
        try {
            sql = "select concat(identificacion,' ', nombre_razon)  from clientes where id_cliente = " + idCliente;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datos = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    //Listar 
    public static List<Facturas> getFacturasList(String Params) throws SQLException {
        List<Facturas> list = new ArrayList<>();
        try {
            sql = "select * from facturas f" + Params;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Facturas f = new Facturas(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7),
                        rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getDate(12),
                        rs.getString(13), rs.getString(14), rs.getFloat(15), rs.getFloat(16));
                list.add(f);
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return list;
    }

    public static Facturas getFacturaById(String idFactura) throws SQLException {
        Facturas f = null;
        try {
            sql = "select * from facturas where id_factura = '" + idFactura + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String id_factura = rs.getString("id_factura");
                Date fecha = rs.getDate("fecha");
                String id_resolucion = rs.getString("id_resolucion");
                int id_cliente = rs.getInt("id_cliente");
                float subtotal = rs.getFloat("subtotal");
                float iva = rs.getFloat("iva");
                float total = rs.getFloat("total");
                int id_estado_factura = rs.getInt("id_estado_factura");
                String id_empresa = rs.getString("id_empresa");
                String id_usuario = rs.getString("id_usuario");
                int id_termino_pago = rs.getInt("id_termino_pago");
                Date vencimiento = rs.getDate("vencimiento");
                String nota = rs.getString("nota");
                String transaccion = rs.getString("transacion");
                f = new Facturas();
                f.setIdFactura(id_factura);
                f.setFecha(fecha);
                f.getResolucion().setIdResolucion(id_resolucion);
                f.getComprador().setIdCliente(id_cliente);
                f.setSubtotal(subtotal);
                f.setIva(iva);
                f.setTotal(total);
                f.getEstado().setIdEstadoFactura(id_estado_factura);
                f.getVendedor().setIdEmpresa(id_empresa);
                f.getUsuario().setIdUsuario(id_usuario);
                f.setValidezDias(id_termino_pago);
                f.setVencimiento(vencimiento);
                f.setNota(nota);
                f.setTransacion(transaccion);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return f;
    }

    public static String getNombreProducto(String id_factura, int id_productoServicio) {
        String nombre = "";
        try {

            sql = "select nombre from productos_servicios where id_producto_servicio = " + id_productoServicio + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                nombre = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return nombre;
    }

    public static int getIdUnidad(String unidad) {
        int idUnidad = 0;
        try {
            sql = "select id_unidad_medida from unidades_medida where nombre = '" + unidad + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                idUnidad = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return idUnidad;
    }

    public static int getDifferenceDays(Date fechainicial, Date fechafinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechainiciostring = df.format(fechainicial);
        try {
            fechainicial = df.parse(fechainiciostring);
        } catch (ParseException ex) {
        }
        String fechafinalstring = df.format(fechafinal);
        try {
            fechafinal = df.parse(fechafinalstring);
        } catch (ParseException ex) {
        }
        long fechainicialms = fechainicial.getTime();
        long fechafinalms = fechafinal.getTime();
        long diferencia = fechafinalms - fechainicialms;
        double dias = Math.floor(diferencia / 86400000L);// 3600*24*1000         
        return ((int) dias);
    }

    public static ArrayList<TiposDeHabitacion> getTiposHabitacionByHabitacion(int id_habitacion) throws SQLException {
        ArrayList<TiposDeHabitacion> datosHa = new ArrayList();
        try {
            sql = "select th.id_tipo_habitacion, th.costo_persona, th.costo_pareja, th.costo_persona_adicional from tipos_de_habitacion th, habitaciones h where h.id_habitacion = " + id_habitacion + " and h.id_tipo_habitacion = th.id_tipo_habitacion  ";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                datosHa.add(new TiposDeHabitacion(rs.getInt(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4)));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datosHa;
    }

    public static boolean updateDisponibilidadHabitacion(int id_habitacion, int estado) throws SQLException {
        boolean update = false;
        try {
            cn.setAutoCommit(false);
            sql = "update habitaciones set disponible = " + estado + " where id_habitacion = " + id_habitacion + "";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static ArrayList<Hospedajes> getHospedajeActivo(String estado, int cliente) throws SQLException {
        ArrayList<Hospedajes> datosH = new ArrayList();
        String and = "";
        if (cliente > 0) {
            and = " and ho.id_cliente = " + cliente + "";
        }
        try {
            if ("".equals(estado)) {
                sql = "select ho.id_hospedaje, ho.fecha_ingreso, ho.fecha_salida, ho.numero_adultos + ho.numero_ninos as total_personas, h.nombre, c.id_cliente, c.nombre_razon, ho.id_habitacion, 'Sin Facturar', ho.id_factura, ho.numero_adultos, ho.numero_ninos from hospedajes ho, habitaciones h, clientes c where ho.id_habitacion = h.id_habitacion and ho.id_cliente = c.id_cliente and ho.id_factura is null" + and + " order by ho.id_hospedaje DESC";
            } else {
                sql = "select ho.id_hospedaje, ho.fecha_ingreso, ho.fecha_salida, ho.numero_adultos + ho.numero_ninos as total_personas, h.nombre, c.id_cliente, c.nombre_razon, ho.id_habitacion, ef.nombre, ho.id_factura, ho.numero_adultos, ho.numero_ninos from hospedajes ho, habitaciones h, clientes c, facturas f, estados_facturas ef where ho.id_habitacion = h.id_habitacion and ho.id_cliente = c.id_cliente and ho.id_factura = f.id_factura and f.id_estado_factura = ef.id_estado_factura and ef.nombre ='" + estado + "'" + and + " order by ho.id_hospedaje DESC";
            }
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                datosH.add(new Hospedajes(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12)));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datosH;
    }

    public static ArrayList<Habitaciones> getHabitacionesDisponibles(boolean americana, boolean chalet, int personas) throws SQLException {
        ArrayList<Habitaciones> datos = new ArrayList();
        String and = "";
        if (personas > 1) {
            and = " and h.total_camas >= " + personas;
        }
        if (americana && chalet) {
            sql = "select h.id_habitacion, h.nombre, h.cama_doble, h.cama_sencilla, t.nombre from habitaciones h, tipos_de_habitacion t where h.id_tipo_habitacion = t.id_tipo_habitacion and h.disponible = 0 " + and;
        }
        if (!americana && chalet) {
            sql = "select h.id_habitacion, h.nombre, h.cama_doble, h.cama_sencilla, t.nombre from habitaciones h, tipos_de_habitacion t where h.id_tipo_habitacion = t.id_tipo_habitacion and h.disponible = 0 and h.id_tipo_habitacion=1 " + and;
        }
        if (americana && !chalet) {
            sql = "select h.id_habitacion, h.nombre, h.cama_doble, h.cama_sencilla, t.nombre from habitaciones h, tipos_de_habitacion t where h.id_tipo_habitacion = t.id_tipo_habitacion and h.disponible = 0 and h.id_tipo_habitacion=2 " + and;
        }
        try {
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                datos.add(new Habitaciones(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return datos;
    }

    public static float getTarifaRetencionById(int id_retencion) {
        float val = 0;
        try {
            sql = "select tarifa from retenciones where id_retencion = " + id_retencion;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                val = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return val;
    }

    public static int getHabitacionByIdHospedaje(int idHospedaje) {
        int val = 0;
        try {
            sql = "select id_habitacion from hospedajes where id_hospedaje = " + idHospedaje;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                val = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return val;
    }

    public static String getUnidadMedida(int id_unidad) {
        String val = "";
        try {
            sql = "select descripcion from unidades_medida where id_unidad_medida = " + id_unidad;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                val = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return val;
    }

    public static int getRolbyIdentificacion(String idUsuario) {
        int val = 0;
        try {
            sql = "select id_rol from usuarios_app where id_usuario = '" + idUsuario + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                val = rs.getInt(1);

            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return val;
    }

    public static String getInfoHospedajeByFactura(String idfactura) {
        String array = "";
        try {
            sql = "select h.nombre, o.numero_adultos, o.numero_ninos, o.fecha_ingreso, o.fecha_salida from hospedajes o, habitaciones h "
                    + "where o.id_habitacion = h.id_habitacion AND o.id_factura = '" + idfactura + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs.next()) {
                array = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5);
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return array;
    }

    private static String getTotalRetencionByIdFactura(String idFactura) {
        String val = "";
        try {
            sql = "select r.nombre, rp.valor from retenciones r, retenciones_pagos rp where rp.id_factura = '" + idFactura + "' "
                    + "AND rp.id_retencion = r.id_retencion";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                val += rs.getString(1) + " " + rs.getString(2) + ". \n";
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return val;
    }

    public static boolean anularfacturas(String idfactura) {
        boolean update = false;
        try {
            cn.setAutoCommit(false);
            sql = "update facturas set id_estado_factura = 5 where id_factura = '" + idfactura + "'";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static boolean updateTarifasHabitaciones(int idTipoHabitacion, Float costoPersona, Float costoPareja, Float costoAdicional) throws SQLException {
        boolean update = false;
        try {
            sql = "update tipos_de_habitacion set costo_persona = " + costoPersona + ", costo_pareja = " + costoPareja + ", costo_persona_adicional = " + costoAdicional + " where id_tipo_habitacion = " + idTipoHabitacion + "";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static void generateReporteHospedaje(String idEmpresa) throws SQLException, JRException {
        try {
            JasperDesign jd = JRXmlLoader.load("src/Reports/reporteHospedajes.jrxml");
            //parametros de entrada
            Map parametros = new HashMap();
            parametros.clear();
//            parametros.put("logo", Utils.class.getResourceAsStream("/Reports/HotelLasVictoriasLogo.png"));
            //probalo
            String fecha = formatF.format(new Date()) + " 15:00:00";
            String query = "select h.nombre, c.nombre_razon, ho.numero_adultos, ho.numero_ninos, ho.valor_noche, ho.id_factura, "
                    + "(select sum(valor) from recibos_caja where id_factura = ho.id_factura) as abonos, "
                    + "ho.fecha_ingreso, ho.fecha_salida,f.total-(select sum(valor) from recibos_caja "
                    + "where id_factura = ho.id_factura) as saldo, "
                    + "m.nombre as concepto, n.pais, concat (ua.nombres,' ',ua.apellidos) as recepcionista "
                    + "from hospedajes ho, habitaciones h, clientes c, facturas f, motivos_estadia m, "
                    + "nacionalidades n, usuarios_app ua where h.id_habitacion = ho.id_habitacion "
                    + "and ho.id_cliente = c.id_cliente and ho.id_factura = f.id_factura and ho.id_motivo = m.id_motivo "
                    + "and c.id_nacionalidad = n.id_nacionalidad and ho.recepcionista = ua.id_usuario and c.id_empresa = '" + idEmpresa + "' "
                    + "and (ho.fecha_salida >= '" + fecha + "' or ho.fecha_salida is null)"
                    + "UNION "
                    + "select h.nombre, c.nombre_razon, ho.numero_adultos, ho.numero_ninos, ho.valor_noche, '' as id_factura, "
                    + "0 as abonos, ho.fecha_ingreso, ho.fecha_salida, '' as saldo,m.nombre as concepto, n.pais, concat (ua.nombres,' ',ua.apellidos) as recepcionista "
                    + "from hospedajes ho, habitaciones h, clientes c, motivos_estadia m, nacionalidades n, "
                    + "usuarios_app ua where h.id_habitacion = ho.id_habitacion and ho.id_cliente = c.id_cliente and ho.id_motivo = m.id_motivo "
                    + "and c.id_nacionalidad = n.id_nacionalidad and ho.recepcionista = ua.id_usuario "
                    + "and ho.id_factura is null and c.id_empresa = '" + idEmpresa + "' "
                    + "and (ho.fecha_salida >= '" + fecha + "' or ho.fecha_salida is null)";
            JRDesignQuery nuevaC = new JRDesignQuery();
            nuevaC.setText(query);
            jd.setQuery(nuevaC);
            JasperReport jasperRep = JasperCompileManager.compileReport(jd);
            JasperPrint JasPrint = JasperFillManager.fillReport(jasperRep, null, Conexion.getConexion());
            JasperViewer jv = new JasperViewer(JasPrint, false);
            jv.setVisible(true);
            jv.setTitle("Reporte Hospedajes");
        } catch (JRException ex) {
            System.out.println("Error jasper: " + ex);
        }
    }

    public static ArrayList<Clientes> getClientes(String tipoUsers, String criterio) {
        ArrayList<Clientes> clientes = new ArrayList();
        try {
            String where = "";
            switch (tipoUsers) {
                case "clientes":
                    where = "where cliente = 1" + criterio + "";
                    break;
                case "proveedores":
                    where = "where proveedor = 1" + criterio + "";
                    break;
            }
            sql = "select * from clientes " + where;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                clientes.add(new Clientes(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getBoolean(11),
                        rs.getBoolean(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getInt(15), rs.getString(16), rs.getInt(17), rs.getDate(18)));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return clientes;
    }

    public static boolean voucherValido(String numero, int limite) {
        boolean valido = true;
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(numero);
        if (m.find() || numero.length() > limite) {
            valido = false;
        }
        return valido;
    }

    public static String obtenerPaisById(int id) throws SQLException {
        String pais = "";
        try {
            sql = "select pais from nacionalidades where id_nacionalidad = " + id + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                pais = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return pais;
    }

    public static float ajusteFactura(float valor) {
        float ajuste = 0;
        if (valor % 50 != 0.0) {
            float valido = valor / (float) 50;
            String valido2 = Float.toString(valido).replace(".", "-");
            String[] z = valido2.split("-");
            int dato2 = Integer.parseInt(z[0]) * 50;
            ajuste = (int) (valor - (float) dato2);
        }
        return ajuste;
    }

    public static boolean cancelarHospedaje(int hospedaje) {
        boolean update = false;
        try {
            sql = "delete from hospedajes where id_hospedaje = " + hospedaje + "";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static boolean deleteCliente(int idCliente) {
        boolean delete = false;
        try {
            sql = "delete from clientes where id_cliente = " + idCliente + "";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                delete = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return delete;
    }

    public static boolean actualizarHospedaje(int hospedaje, int adultos, int ninos, int habitacion) {
        boolean update = false;
        try {
            cn.setAutoCommit(false);
            sql = "update hospedajes set id_habitacion = " + habitacion + ", numero_adultos = " + adultos + ", numero_ninos = " + ninos + " where id_hospedaje = " + hospedaje + "";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static Clientes datosClienteById(int id) throws SQLException {
        Clientes c = null;
        try {
            sql = "select * from clientes where id_cliente = " + id + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                c = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getBoolean(11), rs.getBoolean(12), rs.getString(13), rs.getString(14),
                        rs.getInt(15), rs.getString(16), rs.getInt(17), rs.getDate(18));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return c;
    }

    public static String tipoIdentificacionById(int id) throws SQLException {
        String tipo = "";
        try {
            sql = "select concat(id_tipo_identificacion,'-',nombre) as tipo "
                    + "from tipos_de_identificacion where id_tipo_identificacion = " + id + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                tipo = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return tipo;
    }

    public static String nacionalidadById(int id) throws SQLException {
        String tipo = "";
        try {
            sql = "select concat(pais,' - ',id_nacionalidad) as nacionalidad "
                    + "from nacionalidades where id_nacionalidad = " + id + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                tipo = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return tipo;
    }

    //Listar 
    public static String getCiudadById(int Parms) throws SQLException {
        String ciudad = "";
        try {
            sql = "select nombre from ciudades where id_ciudad = " + Parms + "";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                ciudad = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return ciudad;
    }

    public static int getIdByIdentificacionCliente(String identificacion) throws SQLException {
        int id = 0;
        try {
            sql = "select id_cliente from clientes where identificacion = '" + identificacion + "' ";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return id;
    }

    public static void gerarIngresosDiarios(Date fecha_ini, Date fecha_fin, String userlog, String idEmpresa) throws SQLException, JRException {
        try {
            JasperDesign jd = JRXmlLoader.load("src/Reports/ingresosDiarios.jrxml");
            //parametros de entrada
            Map parametros = new HashMap();
            parametros.clear();
//            parametros.put("logo", Utils.class.getResourceAsStream("/Reports/HotelLasVictoriasLogo.png"));
            parametros.put("fecha_ini", formatF.format(fecha_ini));
            parametros.put("fecha_fin", formatF.format(fecha_fin));
            String and = "where p.fecha between '" + formatF.format(fecha_ini) + "'  and  '" + formatF.format(fecha_fin) + "'";
            int rol = getRolbyIdentificacion(userlog);
            if (rol == 3) {
                and = "where p.recepcionista = '" + userlog + "' and  p.fecha between '" + formatF.format(fecha_ini) + "'  and  '" + formatF.format(fecha_fin) + "'";
            }
            String query = "Select c.identificacion, c.nombre_razon, p.fecha, p.observaciones, t.nombre tipo_pago, p.valor, "
                    + "f.nombre franquicia, p.voucher, rc.consecutivo recibo_caja, rc.concepto, "
                    + "ft.id_factura, rc.cheque, concat(ua.nombres,' ', ua.apellidos) recep "
                    + "from pagos p "
                    + "LEFT JOIN clientes c on c.id_cliente = p.id_cliente and c.id_empresa = '" + idEmpresa + "' "
                    + "LEFT join tipos_de_pagos t on t.id_tipo_pago = p.id_tipo_pago "
                    + "LEFT join franquicias f on f.id_franquicia = p.id_franquicia "
                    + "LEFT JOIN recibos_caja rc on rc.id_recibo_caja = p.id_recibo_caja "
                    + "LEFT join facturas ft on ft.id_factura =p.id_factura "
                    + "Left join usuarios_app ua on p.recepcionista = ua.id_usuario " + and;
            JRDesignQuery nuevaC = new JRDesignQuery();
            nuevaC.setText(query);
            jd.setQuery(nuevaC);
            JasperReport jasperRep = JasperCompileManager.compileReport(jd);
            JasperPrint JasPrint = JasperFillManager.fillReport(jasperRep, parametros, Conexion.getConexion());
            JasperViewer jv = new JasperViewer(JasPrint, false);
            jv.setVisible(true);
            jv.setTitle("Reporte de Ingresos");
        } catch (JRException ex) {
            System.out.println("Error jasper: " + ex);
        }
    }

    public static void generarFacturacion(Date fecha_ini, Date fecha_fin, String params, String idEmpresa) throws SQLException, JRException {
        try {
            JasperDesign jd = JRXmlLoader.load("src/Reports/ReporteFacturacion.jrxml");
            //parametros de entrada
            Map parametros = new HashMap();
            parametros.clear();
//            parametros.put("logo", Utils.class.getResourceAsStream("/Reports/HotelLasVictoriasLogo.png"));
            parametros.put("fecha_ini", formatF.format(fecha_ini));
            parametros.put("fecha_fin", formatF.format(fecha_fin));
            String anulada = "and f.id_estado_factura <> 5";
            if (params.contains("f.id_estado_factura = 5")) {
                anulada = "";
            }
            String query = "select  DATE_FORMAT(f.fecha,'%Y-%m-%d') fecha, f.iva, f.subtotal, f.id_factura,c.nombre_razon, c.identificacion, "
                    + "(select sum(valor)-sum(descuento) from detalle_factura_cotizacion  where id_factura = f.id_factura and id_producto_servicio = 3) terceros, "
                    + "f.total, "
                    + "case when (select sum(valor) from pagos where id_factura = f.id_factura) is null then 0 else (select sum(valor) from pagos where id_factura = f.id_factura) end abonos, "
                    + "case when (Select sum(valor) from retenciones_pagos where id_factura = f.id_factura) is null then 0 else (Select sum(valor) from retenciones_pagos where id_factura = f.id_factura) end retenciones, "
                    + "e.nombre, "
                    + "case when (select sum(valor)-sum(descuento) from detalle_factura_cotizacion  where id_factura = f.id_factura and id_producto_servicio = 4) is null then 0 "
                    + "else (select sum(valor)-sum(descuento) from detalle_factura_cotizacion  where id_factura = f.id_factura and id_producto_servicio = 4) end alojamiento "
                    + "from facturas f "
                    + "left join clientes c on f.id_cliente = c.id_cliente "
                    + "left join pagos p on  p.id_factura = f.id_factura "
                    + "left join estados_facturas e on e.id_estado_factura = f.id_estado_factura "
                    + "where f.id_empresa = '" + idEmpresa + "' and f.id_estado_factura <> 6 " + anulada + " AND DATE_FORMAT(f.fecha,'%Y-%m-%d') between '" + formatF.format(fecha_ini) + "'  and  '" + formatF.format(fecha_fin) + "'" + params
                    + " group by f.id_factura order by fecha, f.id_factura asc";
            JRDesignQuery nuevaC = new JRDesignQuery();
            nuevaC.setText(query);
            jd.setQuery(nuevaC);
            JasperReport jasperRep = JasperCompileManager.compileReport(jd);
            JasperPrint JasPrint = JasperFillManager.fillReport(jasperRep, parametros, Conexion.getConexion());
            JasperViewer jv = new JasperViewer(JasPrint, false);
            jv.setVisible(true);
            jv.setTitle("Reporte de Facturacion");
        } catch (JRException ex) {
            System.out.println("Error jasper: " + ex);
        }
    }

    public static boolean identificacionValida(String identificacion) throws SQLException {
        boolean existe = false;
        try {
            sql = "select * from clientes where identificacion = '" + identificacion + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return existe;
    }

    public static boolean clienteHospedaje(int idCliente) throws SQLException {
        boolean hospedaje = false;
        try {
            sql = "select * from hospedajes where id_cliente = '" + idCliente + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                hospedaje = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return hospedaje;
    }

    public static boolean clienteFactura(int idCliente) throws SQLException {
        boolean delete = false;
        try {
            sql = "select * from facturas where id_cliente = '" + idCliente + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                delete = true;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return delete;
    }

    public static ArrayList<Pagos> getPagos(String id_factura, int idHospedaje) {
        ArrayList<Pagos> p = new ArrayList();
        try {
            String and = "";
            if (idHospedaje == 0) {
                and = " p.id_factura = '" + id_factura + "'";
            } else {
                and = " p.id_factura = '" + id_factura + "' and p.id_hospedaje = " + idHospedaje + "";
            }
            sql = "select c.nombre, p.fecha, p.observaciones, t.nombre, p.id_recibo_caja, "
                    + "p.valor, p.cuatro_digitos "
                    + "from pagos p "
                    + "inner join cuentas c on p.id_cuenta = c.id_cuenta "
                    + "inner join tipos_de_pagos t on t.id_tipo_pago = p.id_tipo_pago "
                    + "where" + and;
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                p.add(new Pagos(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getFloat(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return p;
    }

    public static boolean facturasSinPagar() {
        boolean update = false;
        try {
            cn.setAutoCommit(false);
            sql = "update facturas set id_estado_factura = 4 "
                    + "where id_estado_factura in (1,3) "
                    + "AND vencimiento < '" + formatF.format(new Date()) + "'";
            pstm = cn.prepareStatement(sql);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    public static boolean updateLogoEmpresa(String NombreArchivo, String empresa) throws FileNotFoundException {
        boolean update = false;
        try {
            FileInputStream fis = null;
            File img1 = null;
            img1 = new File(NombreArchivo);
            fis = new FileInputStream(img1);
            cn.setAutoCommit(false);
            sql = "update empresas set logo = ?"
                    + "where id_empresa = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setBinaryStream(1, fis, (int) img1.length());
            pstm.setString(2, empresa);
            int rowAfectedR = pstm.executeUpdate();
            if (rowAfectedR > 0) {
                update = true;
            }
            cn.commit();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return update;
    }

    private static InputStream getLogo(String idEmpresa) {
        InputStream logo = null;
        try {
            sql = "select logo from empresas where id_empresa = '" + idEmpresa + "'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                logo = rs.getBinaryStream(1);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return logo;
    }

    public static ArrayList<String> MoreinfoFactura(String text) throws SQLException {
        ArrayList<String> datos = new ArrayList();
        String sqlq = "SELECT case when (select SUM(valor) from pagos where id_factura = f.id_factura) is not null "
                + "then (select SUM(valor) from pagos where id_factura = f.id_factura) else 0 end as abonos,"
                + " case when (select SUM(valor) from retenciones_pagos where id_factura = f.id_factura) is not null "
                + " then (select SUM(valor) from retenciones_pagos where id_factura = f.id_factura) else 0 end as retenciones FROM facturas f "
                + "where f.id_empresa = '51744384-1' and id_estado_factura <> 6 order by fecha DESC";
        pstm = cn.prepareStatement(sqlq);
        rs = pstm.executeQuery();
        if (rs.next()) {            
          datos.add(Float.toString(rs.getFloat(1))+","+Float.toString(rs.getFloat(2)));
        }
        return datos;
    }

}
