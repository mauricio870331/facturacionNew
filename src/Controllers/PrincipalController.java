package Controllers;

import App.*;
import Model.Ciudades;
import Model.CiudadesDAO;
import Model.ClientesDAO;
import Model.Clientes;
import Model.ConsecutivosImprimibles;
import Model.ConsecutivosImprimiblesDAO;
import Model.DetalleFacturaCotizacion;
import Model.DetalleFacturaCotizacionDAO;
import Model.DetalleFacturaDataSource;
import Model.Empresas;
import Model.EmpresasDAO;
import Model.Facturas;
import Model.FacturasDAO;
import Model.Franquicias;
import Model.FranquiciasDAO;
import Model.Habitaciones;
import Model.HabitacionesDAO;
import Model.Hospedajes;
import Model.HospedajesDAO;
import Model.Impuestos;
import Model.ImpuestosDAO;
import Model.ModulosLicenciasDAO;
import Model.MotivosEstadia;
import Model.MotivosEstadiaDAO;
import Model.Nacionalidades;
import Model.NacionalidadesDAO;
import Model.Pagos;
import Model.PagosDAO;
import Model.ProductosServicios;
import Model.ProductosServiciosDAO;
import Model.RecibosCajaDAO;
import Model.ResolucionesFacturasDAO;
import Model.Retenciones;
import Model.RetencionesDAO;
import Model.RetencionesPagosDAO;
import Model.Roles;
import Model.RolesDAO;
import Model.TerminosDePago;
import Model.TerminosDePagoDAO;
import Model.TiposDeHabitacion;
import Model.TiposDeIdentificacion;
import Model.TiposDeIdentificacionDAO;
import Model.TiposDePagos;
import Model.TiposDePagosDAO;
import Model.TiposImprimibles;
import Model.TiposImprimiblesDAO;
import Model.TiposResoluciones;
import Model.TiposResolucionesDAO;
import Model.UnidadesMedida;
import Model.UnidadesMedidaDAO;
import Model.UsuariosAppDAO;
import Model.RetencionesPagos;
import Model.TiposDeHabitacionDAO;
import Model.UsuariosApp;
import Utils.RenderTable;
import Utils.TestConection;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javafx.scene.control.CheckBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Innova
 */
public final class PrincipalController implements ActionListener, KeyListener, FocusListener, MouseListener, ChangeListener {

    ArrayList<TiposDeHabitacion> tiposList = new ArrayList();
    private final ModalItem ps = getModalItem.getModalItem();
    private final Principal pr = GetPrincipal.getPrincipal();
    private final ModalClientes mc = GetModalClientes.getModalCliente();
    private final SelectTipoHospedaje st = GetTipoHospedaje.getModalTipoHospedaje();
    private final ResolucionFacturacion rf = getModalResolucion.getModalResolucion();
    private final ModalVincularUsuarios vu = getModalVincularUsuarios.getModalVincularUsuarios();
    private final ModalNumeracion mn = getModalNumeracion.getModalNumeracion();
    private final ModalCambiarClave cc = getModalCambiarClave.getModalCambiarClave();
    private final RegistrarHuesped rh = getRegistrarHuesped.getRegistrarHuesped();
    private final GenerarPagos gp = GetGenerarPagos.getGenerarPagos();
    private final GenerarRetencion gr = GetGenerarRetencion.getGenerarRtencion();
    private final AnularFacturas af = GetAnularFacturas.getAnularFacturas();
    private final ModalTarifasHospedaje th = getModalTarifasHospedaje.getModalTarifasHospedaje();
    private final ModalActualizarHospedaje ah = getModalActualizarHospedaje.getModalActualizarHospedaje();
    private final ReporteIngresos ri = GetReporteIngresos.getReporteIngresos();
    private final ModalReporteFacturacion mrf = getModalReporteFacturacion.getModalReporteFacturacion();
    private final PagosRealizados pg = GetPagosRealizados.getPagosRealizados();
    private final ModalEmpresas emp = GetEmpresas.getEmpresas();
    int registro = 0;
    HabitacionesDAO habitacionDao;
    HospedajesDAO hospedajeDao;
    TiposDeIdentificacion tipo;
    TiposDeIdentificacionDAO tipoDao;
    TiposResolucionesDAO tiposResolucionDao;
    RolesDAO rolesDao;
    TiposDePagos tipoPago;
    TiposDePagosDAO tipoPagodao;
    TerminosDePago terminosPago;
    TerminosDePagoDAO terminosDao;
    ProductosServicios producServ;
    ProductosServiciosDAO productosSerDao;
    Impuestos impuesto;
    ImpuestosDAO impuestoDAO;
    UnidadesMedidaDAO unidadDao;
    MotivosEstadiaDAO motivosDao;
    RetencionesDAO retencionesDao;
    RetencionesPagosDAO retencionesPagosDao;
    TiposDeHabitacionDAO tipoHabitacionDao;
    ArrayList<DetalleFacturaCotizacion> detalleFacturaItems = new ArrayList();
    DetalleFacturaCotizacionDAO detallefacturaCotDao;
    String nomProducto;
    float valorProd;
    int idProductoservicio;
    String idUnidaMedida;
    String ConsecutivoFactura;
    DecimalFormat formateador = new DecimalFormat("###,###");
    private Facturas factura;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd 15:00:00");
    String v;
    ArrayList<String> tiposhabitacion = new ArrayList();
    String[] datosTipoHabitacion;
    float valorModal;
    TextAutoCompleter ta;
    ArrayList<String> notaHospedaje = new ArrayList();
    public JLabel labels[];
    public JTextField field[];
    public JTextField field2[];
    String stauFPreview = "";
    Ciudades ciudad;
    CiudadesDAO ciudadDao;
    FranquiciasDAO franquiciaDao;
    TiposImprimiblesDAO imprimiblesDao;
    TiposImprimibles imprimibles;
    ConsecutivosImprimibles consecutivoImprimible;
    ConsecutivosImprimiblesDAO consecutivoImprimiblesDao;
    ClientesDAO clientedao;
    DefaultTableModel modelo;
    String[] validarContenido;
    FacturasDAO facturaDao;
    ResolucionesFacturasDAO resolucionDao;
    UsuariosAppDAO usuariosAppDao;
    PagosDAO pagosDao;
    EmpresasDAO empresasdao;
    RecibosCajaDAO recibosDeCajaDao;
    Iterator<TiposDeIdentificacion> t;
    NacionalidadesDAO nacionalidadDao;
    int id_retencion = 0;
    float valor = 0;
    String optNewFactura = "factura";
    boolean isHospedaje = false;
    int id_hospedajeU = 0;
    int idHospedaje = 0;
    String fechaSalida = "";
    boolean fireReport = true;
    String datosPago = "";

    ArrayList<RetencionesPagos> listRetencionesPagos = new ArrayList();
    int countAction = 0;
    JFileChooser FileChooser = new JFileChooser();
    String foto = "";
    ImageIcon ii = null;
    String NombreArchivo = "";

    UsuariosApp ua;
    UsuariosAppDAO usersDao;
    ModulosLicenciasDAO mdLicencia;
    private int intentosLogin = 0;

    public PrincipalController() throws SQLException {
        Loader();
    }

    private void Loader() throws SQLException {
        ah.chkEstadiaU.addActionListener(this);
        ah.chkHabitacionU.addActionListener(this);
        pr.btnLogin.addActionListener(this);
        pr.btnCancelar.addActionListener(this);
        pr.txtUser.addActionListener(this);
        pr.txtPass.addActionListener(this);
        pr.pbtnVerPagosHospedaje.addActionListener(this);
        pr.pbtnInfo.addActionListener(this);
        pr.pbtnDeleteCliente.addActionListener(this);
        pr.btnTodosClientes.addActionListener(this);
        pr.btnBuscarFiltroCliente.addActionListener(this);
        pr.mnuReporteFacturacion.addActionListener(this);
        pr.mnuIngresosDiarios.addActionListener(this);
        pr.btnNewContacto.addActionListener(this);
        pr.mnuHospedajeActivo.addActionListener(this);
        th.btnCancelarTarifas.addActionListener(this);
        th.btnActualizarTarifas.addActionListener(this);
        th.cboTipoHospedaje.addActionListener(this);
        pr.mnuTarifasHospedaje.addActionListener(this);
        pr.mnuSalir.addActionListener(this);
        ps.txtCostoUnidad.addFocusListener(this);
        ps.txtPrecioVenta.addFocusListener(this);
        pr.cboFiltroHospedaje.addActionListener(this);
        pr.pbtnEstadoCuenta.addActionListener(this);
        pr.pbtnActualizarHospedaje.addActionListener(this);
        pr.pbtnCancelarHospedaje.addActionListener(this);
        pr.pbtnGenerarPago.addActionListener(this);
        pr.mnuHospedaje.addActionListener(this);
        pr.mnuGenerarFactura.addActionListener(this);
        pr.mnuNumeracion.addActionListener(this);
        pr.mnuResolucion.addActionListener(this);
        pr.mnuVincularUsuarios.addActionListener(this);
        pr.mnuHotel.addActionListener(this);
        pr.mnuCambiarClave.addActionListener(this);
        pr.btnRegistrarHospedaje.addActionListener(this);
        pr.btnFindCliente.addActionListener(this);
        pr.btnNewFactura.addActionListener(this);
        pr.btnAddDetalle.addActionListener(this);
        pr.cboProductoServ.addActionListener(this);
        pr.spCantidad2.addChangeListener(this);
        pr.btnSaveAddPay.addActionListener(this);
        pr.btnSaveAndNew.addActionListener(this);
        pr.btnPreview.addActionListener(this);
        pr.cboTerminoDePago.addActionListener(this);
        pr.btnOlySave.addActionListener(this);
        pr.cboTerminoDePago.addActionListener(this);
        pr.btnFindBill.addActionListener(this);
        pr.jLabel22.setVisible(false);
        pr.txtIdFactura.setVisible(false);
        pr.mnuEmpresas.addActionListener(this);
        pr.jLabel15.setVisible(false);
        pr.cboEstadoFacturaBuscar.setVisible(false);
        pr.jLabel12.setVisible(false);
        pr.jLabel14.setVisible(false);
        pr.cldFechaIni.setVisible(false);
        pr.cldFechaFin.setVisible(false);
        pr.cboTipoFiltro.addActionListener(this);
        pr.pbtnGenerateCopia.addActionListener(this);
        pr.btnCotizar.addActionListener(this);
        pr.mnuCotizaciones.addActionListener(this);
        pr.mnuItem.addActionListener(this);
        rh.btnCancelarHospedaje.addActionListener(this);
        rh.tblHabitaciones.addMouseListener(this);
        rh.btnBuscarCliente.addActionListener(this);
        rh.btnBuscarHabitacion.addActionListener(this);
        rh.btnRegistrarHuesped.addActionListener(this);
        mn.cmbImprimible.addActionListener(this);
        mn.btnGuardarConsecutivo.addActionListener(this);
        mn.btnCancelarImprimible.addActionListener(this);
        rf.btnGuardarResolucion.addActionListener(this);
        rf.cboTipoResolucion.addActionListener(this);
        rf.btnCancelarResolucion.addActionListener(this);
        cc.btnActualizarClave.addActionListener(this);
        cc.btnCancelarCambioClave.addActionListener(this);
        vu.btnGuardarUsuario.addActionListener(this);
        vu.btnCancelarVinculacion.addActionListener(this);
        gp.txtCuatroDigitos.setVisible(false);
        gp.txtVoucher.setVisible(false);
        gp.lblCuatroDigitos.setVisible(false);
        gp.lblVoucher.setVisible(false);
        gp.cboTipoPago.addActionListener(this);
        gp.txtCuatroDigitos.setVisible(false);
        gp.txtVoucher.setVisible(false);
        gp.lblCuatroDigitos.setVisible(false);
        gp.lblVoucher.setVisible(false);
        gp.lblBanco.setVisible(false);
        gp.txtBancos.setVisible(false);
        gp.txtCheque.setVisible(false);
        gp.lblCheque.setVisible(false);
        gp.txtIsHospedaje.setVisible(false);
        gp.txtIsCliente.setVisible(false);
        gp.btnGuardar.addActionListener(this);
        gp.btnCancelar.addActionListener(this);
        pg.lblHospedaje.setVisible(false);
        ps.btnCancelarItem.addActionListener(this);
        ps.btnGuardarItem.addActionListener(this);
        ps.chkProducto.addActionListener(this);
        mrf.chkDetallado.addActionListener(this);
        mc.btnCancelarCliente.addActionListener(this);
        pr.pbtnRemoveItem.addActionListener(this);
        pr.tblDetallefactura.addKeyListener(this);
        mc.cboNacionalidad.addActionListener(this);
        pr.btnRetencion.addActionListener(this);
        gr.cboTipoRetencion.addActionListener(this);
        gr.btnAddRetencion.addActionListener(this);
        pr.pbtnGeneratePago.addActionListener(this);
        pr.btnNewFactura.setText("Nueva Factura");
        pr.pbtnAnulateFactura.addActionListener(this);
        ah.btnActualizarHospedaje.addActionListener(this);
        ah.btnCancelarActualizacion.addActionListener(this);
        String txt = "<html><body>Facturas de Venta <br> Cotizaciones</body></html>";
        emp.imagen.addMouseListener(this);
        pr.jLabel8.setText(txt);
        af.btnGuardarAnulacion.addActionListener(this);
        af.btnCancelarAnulacion.addActionListener(this);
        pr.mnuTodos.addActionListener(this);
        pr.mnuClientes.addActionListener(this);
        pr.mnuProveedores.addActionListener(this);
        pr.pbtnEditCLiente.addActionListener(this);
        ri.btnGenerar.addActionListener(this);
        ri.btnCancelar.addActionListener(this);
        gr.btnCancelar.addActionListener(this);
        emp.editarLogo.addActionListener(this);
        emp.btnGuardar.addActionListener(this);
        pr.tbFacturas.addMouseListener(this);
        mrf.btnGererarReporteFacturacion.addActionListener(this);
        SelectTipoHospedaje.spcantS.addChangeListener(this);
        SelectTipoHospedaje.cboTiposHospedajes.addActionListener(this);
        SelectTipoHospedaje.btnAceptHabitacion.addActionListener(this);
        SelectTipoHospedaje.btnCancelHabitacion.addActionListener(this);
        pr.btnCancel.addActionListener(this);
        pr.pbtnPagosRealizados.addActionListener(this);
        pg.pbtnPrint.addActionListener(this);
        ocultarModulos();
        ocultarPaneles("inicio");
    }

//    public void cargarComponentesNumeracion() throws SQLException {
//        imprimiblesDao = new TiposImprimiblesDAO();
//        int cantImprimibles = imprimiblesDao.getTipoImprimibleList().size();
//        pr.pnNumeracion.removeAll();
//        pr.pnNumeracion.setLayout(new java.awt.GridLayout(cantImprimibles, 4));
//        labels = new JLabel[cantImprimibles];
////        labels2 = new JLabel[cantImprimibles];
//        field = new JTextField[cantImprimibles];
//        field2 = new JTextField[cantImprimibles];
//        int i = 0;
//        Iterator<TiposImprimibles> nombreIterator = imprimiblesDao.getTipoImprimibleList().iterator();
//        while (nombreIterator.hasNext()) {
//            TiposImprimibles tp = nombreIterator.next();
//            labels[i] = new JLabel();
//            labels[i].setText(tp.getNombre());
////            labels2[i] = new JLabel();            
////            labels[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//            field[i] = new JTextField();
//            field2[i] = new JTextField();
//            pr.pnNumeracion.add(labels[i]);
//            pr.pnNumeracion.add(field[i]);
//            pr.pnNumeracion.add(field2[i]);
////            pr.pnNumeracion.add(labels2[i]);
//            i++;
//        }
//        pr.pnNumeracion.updateUI();
//        imprimiblesDao = null;
//    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pr.btnCancelar) {
            System.exit(0);
        }
        if (e.getSource() == pr.txtUser) {
            pr.txtUser.transferFocus();
        }
        if (e.getSource() == pr.txtPass) {
            pr.txtPass.transferFocus();
        }
        if (e.getSource() == pr.btnLogin) {

            if (pr.txtUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo usuario es obligatorio..!");
                pr.txtUser.requestFocus();
                return;
            }

            if (Arrays.toString(pr.txtPass.getPassword()).equals("[]")) {
                JOptionPane.showMessageDialog(null, "El campo contraseña es obligatorio..!");
                pr.txtPass.requestFocus();
                return;
            }

            try {
                usersDao = new UsuariosAppDAO();
                ua = usersDao.getUsuariosApp(pr.txtUser.getText(), Arrays.toString(pr.txtPass.getPassword()));
                if (ua != null) {
//                    mdLicencia = new ModulosLicenciasDAO();
//                    Iterator<ModulosLicencias> itr = mdLicencia.getModulos(ua.getVendedror().getIdEmpresa()).iterator();
//                    while (itr.hasNext()) {
//                        ModulosLicencias next = itr.next();
//                        if (next.getId() == 1) {
//                            System.out.println("1");
//                            pr.mnuFacturacion.setVisible(true);
//                            pr.mnuContactos.setVisible(true);
//                        }
//                        if (next.getId() == 2) {
//                            System.out.println("2");
//                            pr.mnuGastos.setVisible(true);
//                        }
//                        if (next.getId() == 3) {
//                            System.out.println("3");
//                            pr.mnuReportes.setVisible(true);
//                        }
//                        if (next.getId() == 4) {
//                            System.out.println("4");
//                            pr.mnuInventarios.setVisible(true);
//                        }
//                    }

                    Principal.idVendedor.setText(ua.getVendedor().getIdEmpresa());
                    Principal.idUsuarioLog.setText(ua.getIdUsuario());
                    Principal.idVendedor.setVisible(false);
                    Principal.idUsuarioLog.setVisible(false);
                    pr.clVencimientomanual.setVisible(false);
                    Principal.IdResolucion.setVisible(false);
                    pr.jMenuBar1.setVisible(true);
                    int rol = Utils.Utils.getRolbyIdentificacion(ua.getIdUsuario());
                    cargarClientes();
                    cargarTerminosPago();
                    cargarProductosServ();
                    cargarFranquicias();
                    cargarTiposPago();
                    cargarImpuestos();
                    cargarTipoRetencion();
                    ocultarPaneles("inicio");
                    cargarTiposIdMC();
                    cargarNacionalidades();
                    cargarCiudades();
                    Utils.Utils.facturasSinPagar();
                    pr.pnLogin.setVisible(false);
                    pr.lblWelcome.setVisible(true);
                    pr.txtUser.setText("");
                    pr.txtPass.setText("");
                    switch (rol) {
                        case 1:
                            System.out.println("soy Administrador");
                            pr.mnuGenerarFactura.setEnabled(false);
                            pr.mnuCotizaciones.setEnabled(false);
                            pr.mnuHospedajeActivo.setEnabled(false);
                            pr.mnuItem.setEnabled(false);
                            pr.mnuHospedaje.setEnabled(false);
                            pr.mnuTarifasHospedaje.setEnabled(false);
                            pr.mnuResolucion.setEnabled(false);
                            pr.mnuNumeracion.setEnabled(false);
                            pr.mnuVincularUsuarios.setEnabled(false);
                            pr.mnuIngresosDiarios.setEnabled(false);
                            pr.mnuReporteFacturacion.setEnabled(false);
                            pr.mnuTodos.setEnabled(false);
                            pr.mnuProveedores.setEnabled(false);
                            pr.pbtnDeleteCliente.setEnabled(false);
                            pr.btnRegistrarHospedaje.setEnabled(false);
                            pr.pbtnGenerarPago.setEnabled(false);
                            pr.btnRetencion.setEnabled(false);
                            pr.btnSaveAddPay.setEnabled(false);
                            pr.btnSaveAndNew.setEnabled(false);
                            pr.btnOlySave.setEnabled(false);
                            pr.pbtnGeneratePago.setEnabled(false);
                            break;

                        case 2:
                            System.out.println("soy Supervisor");
                            pr.mnuGenerarFactura.setEnabled(true);
                            pr.btnNewFactura.setEnabled(false);
                            pr.pbtnAnulateFactura.setEnabled(true);
                            pr.pbtnEditCLiente.setEnabled(true);
                            pr.pbtnCancelarHospedaje.setEnabled(true);
                            pr.mnuCotizaciones.setEnabled(false);
                            pr.mnuHospedajeActivo.setEnabled(true);
                            pr.mnuHospedaje.setEnabled(true);
                            pr.mnuTarifasHospedaje.setEnabled(true);
                            pr.mnuItem.setEnabled(true);
                            pr.mnuResolucion.setEnabled(true);
                            pr.mnuNumeracion.setEnabled(true);
                            pr.mnuVincularUsuarios.setEnabled(true);
                            pr.mnuIngresosDiarios.setEnabled(true);
                            pr.mnuReporteFacturacion.setEnabled(true);
                            pr.mnuTodos.setEnabled(false);
                            pr.mnuProveedores.setEnabled(false);
                            pr.pbtnDeleteCliente.setEnabled(true);
                            pr.btnRegistrarHospedaje.setEnabled(false);
                            pr.pbtnGenerarPago.setEnabled(false);
                            pr.btnRetencion.setEnabled(false);
                            pr.btnSaveAddPay.setEnabled(false);
                            pr.btnSaveAndNew.setEnabled(false);
                            pr.btnOlySave.setEnabled(false);
                            pr.pbtnGeneratePago.setEnabled(false);
                            break;

                        case 3:
                            System.out.println("soy Operario");
                            pr.mnuGenerarFactura.setEnabled(true);
                            pr.btnNewFactura.setEnabled(true);
                            pr.pbtnAnulateFactura.setEnabled(false);
                            pr.pbtnEditCLiente.setEnabled(false);
                            pr.pbtnCancelarHospedaje.setEnabled(false);
                            pr.mnuCotizaciones.setEnabled(true);
                            pr.mnuHospedajeActivo.setEnabled(true);
                            pr.mnuHospedaje.setEnabled(true);
                            pr.mnuTarifasHospedaje.setEnabled(false);
                            pr.mnuItem.setEnabled(false);
                            pr.mnuResolucion.setEnabled(false);
                            pr.mnuNumeracion.setEnabled(false);
                            pr.mnuVincularUsuarios.setEnabled(false);
                            pr.mnuIngresosDiarios.setEnabled(true);
                            pr.mnuReporteFacturacion.setEnabled(false);
                            pr.mnuTodos.setEnabled(false);
                            pr.mnuProveedores.setEnabled(false);
                            pr.pbtnDeleteCliente.setEnabled(false);
                            pr.btnRegistrarHospedaje.setEnabled(true);
                            pr.pbtnGenerarPago.setEnabled(true);
                            pr.btnRetencion.setEnabled(true);
                            pr.btnSaveAddPay.setEnabled(true);
                            pr.btnSaveAndNew.setEnabled(true);
                            pr.btnOlySave.setEnabled(true);
                            pr.pbtnGeneratePago.setEnabled(true);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Usuario o clave no validos!");
                    pr.txtUser.setText("");
                    pr.txtPass.setText("");
                    pr.txtUser.requestFocus();
                }
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }

        }

        if (e.getSource() == emp.btnGuardar) {
            try {
                if (Utils.Utils.updateLogoEmpresa(foto, emp.idEmpresa.getText())) {
                    JOptionPane.showMessageDialog(null, "Logo Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Eror al actualizar logo");
                }
                countAction = 0;
                foto = "";
                ii = null;
                NombreArchivo = "";
                emp.imagen.setIcon(null);
                emp.idEmpresa.setText("");
            } catch (FileNotFoundException ex) {
                System.out.println("error " + ex);
            }

        }

        if (e.getSource() == pr.mnuEmpresas) {
            cargarEmpresas();
            emp.setLocationRelativeTo(null);
            emp.setVisible(true);
        }

        if (e.getSource() == emp.editarLogo) {
            int fila = emp.tblEmpresas.getSelectedRow();
            if (fila >= 0) {
                String idEmpresa = emp.tblEmpresas.getValueAt(fila, 0).toString();
                emp.idEmpresa.setText(idEmpresa);
            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un Cliente!");
            }
        }

        if (e.getSource() == ah.chkEstadiaU) {
            if (ah.chkEstadiaU.isSelected()) {
                ah.lblDias.setVisible(true);
                ah.spnEstadia.setVisible(true);
            } else {
                ah.lblDias.setVisible(false);
                ah.spnEstadia.setVisible(false);
            }
        }

        if (e.getSource() == ah.chkHabitacionU) {
            if (ah.chkHabitacionU.isSelected()) {
                ah.lblHabitacion.setVisible(true);
                ah.cboHabitacionesDisponibles.setVisible(true);
            } else {
                ah.lblHabitacion.setVisible(false);
                ah.cboHabitacionesDisponibles.setVisible(false);
            }
        }

        if (e.getSource() == pr.pbtnDeleteCliente) {
            int fila = pr.tblUsers.getSelectedRow();
            if (fila >= 0) {
                int id_cliente = Integer.parseInt(pr.tblUsers.getValueAt(fila, 0).toString());
                Object[] opciones = {"Continuar", "Cancelar"};
                int eleccion = JOptionPane.showOptionDialog(null, "¿En realidad, desea eliminar el cliente?", "Mensaje de Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opciones, "Continuar");
                if (eleccion == JOptionPane.YES_OPTION) {
                    try {
                        if (!Utils.Utils.clienteFactura(id_cliente) && !Utils.Utils.clienteFactura(id_cliente)) {
                            if (Utils.Utils.deleteCliente(id_cliente)) {
                                JOptionPane.showMessageDialog(null, "¡Base de datos actualizada correctamente!");
                                cargarUsuarios("clientes", "");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Eliminación no permitida!");
                        }
                    } catch (SQLException ex) {
                        System.out.println("error " + ex);
                    }
                } else {
                }

            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un Cliente!");
            }
        }

        if (e.getSource() == pr.btnBuscarFiltroCliente) {
            String and = "";
            Object[] campos = {pr.txtCriterioBusqueda, pr.cboCriterioBusqueda};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar los criterios de búsqueda!");
                return;
            }
            String filtro = (String) pr.cboCriterioBusqueda.getSelectedItem().toString();
            if (filtro.equals("Nombre Cliente")) {
                and = " and nombre_razon like '%" + pr.txtCriterioBusqueda.getText() + "%'";
            } else {
                and = " and identificacion = '" + pr.txtCriterioBusqueda.getText() + "'";
            }
            try {
                cargarUsuarios("clientes", and);
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.btnTodosClientes) {
            try {
                cargarUsuarios("clientes", "");
                pr.txtCriterioBusqueda.setText("");
                pr.cboCriterioBusqueda.setSelectedItem("-- Seleccione --");
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.pbtnPagosRealizados) {
            int fila = pr.tbFacturas.getSelectedRow();
            if (fila >= 0) {
                String id_factura = pr.tbFacturas.getValueAt(fila, 0).toString();
                cargarTblPagosRealizados(id_factura, 0);
                pg.titulo.setText("Pagos Realizados a la factura No. " + id_factura);
                pg.factura.setText(id_factura);
                pg.setLocationRelativeTo(null);
                pg.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

        if (e.getSource() == pr.pbtnVerPagosHospedaje) {
            int fila = pr.tblListaHospedaje.getSelectedRow();
            if (fila >= 0) {
                int id_hospedaje = (int) pr.tblListaHospedaje.getValueAt(fila, 1);
                String idFactura = pr.tblListaHospedaje.getValueAt(fila, 9).toString();
                cargarTblPagosRealizados(idFactura, id_hospedaje);
                pg.titulo.setText("Pagos Realizados al hospedaje No. " + id_hospedaje);
                pg.lblHospedaje.setText(String.valueOf(id_hospedaje));
                pg.setLocationRelativeTo(null);
                pg.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

        if (e.getSource() == pg.pbtnPrint) {
            int fila = pg.tblPagosRealizados.getSelectedRow();
            if (fila >= 0) {
                try {
                    pg.dispose();
                    Utils.Utils.generateReciboCaja(Integer.parseInt(pg.tblPagosRealizados.getValueAt(fila, 0).toString()), Principal.idVendedor.getText(), "", "COPIA");
                } catch (SQLException | JRException ex) {
                    System.out.println("error " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

        if (e.getSource() == pr.btnCancel) {
            limpiarFactura();
            isHospedaje = false;
            id_hospedajeU = 0;
            facturaDao = null;
            hospedajeDao = null;
            if (optNewFactura.equals("factura")) {
                ocultarPaneles("mnuFacturacion");
                try {
                    cargarTablaFactura("", true);
                } catch (Exception ex) {
                    System.out.println("error " + ex);
                }
            } else {
                ocultarPaneles("mnuFacturacion");
                try {
                    cargarTablaFactura("", false);
                } catch (Exception ex) {
                    System.out.println("error " + ex);
                }
            }
        }

        if (e.getSource() == pr.pbtnGenerarPago) {
            int fila1 = pr.tblListaHospedaje.getSelectedRow();
            if (fila1 >= 0) {
                try {
                    String idFactura = pr.tblListaHospedaje.getValueAt(fila1, 9).toString();
                    factura = Utils.Utils.getFacturaById(idFactura);
                    if (factura != null) {
                        try {
                            fireReport = false;
                            float abonos = Utils.Utils.getAbonos(idFactura, 0);
                            float retenciones = Utils.Utils.getRetencionesByFactura(idFactura);
                            float totalFactura = Utils.Utils.getTotalFactura(idFactura);
                            float saldo = totalFactura - retenciones - abonos;
                            gp.txtTotalAPagar.setText(formateador.format(saldo));
                            gp.setLocationRelativeTo(null);
                            gp.setVisible(true);
                        } catch (SQLException ex) {
                            System.out.println("error " + ex);
                        }
                    } else {
                        try {
                            int diasN = 0;
                            if (pr.tblListaHospedaje.getValueAt(fila1, 3).toString().equals("")) {
                                diasN = Utils.Utils.getDifferenceDays(sb.parse(pr.tblListaHospedaje.getValueAt(fila1, 2).toString()), new Date());
                            } else {
                                diasN = Utils.Utils.getDifferenceDays(sb.parse(pr.tblListaHospedaje.getValueAt(fila1, 2).toString()), sb.parse(pr.tblListaHospedaje.getValueAt(fila1, 3).toString()));
                            }
                            if (diasN == 0) {
                                diasN = 1;
                            }
                            gp.txtIsHospedaje.setText(pr.tblListaHospedaje.getValueAt(fila1, 1).toString());
                            gp.txtIsCliente.setText(pr.tblListaHospedaje.getValueAt(fila1, 0).toString());
                            calcularCostoNoche((int) pr.tblListaHospedaje.getValueAt(fila1, 4), (int) Utils.Utils.getHabitacionByIdHospedaje((int) pr.tblListaHospedaje.getValueAt(fila1, 1)));
                            float costoNocheN = valor * (float) diasN;
                            float ivaN = costoNocheN * (float) 0.19;
                            float CostoIvaIncluido = costoNocheN + ivaN;
                            float abonosN = Utils.Utils.getAbonos("", (int) pr.tblListaHospedaje.getValueAt(fila1, 1));
                            float saldo = CostoIvaIncluido - abonosN;
                            gp.txtTotalAPagar.setText(formateador.format(saldo));
                            gp.setLocationRelativeTo(null);
                            gp.setVisible(true);
                        } catch (ParseException ex) {
                            System.out.println("error " + ex);
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un registro!");
            }
        }

        if (e.getSource() == pr.mnuReporteFacturacion) {
            mrf.jPanel1.setVisible(false);
            mrf.txtIdCliente.setVisible(false);
            mrf.cboEstadoFactura.setVisible(false);
            mrf.setLocationRelativeTo(null);
            mrf.setVisible(true);
        }

        if (e.getSource() == pr.mnuIngresosDiarios) {
            ri.setLocationRelativeTo(null);
            ri.setVisible(true);
        }

        if (e.getSource() == mrf.btnGererarReporteFacturacion) {
            Object[] campos = {mrf.cldFechaIni, mrf.cldFechaFin, mrf.chkDetallado};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            try {
                String params = "";
                if (mrf.chkDetallado.isSelected()) {
                    int estado = (int) mrf.cboEstadoFactura.getSelectedIndex();
                    if (!mrf.txtIdCliente.getText().trim().equals("")) {
                        if (Utils.Utils.identificacionValida(mrf.txtIdCliente.getText().trim())) {
                            params = "AND c.identificacion = '" + mrf.txtIdCliente.getText().trim() + "' ";
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Identificación no encontrada en la base de datos!");
                            mrf.txtIdCliente.setText("");
                            return;
                        }
                    }
                    if (estado > 0) {
                        params += "AND f.id_estado_factura = " + estado + " ";
                    }
                }
                Utils.Utils.generarFacturacion(mrf.cldFechaIni.getDate(), mrf.cldFechaFin.getDate(), params, Principal.idVendedor.getText());
                mrf.cldFechaIni.setDate(null);
                mrf.cldFechaFin.setDate(null);
                mrf.txtIdCliente.setText("");
                mrf.dispose();
            } catch (SQLException | JRException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == mrf.btnCancelarModalReporteF) {
            mrf.cldFechaIni.setDate(null);
            mrf.cldFechaFin.setDate(null);
            mrf.txtIdCliente.setText("");
            mrf.chkDetallado.setSelected(false);
            mrf.dispose();
        }

        if (e.getSource() == ri.btnGenerar) {
            if (ri.cldFechaini.getDate() == null) {
                JOptionPane.showMessageDialog(null, "¡Seleccione Rango de fechas!");
                ri.cldFechaini.requestFocus();
                return;
            }
            if (ri.cldFechafin.getDate() == null) {
                JOptionPane.showMessageDialog(null, "¡Seleccione Rango de fechas!");
                ri.cldFechafin.requestFocus();
                return;
            }
            try {
                Utils.Utils.gerarIngresosDiarios(ri.cldFechaini.getDate(), ri.cldFechafin.getDate(), Principal.idUsuarioLog.getText(), Principal.idVendedor.getText());
                ri.cldFechaini.setDate(null);
                ri.cldFechafin.setDate(null);
                ri.dispose();
            } catch (SQLException | JRException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == ri.btnCancelar) {
            ri.cldFechaini.setDate(null);
            ri.cldFechafin.setDate(null);
            ri.dispose();
        }

        if (e.getSource() == pr.mnuTodos) {
            try {
                cargarUsuarios("", "");
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            ocultarPaneles("mnuTodos");
        }

        if (e.getSource() == pr.mnuClientes) {
            try {
                cargarUsuarios("clientes", "");
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            ocultarPaneles("mnuTodos");
        }

        if (e.getSource() == pr.mnuProveedores) {
            try {
                cargarUsuarios("proveedores", "");
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            ocultarPaneles("mnuTodos");
        }

        if (e.getSource() == pr.mnuHospedajeActivo) {
            try {
                Utils.Utils.generateReporteHospedaje(Principal.idVendedor.getText());
            } catch (SQLException | JRException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == th.cboTipoHospedaje) {
            String tipoHabitacion = (String) (th.cboTipoHospedaje.getSelectedItem());
            if (tipoHabitacion == null) {
                return;
            }
            if (tipoHabitacion.equals("-- Seleccione --")) {
                th.txtCostoPersona.setText("");
                th.txtCostoPareja.setText("");
                th.txtCostoAdicional.setText("");
                return;
            }
            String array[] = tipoHabitacion.split("-");
            int idTipoHabitacion = Integer.parseInt(array[0]);
            TiposDeHabitacion tipo = tiposList.get(idTipoHabitacion - 1);

            th.txtCostoPersona.setText(formateador.format(tipo.getCostoPersona()));
            th.txtCostoPareja.setText(formateador.format(tipo.getCostoPareja()));
            th.txtCostoAdicional.setText(formateador.format(tipo.getCostoPersonaAdicional()));
        }

        if (e.getSource() == th.btnCancelarTarifas) {
            Object[] campos = {th.txtCostoAdicional, th.txtCostoPareja, th.txtCostoPersona, th.cboTipoHospedaje};
            cleanCampos(campos);
            th.dispose();
        }

        if (e.getSource() == th.btnActualizarTarifas) {
            Object[] campos = {th.txtCostoAdicional, th.txtCostoPareja, th.txtCostoPersona, th.cboTipoHospedaje};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            String idTipoHabitacion = (String) th.cboTipoHospedaje.getSelectedItem();
            String[] tipoHabitacion = idTipoHabitacion.split("-");
            String costoPersona = th.txtCostoPersona.getText();
            costoPersona = costoPersona.replace(".", "");
            String costoPareja = th.txtCostoPareja.getText();
            costoPareja = costoPareja.replace(".", "");
            String costoAdicional = th.txtCostoAdicional.getText();
            costoAdicional = costoAdicional.replace(".", "");
            try {
                if (Utils.Utils.updateTarifasHabitaciones(Integer.parseInt(tipoHabitacion[0]), Float.parseFloat(costoPersona), Float.parseFloat(costoPareja), Float.parseFloat(costoAdicional))) {
                    JOptionPane.showMessageDialog(null, "¡Se actualizaron correctamente las tarifas!");
                    cleanCampos(campos);
                    th.dispose();
                }
            } catch (SQLException ex) {
                System.out.println("error" + ex);
            }
        }

        if (e.getSource() == pr.mnuTarifasHospedaje) {
            try {
                cargarTipoHabitaciones();
                th.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error " + ex);;
            }
        }

        if (e.getSource() == pr.mnuSalir) {
            cerrar();
        }

        if (e.getSource() == gr.cboTipoRetencion) {
            String val = (String) gr.cboTipoRetencion.getSelectedItem();
            if (val == null) {
                return;
            }
            if (val.equals("-- Seleccione --")) {
                return;
            }
            String parts[] = val.split(" ");
            id_retencion = Integer.parseInt(parts[0]);
            String subtotal = "";
            if (id_retencion != 12) {
                subtotal = pr.lblSubtotal.getText();
            } else {
                subtotal = pr.lblTotalImpuesto.getText();
            }
            float val2 = (Utils.Utils.getTarifaRetencionById(id_retencion) / (float) 100);
            String newSubtotal = subtotal.replace(".", "");
            valor = Float.parseFloat(newSubtotal) * val2;
            gr.lblValorRetencion.setText(formateador.format(valor));

        }

        if (e.getSource() == pr.btnRetencion) {
            if (!detalleFacturaItems.isEmpty()) {
                gr.setLocationRelativeTo(null);
                gr.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "¡No puedes aplicar retención a una factura sin valor!");
            }
        }

        if (e.getSource() == gr.btnAddRetencion) {
            String val = (String) gr.cboTipoRetencion.getSelectedItem();
            if (val.equals("-- Seleccione --")) {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar una opción!");
            } else {
                try {
                    if (valor > 0) {
                        RetencionesPagos r = new RetencionesPagos(pr.txtConsecutivoFactura.getText(), id_retencion, valor);
                        addRetencionFactura(r);
                    } else {
                        JOptionPane.showMessageDialog(null, "NO es necesario aplicar retención");
                    }
                    gr.cboTipoRetencion.setSelectedItem("-- Seleccione --");
                    gr.lblValorRetencion.setText("");
                } catch (SQLException ex) {
                    System.out.println("error linea 317 " + ex);
                }
            }
        }

        if (e.getSource() == pr.cboFiltroHospedaje) {
            if (pr.cboFiltroHospedaje.getSelectedItem().equals("Sin Facturar") || pr.cboFiltroHospedaje.getSelectedItem().equals("Anulada")) {
                pr.pbtnEstadoCuenta.setEnabled(true);
            } else {
                pr.pbtnEstadoCuenta.setEnabled(false);
            }
            if (pr.cboFiltroHospedaje.getSelectedItem().equals("Cobrada")) {
                pr.pbtnGenerarPago.setEnabled(false);
            } else {
                pr.pbtnGenerarPago.setEnabled(true);
            }
            cargarHospedajes(pr.cboFiltroHospedaje.getSelectedItem().toString(), pr.txtFiltrroIdCliente.getText());
        }

        if (e.getSource() == gr.btnCancelar) {
            id_retencion = 0;
            valor = 0;
            gr.lblValorRetencion.setText("");
            gr.dispose();
        }

        if (e.getSource() == pr.pbtnRemoveItem) {
            int fila = pr.tblDetallefactura.getSelectedRow();
            if (fila >= 0) {
                int item = Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 6).toString());
                detalleFacturaItems.remove(item);
                try {
                    cargarDetallaFatutra(-1, 0, false);
                } catch (SQLException ex) {
                    System.out.println("error linea 297 " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un ítem!");
            }
        }

        if (e.getSource() == rh.btnCancelarHospedaje) {
            Object[] campos = {rh.txtHuesped, rh.chkAmericana, rh.chkChalet, Principal.lblCliente, rh.cboMotivo};
            cleanCampos(campos);
            limpiarTables(rh.tblHabitaciones);
            Principal.lblCliente.setEditable(true);
            rh.txtHuesped.setEditable(true);
            rh.dispose();
        }

        if (e.getSource() == mc.cboNacionalidad) {
            String v = (String) mc.cboNacionalidad.getSelectedItem();
            if (v == null) {
                return;
            } else if (!mc.cboNacionalidad.getSelectedItem().equals("Colombia - 52")) {
                mc.txtCiudad.setEnabled(false);
                mc.txtDireccion.setEnabled(false);
            } else {
                try {
                    if (mc.idupdate.getText().equals("")) {
                        mc.txtCiudad.setEnabled(true);
                        cargarCiudades();
                        mc.txtCiudad.setSelectedItem("-- Seleccione --");
                    }
                    mc.txtDireccion.setEnabled(true);
                } catch (SQLException ex) {
                    System.out.println("error" + ex);
                }
            }
        }

        if (e.getSource() == pr.pbtnEstadoCuenta) {
            pr.btnSaveAddPay.setEnabled(true);
            pr.btnSaveAndNew.setEnabled(true);
            pr.btnOlySave.setEnabled(true);
            pr.btnRetencion.setEnabled(true);
            isHospedaje = true;
            int fila2 = pr.tblListaHospedaje.getSelectedRow();
            id_hospedajeU = (int) pr.tblListaHospedaje.getValueAt(fila2, 1);
            detalleFacturaItems.clear();
            limpiarFactura();
            facturarHospedaje();
        }

        if (e.getSource() == pr.pbtnCancelarHospedaje) {
            int fila1 = pr.tblListaHospedaje.getSelectedRow();
            if (fila1 >= 0) {
                int habitacion = (int) pr.tblListaHospedaje.getValueAt(fila1, 7);
                String estado = pr.tblListaHospedaje.getValueAt(fila1, 8).toString();
                int hospedaje = (int) pr.tblListaHospedaje.getValueAt(fila1, 1);
                try {
                    float abonos = Utils.Utils.getAbonos("", hospedaje);
                    if (abonos == 0.0) {
                        if (estado.equals("Sin facturar") || estado.equals("Factura anulada")) {
                            Object[] opciones = {"Continuar", "Cancelar"};
                            int eleccion = JOptionPane.showOptionDialog(null, "¿En realidad, desea cancelar el hospedaje?", "Mensaje de Confirmación",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, opciones, "Continuar");
                            if (eleccion == JOptionPane.YES_OPTION) {
                                if (Utils.Utils.cancelarHospedaje(hospedaje)) {
                                    JOptionPane.showMessageDialog(null, "¡Cancelación exitosa!");
                                    cargarHospedajes("", "");
                                    try {
                                        if (Utils.Utils.updateDisponibilidadHabitacion(habitacion, 0)) {
                                        } else {
                                            JOptionPane.showMessageDialog(null, "¡Error con la disponibilidad de la habitación!");
                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("error " + ex);
                                    }
                                }
                            } else {
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "¡No se puede cancelar un hospedaje con factura asociada!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡No se puede cancelar un hospedaje con pagos asociados!");
                    }
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un registro!");
            }
        }

        if (e.getSource() == pr.pbtnActualizarHospedaje) {
            int fila2 = pr.tblListaHospedaje.getSelectedRow();
            if (fila2 >= 0) {
                String estado2 = pr.tblListaHospedaje.getValueAt(fila2, 8).toString();
                String salida = pr.tblListaHospedaje.getValueAt(fila2, 3).toString();
                int adultos = Integer.parseInt(pr.tblListaHospedaje.getValueAt(fila2, 10).toString());
                int ninos = Integer.parseInt(pr.tblListaHospedaje.getValueAt(fila2, 11).toString());
                ah.spnAdultosU.setModel(new javax.swing.SpinnerNumberModel(adultos, adultos, null, 1));
                ah.spnNinosU.setModel(new javax.swing.SpinnerNumberModel(ninos, ninos, null, 1));
                if (salida.equals("")) {
                    ah.chkEstadiaU.setVisible(false);
                } else {
                    ah.chkEstadiaU.setVisible(true);
                    try {
                        int dias = Utils.Utils.getDifferenceDays(df.parse(pr.tblListaHospedaje.getValueAt(fila2, 2).toString()), df.parse(salida));
                        ah.spnEstadia.setModel(new javax.swing.SpinnerNumberModel(dias + 1, dias + 1, null, 1));
                    } catch (ParseException ex) {
                        System.out.println("error " + ex);
                    }
                }
                if (estado2.equals("Sin facturar") || estado2.equals("Factura anulada")) {
                    try {
                        cargarHabitacionesU();
                    } catch (SQLException ex) {
                        System.out.println("error" + ex);
                    }
                    ah.lblHabitacion.setVisible(false);
                    ah.lblDias.setVisible(false);
                    ah.spnEstadia.setVisible(false);
                    ah.cboHabitacionesDisponibles.setVisible(false);
                    ah.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se puede actualizar un registro con factura asociada!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un registro!");
            }
        }

        if (e.getSource() == ah.btnCancelarActualizacion) {
            Object[] campos = {ah.cboHabitacionesDisponibles};
            cleanCampos(campos);
            ah.dispose();
        }

        if (e.getSource() == ah.btnActualizarHospedaje) {
            try {
                int fila3 = pr.tblListaHospedaje.getSelectedRow();
                int habitacion3 = 0;
                Date fechaEntrada = sa.parse(pr.tblListaHospedaje.getValueAt(fila3, 2).toString());
                String fechaSalida = "";
                int hospedaje3 = (int) pr.tblListaHospedaje.getValueAt(fila3, 1);
                String habitacion = ah.cboHabitacionesDisponibles.getSelectedItem().toString();
                String[] parts = habitacion.split("-");
                if (ah.chkHabitacionU.isSelected()) {
                    habitacion3 = Integer.parseInt(parts[1].trim());
                    Object[] campos = {ah.cboHabitacionesDisponibles};
                    if (validarCampos(campos)) {
                        JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                        return;
                    }
                } else {
                    habitacion3 = (int) pr.tblListaHospedaje.getValueAt(fila3, 7);
                }
                if (ah.chkEstadiaU.isSelected()) {
                    fechaSalida = sb.format(sumarRestarDiasFecha(fechaEntrada, (int) ah.spnEstadia.getValue(), 1));
                }
                int personas = (int) ah.spnAdultosU.getValue() + (int) ah.spnNinosU.getValue();
                try {
                    calcularCostoNoche(personas, habitacion3);
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
                ArrayList<String> consultas = new ArrayList();
                consultas.add("update hospedajes set numero_adultos = " + ah.spnAdultosU.getValue() + ", numero_ninos = " + ah.spnNinosU.getValue() + ", valor_noche = " + valor + " where id_hospedaje = " + hospedaje3 + "");
                if (ah.chkHabitacionU.isSelected()) {
                    consultas.add("update hospedajes set id_habitacion = " + Integer.parseInt(parts[1].trim()) + " where id_hospedaje = " + hospedaje3 + "");
                    consultas.add("update habitaciones set disponible = 0 where id_habitacion = " + habitacion3 + "");
                    consultas.add("update habitaciones set disponible = 1 where id_habitacion = " + parts[1].trim() + "");
                }
                if (ah.chkEstadiaU.isSelected()) {
                    consultas.add("update hospedajes set fecha_salida = '" + fechaSalida + "' where id_hospedaje = " + hospedaje3 + "");
                }
                hospedajeDao = new HospedajesDAO();
                if (hospedajeDao.updateList(consultas)) {
                    JOptionPane.showMessageDialog(null, "¡Actualización exitosa!");
                    Object[] campos1 = {ah.cboHabitacionesDisponibles};
                    cleanCampos(campos1);
                    cargarHospedajes("", "");
                    ah.dispose();
                }
            } catch (ParseException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.mnuItem) {
            Object[] numeros = {ps.txtCostoUnidad, ps.txtPrecioVenta};
            SLetra(numeros, false);
            try {
                cargarUnidadesDeMedida();
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            ps.cboUnidad.setVisible(false);
            ps.spnStock.setVisible(false);
            ps.lblStock.setVisible(false);
            ps.lblUnidad.setVisible(false);
            ps.txtCostoUnidad.setEnabled(false);
            ps.txtPrecioVenta.setEnabled(false);
            ps.txtCostoUnidad.setText("0");
            ps.txtPrecioVenta.setText("0");
            ps.setVisible(true);
        }

        if (e.getSource() == ps.btnCancelarItem) {
            Object[] campos = {ps.txtCostoUnidad, ps.txtNombreItem, ps.txtPrecioVenta, ps.txtReferenciaItem, ps.areaDescripcion, ps.cboGarantia, ps.spnStock, ps.cboUnidad, ps.chkProducto};
            cleanCampos(campos);
            ps.cboUnidad.setVisible(false);
            ps.spnStock.setVisible(false);
            ps.lblStock.setVisible(false);
            ps.lblUnidad.setVisible(false);
            ps.txtCostoUnidad.setEnabled(false);
            ps.txtPrecioVenta.setEnabled(false);
            ps.txtCostoUnidad.setText("0");
            ps.txtPrecioVenta.setText("0");
            ps.dispose();
        }

        if (e.getSource() == mrf.chkDetallado) {
            Object[] campos = {mrf.txtIdCliente};
            cleanCampos(campos);
            if (!mrf.chkDetallado.isSelected()) {
                mrf.jPanel1.setVisible(false);
                mrf.txtIdCliente.setVisible(false);
                mrf.cboEstadoFactura.setVisible(false);
            } else {
                mrf.jPanel1.setVisible(true);
                mrf.txtIdCliente.setVisible(true);
                mrf.cboEstadoFactura.setVisible(true);
            }
        }

        if (e.getSource() == ps.chkProducto) {

            Object[] campos = {ps.spnStock, ps.cboUnidad};
            cleanCampos(campos);
            if (!ps.chkProducto.isSelected()) {
                ps.cboUnidad.setVisible(false);
                ps.spnStock.setVisible(false);
                ps.lblStock.setVisible(false);
                ps.lblUnidad.setVisible(false);
                ps.txtCostoUnidad.setEnabled(false);
                ps.txtPrecioVenta.setEnabled(false);
                ps.txtCostoUnidad.setText("0");
                ps.txtPrecioVenta.setText("0");
            } else {
                ps.cboUnidad.setVisible(true);
                ps.spnStock.setVisible(true);
                ps.lblStock.setVisible(true);
                ps.lblUnidad.setVisible(true);
                ps.txtCostoUnidad.setEnabled(true);
                ps.txtPrecioVenta.setEnabled(true);
            }
        }

        if (e.getSource() == ps.btnGuardarItem) {

            if (!ps.chkProducto.isSelected()) {
                Object[] campos = {ps.txtCostoUnidad, ps.txtNombreItem, ps.txtPrecioVenta, ps.txtReferenciaItem, ps.cboGarantia};
                if (validarCampos(campos)) {
                    JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                    return;
                }
            } else {
                Object[] campos = {ps.txtCostoUnidad, ps.txtNombreItem, ps.txtPrecioVenta, ps.txtReferenciaItem, ps.areaDescripcion, ps.cboGarantia, ps.spnStock, ps.cboUnidad};
                if (validarCampos(campos)) {
                    JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                    return;
                }
            }
            try {
                guardarItem();
                Object[] campos = {ps.txtCostoUnidad, ps.txtNombreItem, ps.txtPrecioVenta, ps.txtReferenciaItem, ps.areaDescripcion, ps.cboGarantia, ps.cboUnidad, ps.chkProducto};
                cleanCampos(campos);
                ps.cboUnidad.setVisible(false);
                ps.spnStock.setVisible(false);
                ps.lblStock.setVisible(false);
                ps.lblUnidad.setVisible(false);
                ps.dispose();

            } catch (SQLException ex) {
                System.out.println("error" + ex);
            }
        }

        if (e.getSource() == pr.cboTipoFiltro) {
            String filtro = (String) pr.cboTipoFiltro.getSelectedItem();
            if (filtro == null) {
                return;
            }
            switch (filtro) {
                case "Estado Factura":
                    pr.jLabel12.setVisible(false);
                    pr.jLabel14.setVisible(false);
                    pr.cldFechaIni.setVisible(false);
                    pr.cldFechaFin.setVisible(false);
                    pr.jLabel22.setVisible(false);
                    pr.txtIdFactura.setVisible(false);
                    pr.jLabel15.setVisible(true);
                    pr.cboEstadoFacturaBuscar.setVisible(true);
                    break;
                case "Rango De Fechas":
                    pr.jLabel15.setVisible(false);
                    pr.cboEstadoFacturaBuscar.setVisible(false);
                    pr.jLabel22.setVisible(false);
                    pr.txtIdFactura.setVisible(false);
                    pr.jLabel12.setVisible(true);
                    pr.jLabel14.setVisible(true);
                    pr.cldFechaIni.setVisible(true);
                    pr.cldFechaFin.setVisible(true);
                    break;
                case "Id Factura":
                    pr.jLabel12.setVisible(false);
                    pr.jLabel22.setText("No. Factura:");
                    pr.jLabel14.setVisible(false);
                    pr.cldFechaIni.setVisible(false);
                    pr.cldFechaFin.setVisible(false);
                    pr.jLabel15.setVisible(false);
                    pr.cboEstadoFacturaBuscar.setVisible(false);
                    pr.jLabel22.setVisible(true);
                    pr.txtIdFactura.setText("");
                    pr.txtIdFactura.setVisible(true);
                    break;
                case "Id Cliente":
                    pr.jLabel12.setVisible(false);
                    pr.jLabel22.setText("Id. Cliente:");
                    pr.jLabel14.setVisible(false);
                    pr.cldFechaIni.setVisible(false);
                    pr.cldFechaFin.setVisible(false);
                    pr.jLabel15.setVisible(false);
                    pr.cboEstadoFacturaBuscar.setVisible(false);
                    pr.jLabel22.setVisible(true);
                    pr.txtIdFactura.setText("");
                    pr.txtIdFactura.setVisible(true);
                    break;
                case "-- Seleccione --":
                    break;
                default:
                    pr.jLabel12.setVisible(false);
                    pr.jLabel14.setVisible(false);
                    pr.cldFechaIni.setVisible(false);
                    pr.cldFechaFin.setVisible(false);
                    pr.jLabel15.setVisible(false);
                    pr.cboEstadoFacturaBuscar.setVisible(false);
                    pr.jLabel22.setVisible(false);
                    pr.txtIdFactura.setVisible(false);
                    try {
                        cargarTablaFactura("", true);
                        pr.cboEstadoFacturaBuscar.setSelectedItem("-- Seleccione --");
                        pr.txtIdFactura.setText("");
                        pr.cldFechaIni.setDate(null);
                        pr.cldFechaFin.setDate(null);
                    } catch (SQLException ex) {
                        System.out.println("error " + ex);
                    }
                    break;
            }
        }

        if (e.getSource() == mc.btnCancelarCliente) {
            Object[] campos = {mc.txtCelular, mc.txtCiudad, mc.txtCorreo, mc.txtDireccion, mc.txtDocumento, mc.txtFax, mc.txtNombreRazon,
                mc.txtTelefono, mc.txtTelefono2, mc.cboTipoId, mc.chkProveedor, mc.cboNacionalidad, mc.cldNacimiento};
            cleanCampos(campos);
            mc.dispose();

        }

        if (e.getSource() == pr.mnuHospedaje) {
            cargarHospedajes("", "");
        }

        if (e.getSource() == gp.cboTipoPago) {
            String valor = (String) gp.cboTipoPago.getSelectedItem();
            if (valor == null) {
                return;
            }
            if (valor.equals("-- Seleccione --")) {
                return;
            }
            String[] id = valor.split("-");
            if (id[0].trim().equals("1")) {
                gp.lblBanco.setVisible(false);
                gp.txtBancos.setVisible(false);
            }
            if (id[0].trim().equals("5") || id[0].trim().equals("6")) {
                gp.txtCuatroDigitos.setVisible(true);
                gp.txtVoucher.setVisible(true);
                gp.lblCuatroDigitos.setVisible(true);
                gp.lblVoucher.setVisible(true);
                gp.txtBancos.setVisible(true);
                gp.lblBanco.setVisible(true);
            } else {
                gp.txtCuatroDigitos.setVisible(false);
                gp.txtVoucher.setVisible(false);
                gp.lblCuatroDigitos.setVisible(false);
                gp.lblVoucher.setVisible(false);
            }
            if (id[0].trim().equals("4")) {
                gp.txtCheque.setVisible(true);
                gp.lblCheque.setVisible(true);
                gp.lblBanco.setVisible(false);
                gp.txtBancos.setVisible(false);
            } else {
                gp.txtCheque.setVisible(false);
                gp.lblCheque.setVisible(false);
            }
        }

        if (e.getSource() == gp.btnGuardar) {
            Object[] campos = {gp.txtValor, gp.cboTipoPago, gp.taObservaciones};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            if (!validarSoloNumeros(gp.txtValor.getText())) {
                JOptionPane.showMessageDialog(null, "¡Valor no valido!");
                gp.txtValor.setText("");
                return;
            }
            if (!gp.txtCuatroDigitos.getText().equals("")) {
                if (!Utils.Utils.voucherValido(gp.txtCuatroDigitos.getText(), 4)) {
                    JOptionPane.showMessageDialog(null, "¡Cuatro dígitos invalido!");
                    return;
                }
            }
            if (Float.parseFloat(gp.txtValor.getText()) > Float.parseFloat(gp.txtTotalAPagar.getText().replace(".", ""))) {
                JOptionPane.showMessageDialog(null, "¡El valor ingresado no debe ser mayor al valor a pagar!");
            } else {
                if (gp.txtIsHospedaje.getText().equals("")) {
                    addPay(false, 0, 0);
                } else {
                    int idHospedaje1 = Integer.parseInt(gp.txtIsHospedaje.getText());
                    int idCliente1 = Integer.parseInt(gp.txtIsCliente.getText());
                    addPay(true, idCliente1, idHospedaje1);
                }
            }
        }

        if (e.getSource() == gp.btnCancelar) {
            try {
                Object[] campos = {gp.txtBancos, gp.txtCheque, gp.txtValor, gp.txtVoucher, gp.txtCuatroDigitos};
                cleanCampos(campos);
                gp.dispose();
                if (fireReport) {
                    ocultarPaneles("mnuFacturacion");
                    DetalleFacturaDataSource datasource = new DetalleFacturaDataSource();
                    datasource.setListaDetalle(detalleFacturaItems);
                    Utils.Utils.generateReporte(factura, datasource, "FACTURA DE VENTA", "FACTURA DE VENTA");
                }
                fireReport = true;
            } catch (SQLException | JRException ex) {
                System.out.println("error al mostrar la factura " + ex);
            }
        }

        if (e.getSource() == rh.btnBuscarHabitacion) {
            if (!rh.chkAmericana.getState() && !rh.chkChalet.getState()) {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un tipo de habitación!");
                return;
            }
            cargarTblHabitaciones();
        }

        if (e.getSource() == rh.btnRegistrarHuesped) {
            Object[] campos = {rh.txtHuesped, rh.cboMotivo};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            if (rh.tblHabitaciones.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "¡No hay habitación seleccionada!");
                return;
            }
            int adultos = Integer.parseInt(rh.spnAdultos.getValue().toString());
            int ninos = Integer.parseInt(rh.spnNinos.getValue().toString());
            int personas = adultos + ninos;
            try {
                calcularCostoNoche(personas, registro);
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            registrarhuesped();
            pr.cboFiltroHospedaje.setSelectedItem("Sin Facturar");
            cargarHospedajes("", "");
            rh.dispose();
        }

        if (e.getSource() == pr.btnRegistrarHospedaje) {
            try {
                cargarMotivos();
                cargarClientes();
                rh.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.mnuCambiarClave) {
            cc.setVisible(true);
        }

        if (e.getSource() == cc.btnCancelarCambioClave) {
            cc.dispose();
            cc.txtClaveActual.setText("");
            cc.txtNuevaClave.setText("");
            cc.txtNuevaClave2.setText("");
        }

        if (e.getSource() == cc.btnActualizarClave) {
            try {
                Object[] campos = {cc.txtClaveActual, cc.txtNuevaClave, cc.txtNuevaClave2};
                if (validarCampos(campos)) {
                    JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                    return;
                }
                if (!Utils.Utils.validarClave(Principal.idUsuarioLog.getText(), Arrays.toString(cc.txtClaveActual.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "¡La clave actual no coincide!");
                    return;
                }
                char clave1[] = cc.txtNuevaClave.getPassword();
                String clavedef1 = new String(clave1);
                char clave2[] = cc.txtNuevaClave2.getPassword();
                String clavedef2 = new String(clave2);
                if (!clavedef1.equals(clavedef2)) {
                    JOptionPane.showMessageDialog(null, "¡Las claves no son iguales!");
                    cc.txtNuevaClave2.requestFocus();
                    return;
                }
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            actualizarClave();
        }

        if (e.getSource() == pr.mnuNumeracion) {
            try {
                cargarTiposImprimibles();
                mn.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error " + e);
            }
        }

        if (e.getSource() == mn.cmbImprimible) {
            if (mn.cmbImprimible.getSelectedItem() == null) {
                return;
            }
            String dato = (String) mn.cmbImprimible.getSelectedItem();
            String[] idRecuperado = dato.split("-");
            if (idRecuperado[0].equals("1")) {
                try {
                    String resolucionActiva = Utils.Utils.getResolucionActiva(Principal.idVendedor.getText());
                    if (resolucionActiva.equals("")) {
                        JOptionPane.showMessageDialog(null, "¡No hay resoluciones activas!");
                        mn.btnGuardarConsecutivo.setEnabled(false);
                        return;
                    } else {
                        mn.btnGuardarConsecutivo.setEnabled(true);
                    }
                    String[] datosResolucion = resolucionActiva.split(",");
                    mn.txtPrefijo.setEditable(false);
                    mn.txtPrefijo.setText(datosResolucion[1]);
                    mn.spnConsecutivo.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(datosResolucion[2]), Integer.valueOf(datosResolucion[2]), Integer.valueOf(datosResolucion[3]), Integer.valueOf(1)));
                } catch (SQLException ez) {
                    System.out.println("error " + ez);
                }
            } else {
                mn.txtPrefijo.setText("");
                mn.txtPrefijo.setEditable(true);
                mn.spnConsecutivo.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(99999999), Integer.valueOf(1)));
            }
        }

        if (e.getSource() == pr.mnuResolucion) {
            try {
                if (Utils.Utils.getExisteResolucion(Principal.idVendedor.getText())) {
                    int response = JOptionPane.showConfirmDialog(null, "¡Ya existe una resolución activa!\n¿Desea ingresar una nueva resolución?", "¡Aviso!",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        cargarTiposResolucion();
                        rf.setVisible(true);
                    }
                } else {
                    cargarTiposResolucion();
                    rf.setVisible(true);
                }
            } catch (SQLException ex) {
                System.out.println("Error consultando " + ex);
            }
        }

        if (e.getSource() == pr.mnuVincularUsuarios) {
            try {
                cargarRoles();
                cargarTiposId2();
                vu.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("Error consultando " + ex);
            }
        }

        if (e.getSource() == mn.btnGuardarConsecutivo) {
            Object[] campos = {mn.cmbImprimible, mn.spnConsecutivo};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            guardarConsecutivo();
        }

        if (e.getSource() == mn.btnCancelarImprimible) {
            mn.dispose();
        }

        if (e.getSource() == vu.btnGuardarUsuario) {
            Object[] campos = {vu.txtDocumento, vu.txtNombres, vu.txtApellidos, vu.cmbTipoDocumento, vu.cmbRol, vu.txtClave1, vu.txtClave2};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            char clave1[] = vu.txtClave1.getPassword();
            String clavedef1 = new String(clave1);
            char clave2[] = vu.txtClave2.getPassword();
            String clavedef2 = new String(clave2);
            if (!clavedef1.equals(clavedef2)) {
                JOptionPane.showMessageDialog(null, "¡Las claves no son iguales!");
                vu.txtClave2.requestFocus();
                return;
            }
            guardarVinculacionUsuario();
        }

        if (e.getSource() == rf.btnGuardarResolucion) {
            Object[] campos = {rf.txtResolucion, rf.txtInicial, rf.txtFinal, rf.cldFechaResolucion, rf.cboTipoResolucion};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            if (Integer.parseInt(rf.txtInicial.getText()) > Integer.parseInt(rf.txtFinal.getText())) {
                JOptionPane.showMessageDialog(null, "¡Consecutivo Inicial no puede ser mayor al consecutivo final!");
                rf.txtInicial.requestFocus();
                return;
            }
            guardarResolucion();
        }

        if (e.getSource() == pr.btnFindCliente || e.getSource() == rh.btnBuscarCliente || e.getSource() == pr.btnNewContacto) {
            getModalClienteController.ClienteController();
            mc.cboTipoId.setEnabled(true);
            mc.txtDocumento.setEditable(true);
            mc.cboNacionalidad.setEnabled(true);
            mc.txtNombreRazon.setEditable(true);
            mc.txtDireccion.setEditable(true);
            mc.txtTelefono.setEditable(true);
            mc.txtTelefono2.setEditable(true);
            mc.txtCorreo.setEditable(true);
            mc.txtFax.setEditable(true);
            mc.cldNacimiento.setEnabled(true);
            mc.txtCelular.setEditable(true);
            mc.txtCiudad.setEnabled(true);
            mc.chkCliente.setEnabled(true);
            mc.chkProveedor.setEnabled(true);
            mc.txAreaObs.setEditable(true);
            mc.btnGuardarComprador.setVisible(true);
            mc.chkCliente.setSelected(true);
            Object[] campos = {mc.txtCelular, mc.txtCiudad, mc.txtCorreo, mc.txtDireccion, mc.txtDocumento, mc.txtFax, mc.txtNombreRazon,
                mc.txtTelefono, mc.txtTelefono2, mc.cboTipoId, mc.chkProveedor, mc.cboNacionalidad, mc.cldNacimiento};
            cleanCampos(campos);
            mc.setVisible(true);
        }

        if (e.getSource() == pr.mnuGenerarFactura) {
            isHospedaje = false;
            id_hospedajeU = 0;
            pr.cboTipoFiltro.removeAllItems();
            pr.cboTipoFiltro.addItem("-- Seleccione --");
            pr.cboTipoFiltro.addItem("Estado Factura");
            pr.cboTipoFiltro.addItem("Rango De Fechas");
            pr.cboTipoFiltro.addItem("Id Factura");
            pr.cboTipoFiltro.addItem("Id Cliente");
            pr.cboTipoFiltro.setSelectedItem("-- Seleccione --");
            pr.btnNewFactura.setText("Nueva Factura");
            optNewFactura = "factura";
            pr.pbtnPagosRealizados.setVisible(true);
            pr.pbtnGeneratePago.setVisible(true);
            pr.pbtnAnulateFactura.setVisible(true);
            detalleFacturaItems.clear();
            limpiarFactura();
            try {
                cargarTablaFactura("", true);
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            if (!newFactura("factura")) {
                ocultarPaneles("mnuFacturacion");
            }
        }

        if (e.getSource() == pr.mnuCotizaciones) {
            isHospedaje = false;
            id_hospedajeU = 0;
            pr.cboTipoFiltro.removeAllItems();
            pr.cboTipoFiltro.addItem("-- Seleccione --");
            pr.cboTipoFiltro.addItem("Rango De Fechas");
            pr.cboTipoFiltro.addItem("Id Cliente");
            pr.cboTipoFiltro.setSelectedItem("-- Seleccione --");
            pr.btnNewFactura.setText("Nueva Cotización");
            pr.pbtnPagosRealizados.setVisible(false);
            pr.pbtnGeneratePago.setVisible(false);
            pr.pbtnAnulateFactura.setVisible(false);
            optNewFactura = "cotizaciones";
            detalleFacturaItems.clear();
            limpiarFactura();
            try {
                cargarTablaFactura("", false);
            } catch (SQLException ex) {
                System.out.println("error  " + ex);
            }
            if (!newFactura("cotizaciones")) {
                ocultarPaneles("mnuFacturacion");
            }
        }

        if (e.getSource() == pr.btnNewFactura) {
            if (pr.btnNewFactura.getText().equals("Nueva Factura")) {
                pr.btnSaveAddPay.setEnabled(true);
                pr.btnSaveAndNew.setEnabled(true);
                pr.btnOlySave.setEnabled(true);
            }
            detalleFacturaItems.clear();
            limpiarFactura();
            if (!newFactura(optNewFactura)) {
                ocultarPaneles("btnNewFactura");
            }
        }

        if (e.getSource() == SelectTipoHospedaje.cboTiposHospedajes) {
            pr.txtNota.setText("");
            String option = (String) SelectTipoHospedaje.cboTiposHospedajes.getSelectedItem();
            if (option == null) {
                return;
            }
            String[] optionSelected = option.split(" - ");
            if (option.equals("-- Seleccione --")) {
                SelectTipoHospedaje.spcantS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
                SelectTipoHospedaje.lblTotal.setText("0");
                return;
            }
            try {
                SelectTipoHospedaje.spcantS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
                SelectTipoHospedaje.lblTotal.setText("0");
                tiposhabitacion.clear();
                tiposhabitacion = Utils.Utils.getDatosHabitacion();
                datosTipoHabitacion = tiposhabitacion.get(Integer.parseInt(optionSelected[0].trim()) - 1).split(",");
//                nomProducto = optionSelected[1].trim();
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == SelectTipoHospedaje.btnAceptHabitacion) {
            String option = (String) SelectTipoHospedaje.cboTiposHospedajes.getSelectedItem();
            if (option.equals("-- Seleccione --")) {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un tipo de habitación!");
                SelectTipoHospedaje.cboTiposHospedajes.requestFocus();
                return;
            }
            float cantidad = Float.parseFloat(SelectTipoHospedaje.spcantS.getValue().toString());
            if (cantidad == 0.0) {
                JOptionPane.showMessageDialog(null, "¡No se permite cantidad 0!");
                SelectTipoHospedaje.spcantS.requestFocus();
                return;
            }
            int costoPesronas = Integer.parseInt(SelectTipoHospedaje.lblTotal.getText().replace(".", ""));
            String valc = formateador.format((float) costoPesronas);
            pr.txtCostoServicio.setText(valc);
            pr.txtCostoServicio.requestFocus();
            String[] optionSelected = option.split(" - ");
            notaHospedaje.add(optionSelected[1] + " Total Personas: " + (int) cantidad + " | ");
            SelectTipoHospedaje.spcantS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
            SelectTipoHospedaje.lblTotal.setText("0");
            pr.spCantidad2.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
            pr.spCantidad2.setEnabled(true);
            valorProd = costoPesronas;
            st.dispose();
        }

        if (e.getSource() == SelectTipoHospedaje.btnCancelHabitacion) {
            SelectTipoHospedaje.spcantS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
            SelectTipoHospedaje.lblTotal.setText("0");
            pr.txtNota.setText("");
            st.dispose();
        }

        if (e.getSource() == vu.btnCancelarVinculacion) {
            vu.txtDocumento.setText("");
            vu.txtNombres.setText("");
            vu.txtApellidos.setText("");
            vu.txtClave1.setText("");
            vu.txtClave2.setText("");
            String option = (String) vu.cmbRol.getSelectedItem();
            String[] optionSelected = option.split("-");
            String option2 = (String) vu.cmbTipoDocumento.getSelectedItem();
            String[] optionSelected2 = option2.split("-");
            vu.dispose();
        }

        if (e.getSource() == rf.btnCancelarResolucion) {
            rf.txtResolucion.setText("");
            rf.txtInicial.setText("");
            rf.txtFinal.setText("");
            rf.txtPrefijo.setText("");
            String option = (String) rf.cboTipoResolucion.getSelectedItem();
            String[] optionSelected = option.split("-");
            rf.dispose();
        }

        if (e.getSource() == pr.cboProductoServ) {
            String prodServ = (String) pr.cboProductoServ.getSelectedItem();
            if (prodServ.equals("-- Seleccione --") || prodServ == null) {
                return;
            }
            String[] parts = prodServ.split("-");
            String[] datos = null;
            try {
                datos = Utils.Utils.isProducto(Integer.parseInt(parts[0].trim())).split(",");
                nomProducto = datos[0];
                valorProd = Float.parseFloat(datos[1]);
                idProductoservicio = Integer.parseInt(datos[4]);
                idUnidaMedida = Utils.Utils.getUnidadMedida(Integer.parseInt(datos[5]));
            } catch (SQLException ex) {
                System.out.println("error" + ex);
            }
            if (prodServ.contains("Hospedaje")) {
                pr.txtCostoServicio.setEditable(true);
                pr.txtCostoServicio.setValue(null);
                pr.cboImpuesto.setSelectedItem("-- Seleccione --");
                pr.txtDescuento.setValue(null);
                pr.spCantidad2.setEnabled(false);
                pr.spCantidad2.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.0f)));
                try {
                    tiposhabitacion.clear();
                    tiposhabitacion = Utils.Utils.getDatosHabitacion();
                    SelectTipoHospedaje.cboTiposHospedajes.removeAllItems();
                    SelectTipoHospedaje.cboTiposHospedajes.addItem("-- Seleccione --");
                    tiposhabitacion.stream().forEach((String string) -> {
                        String[] partes = string.split(",");
                        SelectTipoHospedaje.cboTiposHospedajes.addItem(partes[0] + " - " + partes[1]);
                    });
                    st.setLocationRelativeTo(null);
                    st.setVisible(true);
                } catch (SQLException ex) {
                    System.out.println("error" + ex);
                }

            } else if (Integer.parseInt(datos[3]) == 0) {
                pr.txtCostoServicio.setEditable(true);
                pr.txtCostoServicio.setValue(null);
                pr.cboImpuesto.setSelectedItem("-- Seleccione --");
                pr.txtDescuento.setValue(null);
                pr.spCantidad2.setEnabled(false);
                pr.spCantidad2.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.0f)));
            } else {
                v = formateador.format(valorProd);
                pr.txtCostoServicio.setEditable(false);
                pr.txtCostoServicio.setText(v);
                pr.cboImpuesto.setSelectedItem("-- Seleccione --");
                pr.txtDescuento.setValue(null);
                pr.txtCostoServicio.requestFocus();
                pr.spCantidad2.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(Float.parseFloat(datos[2])), Float.valueOf(1.0f)));
                pr.spCantidad2.setEnabled(true);

            }
        }

        if (e.getSource() == pr.cboTerminoDePago) {
            String termino = (String) pr.cboTerminoDePago.getSelectedItem();
            if (termino == null) {
                return;
            }
            String[] partes = termino.split("-");
            if (termino.equals("-- Seleccione --") || termino == null) {
                if (pr.clVencimientomanual.isVisible()) {
                    pr.clVencimientomanual.setVisible(false);
                }
                return;
            } else if (partes[0].trim().equals("1")) {
                pr.clVencimientomanual.setVisible(true);
            } else {
                pr.clVencimientomanual.setVisible(false);
            }
        }

        if (e.getSource() == pr.btnAddDetalle) {
            Object[] campos = {pr.cboProductoServ, pr.txtCostoServicio, pr.cboImpuesto, pr.spCantidad2, pr.cboImpuesto};
            if (validarCampos(campos)) {
                JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
                return;
            }
            float valor = Float.parseFloat(pr.txtCostoServicio.getValue().toString());
            float cantidad = Float.parseFloat(pr.spCantidad2.getValue().toString());
            String impuestoitem = (String) pr.cboImpuesto.getSelectedItem();
            String[] parts = impuestoitem.split("-");
            float descuento = 0;
            if (pr.txtDescuento.getValue() != null) {
                descuento = Float.parseFloat(pr.txtDescuento.getValue().toString());
            }
            if (descuento > (valor / 2)) {
                JOptionPane.showMessageDialog(null, "Máximo descuento permitido: 50%, es decir " + formateador.format((valor / 2)));
                return;
            }
            try {
                addDetalleFactura(idProductoservicio,
                        cantidad,
                        ConsecutivoFactura,
                        Utils.Utils.getImpuesto(Integer.parseInt(parts[0].trim()), valor, descuento),
                        (valorProd == 0) ? valor : valorProd,
                        nomProducto,
                        descuento,
                        ((valor - descuento)),
                        Integer.parseInt(parts[0].trim()), idUnidaMedida);
                cleanDetalle();
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.btnPreview) {
            try {
                getDatosFactura(4, "btnPreview");
                // 4 borrador
            } catch (SQLException | JRException ex) {//
                System.out.println("error btnPreview" + ex);
            }
        }

        if (e.getSource() == pr.btnSaveAddPay) {
            try {
                getDatosFactura(1, "btnSaveAddPay");
            } catch (SQLException | JRException ex) {
                System.out.println("error btnSaveAddPay" + ex);
            }
        }
        if (e.getSource() == pr.btnSaveAndNew) {
            try {
                getDatosFactura(1, "btnSaveAndNew");
            } catch (SQLException | JRException ex) {
                System.out.println("error btnSaveAndNew" + ex);
            }
        }
        if (e.getSource() == pr.btnOlySave) {
            try {
                getDatosFactura(1, "btnOlySave");
            } catch (SQLException | JRException ex) {
                System.out.println("error btnOlySave" + ex);
            }
        }

        if (e.getSource() == pr.btnCotizar) {
            try {
                getDatosFactura(6, "btnCotizar");
            } catch (SQLException | JRException ex) {
                System.out.println("error btnSaveAddPay" + ex);
            }
        }

        if (e.getSource() == pr.btnFindBill) {
            try {
                String sql = "";
                String filtro = (String) pr.cboTipoFiltro.getSelectedItem();
                if (filtro.equals("-- Seleccione --")) {
                    JOptionPane.showMessageDialog(null, "¡Seleccione un tipo de búsqueda!");
                    pr.cboTipoFiltro.requestFocus();
                    return;
                }
                switch (filtro) {
                    case "Estado Factura":
                        String estadoFacturaFind = (String) pr.cboEstadoFacturaBuscar.getSelectedItem();
                        if (!estadoFacturaFind.equals("-- Seleccione --")) {
                            sql = ", estados_facturas e where f.id_empresa = '" + Principal.idVendedor.getText() + "' and e.nombre = '" + estadoFacturaFind
                                    + "' and e.id_estado_factura = f.id_estado_factura";
                        }
                        break;
                    case "Rango De Fechas":
                        if (pr.cldFechaIni.getDate() != null && pr.cldFechaFin.getDate() != null) {
                            sql = ", estados_facturas e where f.id_empresa = '" + Principal.idVendedor.getText() + "'"
                                    + " and e.id_estado_factura = f.id_estado_factura"
                                    + " and f.fecha between '" + df.format(pr.cldFechaIni.getDate()) + " 00:00:00' and '" + df.format(pr.cldFechaFin.getDate()) + " 23:59:59'";
                        }
                        break;
                    case "Id Factura":
                        String idFactura = pr.txtIdFactura.getText();
                        if (!idFactura.equals("")) {
                            sql = ", estados_facturas e where f.id_empresa = '" + Principal.idVendedor.getText() + "'"
                                    + " and e.id_estado_factura = f.id_estado_factura"
                                    + " and f.id_factura = '" + idFactura + "'";
                        }
                        break;
                    case "Id Cliente":
                        String idCliente = pr.txtIdFactura.getText();
                        if (!idCliente.equals("")) {
                            sql = " inner join estados_facturas e on e.id_estado_factura = f.id_estado_factura "
                                    + "inner join clientes c on c.id_cliente = f.id_cliente AND c.identificacion = '" + idCliente + "'"
                                    + " where f.id_empresa = '" + Principal.idVendedor.getText() + "'";
                        }
                        break;
                }
                if (sql.equals("")) {
                    JOptionPane.showMessageDialog(null, "¡No has ingresado un parámetro de búsqueda!");
                    return;
                }
                if (pr.btnNewFactura.getText().equals("Nueva Factura")) {
                    cargarTablaFactura(sql, true);
                } else {
                    cargarTablaFactura(sql, false);
                }
            } catch (SQLException ex) {
                System.out.println("error btnSaveAddPay" + ex);
            }
        }

        if (e.getSource() == pr.pbtnGenerateCopia) {
            try {
                int fila = pr.tbFacturas.getSelectedRow();
                if (fila >= 0) {
                    String idFacturaToFilter = pr.tbFacturas.getValueAt(fila, 0).toString();
                    DetalleFacturaDataSource datasource = new DetalleFacturaDataSource();
                    detallefacturaCotDao = new DetalleFacturaCotizacionDAO();
                    String opciones[] = {"Seleccione", "COPIA", "COPIA ARCHIVO", "COPIA CONTABILIDAD"};
                    String valor = (String) JOptionPane.showInputDialog(null, "Generar Copia", "Seleccione una opción", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                    if (valor == null) {
                        return;
                    }
                    if (!valor.equals("Seleccione")) {
                        if (pr.tbFacturas.getValueAt(fila, 4).toString().equals("Anulada")) {
                            if (!valor.equals("COPIA")) {
                                valor += " " + pr.tbFacturas.getValueAt(fila, 4).toString().toUpperCase();
                            } else {
                                valor = pr.tbFacturas.getValueAt(fila, 4).toString().toUpperCase();
                            }
                        }
                        String opt = "COPIA";
                        if (!optNewFactura.equals("factura")) {
                            opt = "COTIZACIÓN";
                        }
                        datasource.setListaDetalle(detallefacturaCotDao.getDetallesFacturaCotizacionListByIdFactura(idFacturaToFilter));
                        Utils.Utils.generateReporte(Utils.Utils.getFacturaById(idFacturaToFilter), datasource, opt, valor);
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Seleccione una opción!");
                        pr.pbtnGenerateCopia.doClick();
                    }
                    detallefacturaCotDao = null;
//                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
                }

            } catch (SQLException | JRException ex) {
                System.out.println("error linea 867 = " + ex);
            }
        }

        if (e.getSource() == pr.pbtnGeneratePago) {
            Object[] numeros = {gp.txtValor, gp.txtCuatroDigitos};
            SLetra(numeros, false);
            int fila = pr.tbFacturas.getSelectedRow();
            if (fila >= 0) {

                int restSaldo = (Integer.parseInt(pr.tbFacturas.getValueAt(fila, 1).toString().replace(".", "")) - Utils.Utils.getSaldoByFatura(pr.tbFacturas.getValueAt(fila, 0).toString()));
                System.out.println("saldo = " + restSaldo);
                if (pr.tbFacturas.getValueAt(fila, 4).toString().equals("Cotización")) {
                    JOptionPane.showMessageDialog(null, "¡No puedes hacer pagos a una cotización!");
                } else if (pr.tbFacturas.getValueAt(fila, 4).toString().equals("Anulada")) {
                    JOptionPane.showMessageDialog(null, "¡Factura anulada..!");
                } else if (restSaldo == 0) {
                    JOptionPane.showMessageDialog(null, "¡Factura no presenta saldo!");
                } else {
                    try {
                        fireReport = false;
                        String idFacturaToFilter = pr.tbFacturas.getValueAt(fila, 0).toString();
                        factura = Utils.Utils.getFacturaById(idFacturaToFilter);
                        String saldo = Integer.toString(restSaldo);
                        gp.txtTotalAPagar.setText(saldo);
                        gp.setLocationRelativeTo(null);
                        gp.setVisible(true);
                    } catch (SQLException ex) {
                        System.out.println("error linea 2136 = " + ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

        if (e.getSource() == pr.pbtnAnulateFactura) {
            int fila = pr.tbFacturas.getSelectedRow();
            if (fila >= 0) {
                af.idfactura.setText(pr.tbFacturas.getValueAt(fila, 0).toString());
                af.setLocationRelativeTo(null);
                af.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

        if (e.getSource() == af.btnGuardarAnulacion) {
            if (af.taConcepto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
                af.taConcepto.requestFocus();
            } else {
                try {
                    anularFactura(af.idfactura.getText(), af.taConcepto.getText());
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }

        }

        if (e.getSource() == af.btnCancelarAnulacion) {
            af.idfactura.setText("");
            af.taConcepto.setText("");
            af.dispose();
        }

        if (e.getSource() == pr.pbtnEditCLiente) {
            try {
                cargarClienteEdit(false);
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.pbtnInfo) {
            try {
                cargarClienteEdit(true);
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        }

    }

    // fin action performed
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == pr.tblDetallefactura) {
            int columna = pr.tblDetallefactura.getSelectedColumn();
            int fila = pr.tblDetallefactura.getSelectedRow();
            int item = Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 6).toString());
            char tecla = e.getKeyChar();
            if (tecla == KeyEvent.VK_ENTER) {
                if (columna == 0) {
                    if (validarSoloNumeros(pr.tblDetallefactura.getValueAt(fila, 0).toString())) {
                        int newCant = Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 0).toString());
                        pr.tblDetallefactura.setValueAt(newCant, fila, columna);
                        try {
                            cargarDetallaFatutra(item, newCant, false);
                        } catch (SQLException ex) {
                            System.out.println("error line 1037" + ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Solo se permiten números!");
                        try {
                            cargarDetallaFatutra(-1, 0, false);
                        } catch (SQLException ex) {
                            System.out.println("error line 1996" + ex);
                        }
                    }
                }
                if (columna == 3) {
                    if (validarSoloNumeros(pr.tblDetallefactura.getValueAt(fila, 3).toString())) {
                        float subtotalB = (Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 4).toString().replace(".", "")));
                        float permitido = subtotalB * ((float) 50 / (float) 100);
                        if (Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 3).toString()) <= permitido) {
                            int newCant = Integer.parseInt(pr.tblDetallefactura.getValueAt(fila, 3).toString());
                            pr.tblDetallefactura.setValueAt(newCant, fila, columna);
                            try {
                                cargarDetallaFatutra(item, newCant, true);
                                if (isHospedaje) {
                                    float abonosC = Utils.Utils.getAbonos("", id_hospedajeU);
                                    float totalC = Float.parseFloat(pr.lbltotalFactura.getText().replace(".", ""));
                                    float saldoC = totalC - abonosC;
                                    if (saldoC < 0) {
                                        JOptionPane.showMessageDialog(null, "¡Descuento no permitido! No se permitirá facturar, error en el nuevo saldo");
                                        pr.btnSaveAddPay.setEnabled(false);
                                        pr.btnSaveAndNew.setEnabled(false);
                                        pr.btnOlySave.setEnabled(false);
                                        pr.btnRetencion.setEnabled(false);
                                    } else {
                                        pr.btnSaveAddPay.setEnabled(true);
                                        pr.btnSaveAndNew.setEnabled(true);
                                        pr.btnOlySave.setEnabled(true);
                                        pr.btnRetencion.setEnabled(true);
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("error line 1037" + ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Descuento no permitido!");
                            try {
                                cargarDetallaFatutra(-1, 0, false);
                            } catch (SQLException ex) {
                                System.out.println("error line 2056" + ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Solo se permiten números!");
                        try {
                            cargarDetallaFatutra(-1, 0, false);
                        } catch (SQLException ex) {
                            System.out.println("error line 2014" + ex);
                        }
                    }
                }
            }
        }
    }

    private void cargarTiposIdMC() throws SQLException {
        mc.cboTipoId.removeAllItems();
        vu.cmbTipoDocumento.removeAllItems();
        tipoDao = new TiposDeIdentificacionDAO();
        t = tipoDao.getTiposDeIdentificacionList().iterator();
        mc.cboTipoId.addItem("-- Seleccione --");
        vu.cmbTipoDocumento.addItem("-- Seleccione --");
        while (t.hasNext()) {
            TiposDeIdentificacion tipoId = t.next();
            mc.cboTipoId.addItem(tipoId.getIdTipoIdentificacion() + "-" + tipoId.getNombre());
            vu.cmbTipoDocumento.addItem(tipoId.getIdTipoIdentificacion() + "-" + tipoId.getNombre());
        }
        tipoDao = null;
        t = null;
    }

    private void cargarTiposId2() throws SQLException {
        vu.cmbTipoDocumento.removeAllItems();
        tipoDao = new TiposDeIdentificacionDAO();
        Iterator<TiposDeIdentificacion> t = tipoDao.getTiposDeIdentificacionList().iterator();
        vu.cmbTipoDocumento.addItem("-- Seleccione --");
        while (t.hasNext()) {
            TiposDeIdentificacion tipoId = t.next();
            vu.cmbTipoDocumento.addItem(tipoId.getIdTipoIdentificacion() + "-" + tipoId.getNombre());
        }
        tipoDao = null;
    }

    private void cargarTiposResolucion() throws SQLException {
        rf.cboTipoResolucion.removeAllItems();
        tiposResolucionDao = new TiposResolucionesDAO();
        Iterator<TiposResoluciones> t = tiposResolucionDao.getTiposResolucionesList().iterator();
        rf.cboTipoResolucion.addItem("-- Seleccione --");
        while (t.hasNext()) {
            TiposResoluciones tipoRe = t.next();
            rf.cboTipoResolucion.addItem(tipoRe.getIdTipoResolucion() + "-" + tipoRe.getNombre());
        }
        tiposResolucionDao = null;
    }

    private void cargarRoles() throws SQLException {
        vu.cmbRol.removeAllItems();
        rolesDao = new RolesDAO();
        Iterator<Roles> r = rolesDao.getRolesList().iterator();
        vu.cmbRol.addItem("-- Seleccione --");
        while (r.hasNext()) {
            Roles roles = r.next();
            vu.cmbRol.addItem(roles.getIdRol() + "-" + roles.getNombre());
        }
        tiposResolucionDao = null;
    }

    private void cargarTiposImprimibles() throws SQLException {
        mn.cmbImprimible.removeAllItems();
        imprimiblesDao = new TiposImprimiblesDAO();
        Iterator<TiposImprimibles> ti = imprimiblesDao.getTipoImprimibleList().iterator();
        mn.cmbImprimible.addItem("-- Seleccione --");
        while (ti.hasNext()) {
            TiposImprimibles formatos = ti.next();
            mn.cmbImprimible.addItem(formatos.getIdTipoImprimible() + "-" + formatos.getNombre());
        }
        tiposResolucionDao = null;
    }

    private void cargarTipoHabitaciones() throws SQLException {

        th.cboTipoHospedaje.removeAllItems();
        tipoHabitacionDao = new TiposDeHabitacionDAO();
        tiposList.clear();
        tiposList = (ArrayList<TiposDeHabitacion>) tipoHabitacionDao.getTiposDeHabitacionList(Principal.idVendedor.getText());
        Iterator<TiposDeHabitacion> tdh = tiposList.iterator();
        th.cboTipoHospedaje.addItem("-- Seleccione --");
        while (tdh.hasNext()) {
            TiposDeHabitacion tipoHabitacion = tdh.next();
            th.cboTipoHospedaje.addItem(tipoHabitacion.getIdTipoHabitacion() + "-" + tipoHabitacion.getNombre());
        }
        tipoHabitacionDao = null;
    }

    public void cargarCiudades() throws SQLException {
        ciudadDao = new CiudadesDAO();
        mc.txtCiudad.removeAllItems();
        mc.txtCiudad.addItem("-- Seleccione --");
        Iterator<Ciudades> c = ciudadDao.getCiudadesList().iterator();
        while (c.hasNext()) {
            Ciudades ciu = c.next();
            mc.txtCiudad.addItem(ciu.getNombre());
        }
        AutoCompleteDecorator.decorate(mc.txtCiudad);
    }

    public void cargarHabitacionesU() throws SQLException {
        habitacionDao = new HabitacionesDAO();
        ah.cboHabitacionesDisponibles.removeAllItems();
        ah.cboHabitacionesDisponibles.addItem("-- Seleccione --");
        Iterator<Habitaciones> h = Utils.Utils.getHabitacionesDisponibles(true, true, 1).iterator();
        while (h.hasNext()) {
            Habitaciones habitacion = h.next();
            ah.cboHabitacionesDisponibles.addItem(habitacion.getNombre() + " - " + habitacion.getIdHabitacion());
        }
        AutoCompleteDecorator.decorate(ah.cboHabitacionesDisponibles);
    }

    //cargar bancos
    public void cargarFranquicias() throws SQLException {
        franquiciaDao = new FranquiciasDAO();
        gp.txtBancos.removeAllItems();
        gp.txtBancos.addItem("-- Seleccione --");
        Iterator<Franquicias> f = franquiciaDao.getFranquiciasList().iterator();
        while (f.hasNext()) {
            Franquicias franquicia = f.next();
            gp.txtBancos.addItem(franquicia.getIdFranquicia() + " " + franquicia.getNombre());
        }
        AutoCompleteDecorator.decorate(gp.txtBancos);
    }

    // para cargar el auto completar de lblCliente.
    public void cargarClientes() throws SQLException {
        clientedao = new ClientesDAO();
        Principal.lblCliente.removeAllItems();
        Principal.lblCliente.addItem("-- Seleccione --");
        rh.txtHuesped.removeAllItems();
        rh.txtHuesped.addItem("-- Seleccione --");//       
        Iterator<Clientes> c = clientedao.getCompradorByVendedor(Principal.idVendedor.getText()).iterator();
        while (c.hasNext()) {
            Clientes com = c.next();
            Principal.lblCliente.addItem(com.getIdentificacion() + " " + com.getNombreRazon());
            rh.txtHuesped.addItem(com.getIdentificacion() + " " + com.getNombreRazon());
        }
        AutoCompleteDecorator.decorate(Principal.lblCliente);
        AutoCompleteDecorator.decorate(rh.txtHuesped);
    }

    //public void cargarClientes() throws SQLException {
    public void cargarTipoRetencion() throws SQLException {
        retencionesDao = new RetencionesDAO();
        gr.cboTipoRetencion.removeAllItems();
        gr.cboTipoRetencion.addItem("-- Seleccione --");
        Iterator<Retenciones> c = retencionesDao.getRetencionesList(Principal.idVendedor.getText()).iterator();
        while (c.hasNext()) {
            Retenciones com = c.next();
            gr.cboTipoRetencion.addItem(com.getIdRetencion() + " " + com.getNombre());
        }
        AutoCompleteDecorator.decorate(gr.cboTipoRetencion);
        retencionesDao = null;
    }

    //Metodo que carga los tipos de pagos en el combo cboTipoPago
    private void cargarTiposPago() throws SQLException {
        gp.cboTipoPago.removeAllItems();
        tipoPagodao = new TiposDePagosDAO();
        Iterator<TiposDePagos> t = tipoPagodao.getTiposDePagoList().iterator();
        gp.cboTipoPago.addItem("-- Seleccione --");
        while (t.hasNext()) {
            TiposDePagos tipoId = t.next();
            gp.cboTipoPago.addItem(tipoId.getIdTipoPago() + " - " + tipoId.getNombre());
        }
    }

    //Metodo que carga los terminos de pago
    private void cargarTerminosPago() throws SQLException {
        pr.cboTerminoDePago.removeAllItems();
        terminosDao = new TerminosDePagoDAO();
        Iterator<TerminosDePago> t = terminosDao.getTerminoPagoList().iterator();
        pr.cboTerminoDePago.addItem("-- Seleccione --");
        while (t.hasNext()) {
            TerminosDePago tipoId = t.next();
            pr.cboTerminoDePago.addItem(tipoId.getIdTerminoPago() + " - " + tipoId.getNombre());
        }
    }

    //cargarProductosServ
    private void cargarProductosServ() throws SQLException {
        pr.cboProductoServ.removeAllItems();
        productosSerDao = new ProductosServiciosDAO();
        Iterator<ProductosServicios> t = productosSerDao.getProductosServiciosList(Principal.idVendedor.getText()).iterator();
        pr.cboProductoServ.addItem("-- Seleccione --");
        while (t.hasNext()) {
            ProductosServicios tipoId = t.next();
            pr.cboProductoServ.addItem(tipoId.getIdProductoServicio() + " - " + tipoId.getNombre());
        }
//        AutoCompleteDecorator.decorate(pr.cboProductoServ);
    }

    //cargarProductosServ
    private void cargarImpuestos() throws SQLException {
        pr.cboImpuesto.removeAllItems();
        impuestoDAO = new ImpuestosDAO();
        Iterator<Impuestos> t = impuestoDAO.getImpuestosList().iterator();
        pr.cboImpuesto.addItem("-- Seleccione --");
        while (t.hasNext()) {
            Impuestos tipoId = t.next();
            pr.cboImpuesto.addItem(tipoId.getIdImpuesto() + " -  " + tipoId.getDetalle());
        }
    }

    public void ocultarPaneles(String boton) {
        pr.pnHospedaje.setVisible(false);
        pr.pnListFacturas.setVisible(false);
        pr.pnInicio.setVisible(false);
        pr.pnFacturas.setVisible(false);
        pr.pnListClientes.setVisible(false);
        switch (boton) {
            case "btnNewFactura":
                pr.pnHospedaje.setVisible(false);
                pr.pnListFacturas.setVisible(false);
                pr.pnInicio.setVisible(false);
                pr.pnListClientes.setVisible(false);
                pr.pnFacturas.setVisible(true);
                break;
            case "mnuHospedaje":
                pr.pnListFacturas.setVisible(false);
                pr.pnInicio.setVisible(false);
                pr.pnFacturas.setVisible(false);
                pr.pnListClientes.setVisible(false);
                pr.pnHospedaje.setVisible(true);
                break;
            case "mnuFacturacion":
                pr.pnHospedaje.setVisible(false);
                pr.pnInicio.setVisible(false);
                pr.pnFacturas.setVisible(false);
                pr.pnListClientes.setVisible(false);
                pr.pnListFacturas.setVisible(true);
                break;
            case "inicio":
                pr.pnHospedaje.setVisible(false);
                pr.pnListFacturas.setVisible(false);
                pr.pnFacturas.setVisible(false);
                pr.pnListClientes.setVisible(false);
                pr.pnInicio.setVisible(true);
                break;
            case "mnuTodos":
                pr.pnHospedaje.setVisible(false);
                pr.pnListFacturas.setVisible(false);
                pr.pnFacturas.setVisible(false);
                pr.pnInicio.setVisible(false);
                pr.pnListClientes.setVisible(true);
                break;
        }
    }

    public void addDetalleFactura(int idProductoServicio, float cantidad, String idFactura, float Impuesto, float valor, String nomprod, float descuento, float subtotal, int idImpuesto, String unidaMedida) throws SQLException {
//        detalleFacturaItems.clear();
        DetalleFacturaCotizacion d = new DetalleFacturaCotizacion(0, idProductoServicio, cantidad, idFactura, Impuesto, valor, nomprod, descuento, subtotal, idImpuesto, unidaMedida);
        detalleFacturaItems.add(d);
        d = null;
        cargarDetallaFatutra(-1, 0, false);
    }

    public void cargarDetallaFatutra(int itemUpdate, float newCant, boolean descuento) throws SQLException {
        String Titulos[] = {"Cantidad", "Item", "Venta Unidad", "Descuento", "Subtotal", "Impuesto", "keyItem"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { //para evitar que las celdas sean editables
                if (column == 0 || column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        if (itemUpdate >= 0) {
            if (!descuento) {
                DetalleFacturaCotizacion d = detalleFacturaItems.get(itemUpdate);
                detalleFacturaItems.remove(itemUpdate);
                d.setCantidad(newCant);
                float newSubtotal = (d.getCantidad() * d.getValor()) - d.getDescuento();
                d.setSubtotal(newSubtotal);
                d.getImpuesto().setImpuesto2(Utils.Utils.getImpuesto(d.getId_Impuesto(), (d.getCantidad() * d.getValor()), d.getDescuento()));
                detalleFacturaItems.add(itemUpdate, d);
            } else {
                DetalleFacturaCotizacion d = detalleFacturaItems.get(itemUpdate);
                detalleFacturaItems.remove(itemUpdate);
                d.setDescuento(newCant);
                float newSubtotal = (d.getCantidad() * d.getValor()) - d.getDescuento();
                d.setSubtotal(newSubtotal);
                d.getImpuesto().setImpuesto2(Utils.Utils.getImpuesto(d.getId_Impuesto(), (d.getCantidad() * d.getValor()), d.getDescuento()));
                detalleFacturaItems.add(itemUpdate, d);
            }
        }
        int keyItem = 0;
        float valorTotal = 0;
        float descuTotal = 0;
        float impuestoTotal = 0;
        Object[] columna = new Object[7];
        Iterator<DetalleFacturaCotizacion> nombreIterator = detalleFacturaItems.iterator();
        while (nombreIterator.hasNext()) {
            DetalleFacturaCotizacion a = nombreIterator.next();
            valorTotal += a.getValor() * a.getCantidad();
            descuTotal += a.getDescuento();
            impuestoTotal += a.getImpuesto().getImpuesto2();
            columna[0] = (int) a.getCantidad();
            columna[1] = a.getNomprod();
            columna[2] = formateador.format(a.getValor());
            columna[3] = formateador.format(a.getDescuento());
            columna[4] = formateador.format(a.getSubtotal());
            columna[5] = formateador.format(a.getImpuesto().getImpuesto2());
            columna[6] = keyItem;
            modelo.addRow(columna);
            keyItem++;
        }
        float totalApagar = (valorTotal - descuTotal) + impuestoTotal;
        pr.lblSubtotal.setText(formateador.format(valorTotal));
        pr.lblDescuentoTotal.setText(formateador.format(descuTotal));
        pr.lblTotalImpuesto.setText(formateador.format(impuestoTotal));
        pr.lbltotalFactura.setText(formateador.format(totalApagar));
        pr.tblDetallefactura.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tblDetallefactura.setRowSorter(ordenar);
        pr.tblDetallefactura.getColumnModel().getColumn(6).setMaxWidth(0);
        pr.tblDetallefactura.getColumnModel().getColumn(6).setMinWidth(0);
        pr.tblDetallefactura.getColumnModel().getColumn(6).setPreferredWidth(0);
        pr.tblDetallefactura.setModel(modelo);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

        if (e.getSource() == ps.txtCostoUnidad && !ps.txtCostoUnidad.getText().equals("")) {

            String valcu = formateador.format((Integer.parseInt(ps.txtCostoUnidad.getText())));
            ps.txtCostoUnidad.setText(valcu);

        }
        if (e.getSource() == ps.txtPrecioVenta && !ps.txtPrecioVenta.getText().equals("")) {
            String valcv = formateador.format(Integer.parseInt(ps.txtPrecioVenta.getText()));
            ps.txtPrecioVenta.setText(valcv);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == pr.spCantidad2) {
            float cantidad = (float) pr.spCantidad2.getValue();
            float name = valorProd * cantidad;
            String v = formateador.format(name);
            pr.txtCostoServicio.setText(v);
            pr.txtCostoServicio.requestFocus();
        }
        if (e.getSource() == SelectTipoHospedaje.spcantS) {
            String option = (String) SelectTipoHospedaje.cboTiposHospedajes.getSelectedItem();
            if (option == null || option.equals("-- Seleccione --")) {
                SelectTipoHospedaje.spcantS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
                SelectTipoHospedaje.lblTotal.setText("0");
                JOptionPane.showMessageDialog(null, "¡Seleccione un tipo de habitación!");
                return;
            }
            float cantidad = Float.parseFloat(SelectTipoHospedaje.spcantS.getValue().toString());
            String valFormat = "";
            if (cantidad == 0) {
                valFormat = formateador.format(0);
                SelectTipoHospedaje.lblTotal.setText(valFormat);
            } else if (cantidad == 1) {
                valorModal = Float.parseFloat(datosTipoHabitacion[2]);
                valFormat = formateador.format(valorModal);
                SelectTipoHospedaje.lblTotal.setText(valFormat);
            } else if (cantidad == 2) {
                valorModal = Float.parseFloat(datosTipoHabitacion[3]);
                valFormat = formateador.format(valorModal);
                SelectTipoHospedaje.lblTotal.setText(valFormat);
            } else if (cantidad > 2) {
                valorModal = Float.parseFloat(datosTipoHabitacion[3]);
                float val = valorModal + (Float.parseFloat(datosTipoHabitacion[4]) * (cantidad - 2));
                valFormat = formateador.format(val);
                SelectTipoHospedaje.lblTotal.setText(valFormat);
            }
        }
    }

    public Facturas getFactura() {
        if (factura == null) {
            factura = new Facturas();
        }
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    private void getDatosFactura(int estadoFactura, String btnAction) throws SQLException, JRException {
        String cliente = (String) Principal.lblCliente.getSelectedItem();
        String[] identificacionCliente = cliente.split(" ");
        String consecutivoFactura = pr.txtConsecutivoFactura.getText();
        String terminosDePago = (String) pr.cboTerminoDePago.getSelectedItem();
        String[] idtermino = terminosDePago.split("-");
        Date vencimiento = pr.clVencimientomanual.getDate();
        Object[] campos = null;
        if (idtermino[0].trim().equals("1")) {
            campos = new Object[3];
            campos[0] = Principal.lblCliente;
            campos[1] = pr.cboTerminoDePago;
            campos[2] = pr.clVencimientomanual;
        } else {
            campos = new Object[2];
            campos[0] = Principal.lblCliente;
            campos[1] = pr.cboTerminoDePago;
        }
        if (idtermino[0].trim().equals("1")) {
            if (vencimiento != null) {
                if (vencimiento.getTime() <= new Date().getTime()) {
                    pr.clVencimientomanual.setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));
                    JOptionPane.showMessageDialog(null, "¡La fecha de vencimiento no debe ser menor ni igual a la actual!");
                    return;
                }
            }
        }
        if (validarCampos(campos)) {
            JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
            return;
        }
        if (detalleFacturaItems.isEmpty()) {
            pr.jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));
            JOptionPane.showMessageDialog(null, "¡No has agregado Ítems a la factura!");
            return;
        } else {
            pr.jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
        }
        getFactura();
        factura.setIdFactura(consecutivoFactura);
        factura.setTerminosdepago(Integer.parseInt(idtermino[0].trim()));
        factura.getComprador().setIdCliente(Utils.Utils.getIdCliente(identificacionCliente[0].trim(), true));
        switch (identificacionCliente.length) {
            case 2:
                factura.getComprador().setNombreRazon(identificacionCliente[1].trim());
                break;
            case 3:
                factura.getComprador().setNombreRazon(identificacionCliente[1].trim() + " " + identificacionCliente[2].trim());
                break;
            case 4:
                factura.getComprador().setNombreRazon(identificacionCliente[1].trim() + " " + identificacionCliente[2].trim() + " " + identificacionCliente[3].trim());
                break;
            case 5:
                factura.getComprador().setNombreRazon(identificacionCliente[1].trim() + " " + identificacionCliente[2].trim() + " " + identificacionCliente[3].trim() + " " + identificacionCliente[4].trim());
                break;
        }
        factura.getComprador().setIdentificacion(identificacionCliente[0].trim());
        factura.getVendedor().setIdEmpresa(Principal.idVendedor.getText());
        factura.getUsuario().setIdUsuario(Principal.idUsuarioLog.getText());
        factura.setFecha(new Date());
        factura.getResolucion().setIdResolucion(Principal.IdResolucion.getText());
        factura.getEstado().setIdEstadoFactura(estadoFactura);
        factura.setNota(pr.txtNota.getText());
        factura.setTransacion(Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "facturas"));
        int subtotal = Integer.parseInt(pr.lblSubtotal.getText().replace(".", ""));
        int iva = Integer.parseInt(pr.lblTotalImpuesto.getText().replace(".", ""));
        int total = Integer.parseInt(pr.lbltotalFactura.getText().replace(".", ""));
        int descuento = Integer.parseInt(pr.lblDescuentoTotal.getText().replace(".", ""));
        factura.setSubtotal((float) subtotal);
        factura.setIva((float) iva);
        float ajuste = Utils.Utils.ajusteFactura(total);
        factura.setTotal((float) total - ajuste);
        factura.setDescuentoTotal((float) descuento);
        switch (idtermino[0].trim()) {
            case "1":
                factura.setVencimiento(vencimiento);
                break;
            case "2":
                factura.setVencimiento(new Date());
                break;
            case "3":
                factura.setVencimiento(sumarRestarDiasFecha(new Date(), 8, 1));//1 para dias, 0 para meses
                break;
            case "4":
                factura.setVencimiento(sumarRestarDiasFecha(new Date(), 15, 1));
                break;
            case "5":
                factura.setVencimiento(sumarRestarDiasFecha(new Date(), 30, 1));
                break;
            case "6":
                factura.setVencimiento(sumarRestarDiasFecha(new Date(), 60, 1));
                break;
        }
        DetalleFacturaDataSource datasource = null;
        switch (btnAction) {
            case "btnSaveAddPay":
                saveFactura(0, isHospedaje, id_hospedajeU);
                break;
            case "btnPreview":
                datasource = new DetalleFacturaDataSource();
                datasource.setListaDetalle(detalleFacturaItems);
                Utils.Utils.generateReporte(factura, datasource, "", "");
                break;
            case "btnSaveAndNew":
                saveFactura(1, isHospedaje, id_hospedajeU);
                datasource = new DetalleFacturaDataSource();
                datasource.setListaDetalle(detalleFacturaItems);
                Utils.Utils.generateReporte(factura, datasource, "FACTURA DE VENTA", "FACTURA DE VENTA");
                detalleFacturaItems.clear();
                break;
            case "btnOlySave":
                saveFactura(2, isHospedaje, id_hospedajeU);
                datasource = new DetalleFacturaDataSource();
                datasource.setListaDetalle(detalleFacturaItems);
                Utils.Utils.generateReporte(factura, datasource, "FACTURA DE VENTA", "FACTURA DE VENTA");
                break;
            case "btnCotizar":
                saveFactura(3, false, 0);
                datasource = new DetalleFacturaDataSource();
                datasource.setListaDetalle(detalleFacturaItems);
                Utils.Utils.generateReporte(factura, datasource, stauFPreview, "COTIZACIÓN");
                break;
        }
    }

    public boolean validarCampos(Object campos[]) {
        int countErrors = 0;
        for (Object componente : campos) {
            if (componente instanceof JTextField) {
                boolean equals = ((JTextField) componente).getText().equals("");
                if (equals) {
                    countErrors++;
                    ((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
            if (componente instanceof JTextArea) {
                boolean equals = ((JTextArea) componente).getText().equals("");
                if (equals) {
                    countErrors++;
                    ((JTextArea) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JTextArea) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
            if (componente instanceof JComboBox) {
                boolean equals = ((JComboBox) componente).getSelectedItem().equals("-- Seleccione --");
                if (equals) {
                    countErrors++;
                    ((JComboBox) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JComboBox) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
            if (componente instanceof JDateChooser) {
                boolean equals = ((JDateChooser) componente).getDate() == null;
                if (equals) {
                    countErrors++;
                    ((JDateChooser) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JDateChooser) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
            if (componente instanceof JFormattedTextField) {
                boolean equals = ((JFormattedTextField) componente).getValue() == null;
                if (equals) {
                    countErrors++;
                    ((JFormattedTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JFormattedTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
            if (componente instanceof JSpinner) {
                boolean equals = ((JSpinner) componente).getValue() == null;
                if (equals) {
                    countErrors++;
                    ((JSpinner) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JSpinner) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                }
            }
        }
        return countErrors > 0;
    }

    public Date sumarRestarDiasFecha(Date fecha, int dom, int diaMes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        if (diaMes == 1) {
            calendar.add(Calendar.DAY_OF_YEAR, dom);  // numero de días a añadir, o restar en caso de días<0  
        } else {
            calendar.add(Calendar.MONTH, dom);  // numero de días a añadir, o restar en caso de días<0
        }
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    private void cleanDetalle() {
        pr.spCantidad2.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
        pr.spCantidad2.setEnabled(false);
        pr.cboProductoServ.setSelectedItem("-- Seleccione --");
        pr.cboImpuesto.setSelectedItem("-- Seleccione --");
        pr.txtCostoServicio.setText("");
        pr.txtDescuento.setText("");
    }

    private void saveFactura(int partialPay, boolean isHospedaje, int id_hospedajeU) {

        Object[] numeros = {gp.txtValor, gp.txtCuatroDigitos};
        SLetra(numeros, false);

        switch (partialPay) {
            case 0:
                try {
                    facturaDao = new FacturasDAO();
                    int estadoFactura = 1;
                    if (isHospedaje) {
                        if (fechaSalida.equals("")) {
                            fechaSalida = sb.format(new Date());
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) > 0.0 && factura.getTotal() > Utils.Utils.getAbonos("", id_hospedajeU)) {
                            estadoFactura = 3;
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) == factura.getTotal()) {
                            estadoFactura = 2;
                        }
                    }
                    boolean facturacreada = facturaDao.crearFacturas(factura.getIdFactura(),
                            factura.getFecha(),
                            factura.getResolucion().getIdResolucion(),
                            factura.getComprador().getIdCliente(),
                            factura.getSubtotal(),
                            factura.getIva(),
                            factura.getTotal(),
                            estadoFactura,
                            factura.getVendedor().getIdEmpresa(),
                            factura.getUsuario().getIdUsuario(),
                            factura.getTerminosdepago(),
                            factura.getVencimiento(), factura.getNota(), factura.getTransacion(), "create", detalleFacturaItems, 1);
                    if (facturacreada) {
                        if (isHospedaje) {
                            ArrayList<String> consultas = new ArrayList();
                            consultas.add("update hospedajes set id_factura = '" + factura.getIdFactura() + "', fecha_salida = '" + fechaSalida + "' where id_hospedaje = " + idHospedaje + "");
                            consultas.add("update pagos set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            consultas.add("update recibos_caja set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            hospedajeDao = new HospedajesDAO();
                            if (hospedajeDao.updateList(consultas)) {
                                isHospedaje = false;
                                id_hospedajeU = 0;
                                JOptionPane.showMessageDialog(null, "¡Actualización exitosa!");
                            }
                        }
                        if (!listRetencionesPagos.isEmpty()) {
                            guardarRetenciones();
                            listRetencionesPagos.clear();
                        }
                        JOptionPane.showMessageDialog(null, "¡Factura creada con éxito!");
                        fireReport = true;
                        float abonos = Utils.Utils.getAbonos(factura.getIdFactura(), id_hospedajeU);
                        float retenciones = Utils.Utils.getRetencionesByFactura(factura.getIdFactura());
                        float saldo = factura.getTotal() - abonos - retenciones;
                        gp.txtTotalAPagar.setText(formateador.format(saldo));
                        gp.setLocationRelativeTo(null);
                        gp.setVisible(true);
                        optNewFactura = "factura";
                        cargarTablaFactura("", true);
                        facturaDao = null;
                        hospedajeDao = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Ocurrió un error al crear la factura!");
                    }
                } catch (SQLException e) {
                    System.out.println("error " + e);
                }
                break;
            case 1:
                try {
                    facturaDao = new FacturasDAO();
                    int estadoFactura = 1;
                    if (isHospedaje) {
                        if (fechaSalida.equals("")) {
                            fechaSalida = sb.format(new Date());
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) > 0.0 && factura.getTotal() > Utils.Utils.getAbonos("", id_hospedajeU)) {
                            estadoFactura = 3;
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) == factura.getTotal()) {
                            estadoFactura = 2;
                        }
                    }
                    boolean facturacreada = facturaDao.crearFacturas(factura.getIdFactura(),
                            factura.getFecha(),
                            factura.getResolucion().getIdResolucion(),
                            factura.getComprador().getIdCliente(),
                            factura.getSubtotal(),
                            factura.getIva(),
                            factura.getTotal(),
                            estadoFactura,
                            factura.getVendedor().getIdEmpresa(),
                            factura.getUsuario().getIdUsuario(),
                            factura.getTerminosdepago(),
                            factura.getVencimiento(), factura.getNota(), factura.getTransacion(), "create", detalleFacturaItems, 1);
                    if (facturacreada) {
                        if (isHospedaje) {
                            ArrayList<String> consultas = new ArrayList();
                            consultas.add("update hospedajes set id_factura = '" + factura.getIdFactura() + "', fecha_salida = '" + fechaSalida + "' where id_hospedaje = " + idHospedaje + "");
                            consultas.add("update pagos set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            consultas.add("update recibos_caja set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            hospedajeDao = new HospedajesDAO();
                            if (hospedajeDao.updateList(consultas)) {
                                isHospedaje = false;
                                id_hospedajeU = 0;
                                JOptionPane.showMessageDialog(null, "¡Actualización exitosa!");
                            }
                        }
                        if (!listRetencionesPagos.isEmpty()) {
                            guardarRetenciones();
                            listRetencionesPagos.clear();
                        }
                        JOptionPane.showMessageDialog(null, "¡Factura creada con éxito!");
                        float abonos = Utils.Utils.getAbonos(factura.getIdFactura(), id_hospedajeU);
                        float retenciones = Utils.Utils.getRetencionesByFactura(factura.getIdFactura());
                        float saldo = factura.getTotal() - abonos - retenciones;
                        gp.txtTotalAPagar.setText(formateador.format(saldo));
                        facturaDao = null;
                        hospedajeDao = null;
                        optNewFactura = "factura";
                        limpiarFactura();
                        pr.txtConsecutivoFactura.setText(Utils.Utils.getNextConsecutivo(factura.getVendedor().getIdEmpresa(), 1));
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Ocurrió un error al crear la factura!");
                    }
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
                break;
            case 2:
                try {
                    facturaDao = new FacturasDAO();
                    int estadoFactura = 1;
                    if (isHospedaje) {
                        if (fechaSalida.equals("")) {
                            fechaSalida = sb.format(new Date());
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) > 0.0 && factura.getTotal() > Utils.Utils.getAbonos("", id_hospedajeU)) {
                            estadoFactura = 3;
                        }
                        if (Utils.Utils.getAbonos("", id_hospedajeU) == factura.getTotal()) {
                            estadoFactura = 2;
                        }
                    }
                    boolean facturacreada = facturaDao.crearFacturas(factura.getIdFactura(),
                            factura.getFecha(),
                            factura.getResolucion().getIdResolucion(),
                            factura.getComprador().getIdCliente(),
                            factura.getSubtotal(),
                            factura.getIva(),
                            factura.getTotal(),
                            estadoFactura,
                            factura.getVendedor().getIdEmpresa(),
                            factura.getUsuario().getIdUsuario(),
                            factura.getTerminosdepago(),
                            factura.getVencimiento(), factura.getNota(), factura.getTransacion(), "create", detalleFacturaItems, 1);
                    if (facturacreada) {
                        if (isHospedaje) {
                            ArrayList<String> consultas = new ArrayList();
                            consultas.add("update hospedajes set id_factura = '" + factura.getIdFactura() + "', fecha_salida = '" + fechaSalida + "' where id_hospedaje = " + idHospedaje + "");
                            consultas.add("update pagos set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            consultas.add("update recibos_caja set id_factura = '" + factura.getIdFactura() + "' where id_hospedaje = " + id_hospedajeU + "");
                            hospedajeDao = new HospedajesDAO();
                            if (hospedajeDao.updateList(consultas)) {
                                isHospedaje = false;
                                id_hospedajeU = 0;
                                JOptionPane.showMessageDialog(null, "¡Actualización exitosa!");
                            }
                        }
                        if (!listRetencionesPagos.isEmpty()) {
                            guardarRetenciones();
                            listRetencionesPagos.clear();
                        }
                        JOptionPane.showMessageDialog(null, "¡Factura creada con éxito!");
                        float abonos = Utils.Utils.getAbonos(factura.getIdFactura(), id_hospedajeU);
                        float retenciones = Utils.Utils.getRetencionesByFactura(factura.getIdFactura());
                        float saldo = factura.getTotal() - abonos - retenciones;
                        gp.txtTotalAPagar.setText(formateador.format(saldo));
                        facturaDao = null;
                        hospedajeDao = null;
                        limpiarFactura();
                        optNewFactura = "factura";
                        ocultarPaneles("mnuFacturacion");
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Ocurrió un error al crear la factura!");
                    }
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
                break;
            case 3:
                try {
                    facturaDao = new FacturasDAO();
                    boolean facturacreada = facturaDao.crearFacturas(factura.getIdFactura(),
                            factura.getFecha(),
                            factura.getResolucion().getIdResolucion(),
                            factura.getComprador().getIdCliente(),
                            factura.getSubtotal(),
                            factura.getIva(),
                            factura.getTotal(),
                            6,
                            factura.getVendedor().getIdEmpresa(),
                            factura.getUsuario().getIdUsuario(),
                            factura.getTerminosdepago(),
                            factura.getVencimiento(), factura.getNota(), factura.getTransacion(), "create", detalleFacturaItems, 2);
                    if (facturacreada) {
                        JOptionPane.showMessageDialog(null, "¡Cotización creada con éxito!");
                        limpiarFactura();
                        cargarTablaFactura("", false);
                        facturaDao = null;
                        optNewFactura = "cotizaciones";
                        ocultarPaneles("mnuFacturacion");
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Ocurrió un error al crear la cotización!");
                    }
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
                break;
        }
    }

    private void guardarResolucion() {
        try {
            Utils.Utils.desactivarResolucion(Principal.idVendedor.getText());
            resolucionDao = new ResolucionesFacturasDAO();
            String tipoRes = (String) rf.cboTipoResolucion.getSelectedItem();
            String[] partes = tipoRes.split("-");
            String msn = resolucionDao.crearResolucionesFactura(rf.txtResolucion.getText(),
                    rf.cldFechaResolucion.getDate(),
                    rf.txtPrefijo.getText(),
                    Integer.parseInt(rf.txtInicial.getText()),
                    Integer.parseInt(rf.txtFinal.getText()),
                    Integer.parseInt(partes[0]),
                    true,
                    Principal.idVendedor.getText(),
                    Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "resoluciones_facturas"),
                    "create");
            JOptionPane.showMessageDialog(null, msn);
            rf.dispose();
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private void guardarVinculacionUsuario() {
        try {
            usuariosAppDao = new UsuariosAppDAO();
            String roles = (String) vu.cmbRol.getSelectedItem();
            String[] partesR = roles.split("-");
            String tipoId = (String) vu.cmbTipoDocumento.getSelectedItem();
            String[] partesT = tipoId.split("-");
            String r = usuariosAppDao.crearUsuarioApp(vu.txtDocumento.getText(),
                    Integer.parseInt(partesT[0]),
                    Principal.idVendedor.getText(),
                    Integer.parseInt(partesR[0]),
                    Arrays.toString(vu.txtClave1.getPassword()),
                    vu.txtNombres.getText(),
                    vu.txtApellidos.getText(),
                    1,
                    Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "usuarios_app"),
                    "create");
            JOptionPane.showMessageDialog(null, r);
            vu.dispose();
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private void guardarConsecutivo() {
        try {
            consecutivoImprimiblesDao = new ConsecutivosImprimiblesDAO();
            String tipoI = (String) mn.cmbImprimible.getSelectedItem();
            String[] partesR = tipoI.split("-");
            String r = consecutivoImprimiblesDao.crearConsecutivoImprimible(0,
                    mn.txtPrefijo.getText(),
                    Integer.parseInt(mn.spnConsecutivo.getValue().toString()),
                    Integer.parseInt(partesR[0]),
                    Principal.idVendedor.getText(),
                    Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "consecutivos_imprimibles"),
                    "create");
            JOptionPane.showMessageDialog(null, r);
            mn.dispose();
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private void actualizarClave() {
        try {
            boolean correcto = Utils.Utils.actualizarClave(Principal.idUsuarioLog.getText(), Arrays.toString(cc.txtNuevaClave.getPassword()));
            if (correcto) {
                JOptionPane.showMessageDialog(null, "¡Se actualizó correctamente la clave!");
                Object[] campos = {cc.txtClaveActual, cc.txtNuevaClave, cc.txtNuevaClave2};
                cleanCampos(campos);
                cc.dispose();
            }
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private void addFilter() {
        FileChooser.setFileFilter(new FileNameExtensionFilter("Imagen (*.PNG)", "png"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == rh.tblHabitaciones) {
            int fila = rh.tblHabitaciones.getSelectedRow();
            registro = Integer.parseInt(rh.tblHabitaciones.getValueAt(fila, 0).toString());
        }

        if (e.getSource() == pr.tbFacturas) {
            int fila = pr.tbFacturas.getSelectedRow();
            int column = pr.tbFacturas.getSelectedColumn();
            if (fila >= 0 && column == 5) {
                MoreInfoFactura mi = new MoreInfoFactura(pr, true);
//               {Factura, "Valor", "Creada", "Vence", "Estado", "Ver Mas"}
                mi.lblFactura.setText(pr.tbFacturas.getValueAt(fila, 0).toString());
                mi.lblFechaCreada.setText(pr.tbFacturas.getValueAt(fila, 2).toString());
                mi.lblVence.setText(pr.tbFacturas.getValueAt(fila, 3).toString());
                mi.estado.setText(pr.tbFacturas.getValueAt(fila, 4).toString());
                mi.lblValor.setText(pr.tbFacturas.getValueAt(fila, 1).toString());
                mi.setLocationRelativeTo(null);
                mi.setVisible(true);
            }

        }

        if (e.getSource() == emp.imagen) {
            countAction++;
            File archivo;
            if (countAction == 1) {
                addFilter();
            }
            FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (FileChooser.showDialog(null, "Seleccionar Archivo") == JFileChooser.APPROVE_OPTION) {
                archivo = FileChooser.getSelectedFile();
                if (archivo.getName().endsWith("PNG") || archivo.getName().endsWith("png")) {
                    foto = String.valueOf(archivo);
                    ImageIcon icon = new ImageIcon(archivo.toString());
                    Image conver = icon.getImage();
                    Image tam = conver.getScaledInstance(emp.imagen.getWidth(), emp.imagen.getHeight(), Image.SCALE_SMOOTH);
                    ii = new ImageIcon(tam);
                    emp.imagen.setIcon(ii);
                    NombreArchivo = FileChooser.getName(archivo);
                    JOptionPane.showMessageDialog(null, "Archivo Seleccionado: " + String.valueOf(NombreArchivo));
                } else {
                    JOptionPane.showMessageDialog(null, "Elija un formato valido");
                }
            } else {

            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void registrarhuesped() {
        Date fechaSalida = null;
        try {
            if (!rh.chkSinSalida.getState()) {
                fechaSalida = sumarRestarDiasFecha(new Date(), Integer.parseInt(rh.spnNoches.getValue().toString()), 1);
            }
            hospedajeDao = new HospedajesDAO();
            String huesped = (String) rh.txtHuesped.getSelectedItem();
            String[] idHuesped = huesped.split(" ");
            String motivo = (String) rh.cboMotivo.getSelectedItem();
            String[] idMotivo = motivo.split(" - ");
            if (Utils.Utils.updateDisponibilidadHabitacion(registro, 1)) {
                String response = hospedajeDao.crearHospedaje(0,
                        Integer.parseInt(idMotivo[1]),
                        new Date(),
                        fechaSalida,
                        registro,
                        Utils.Utils.getIdCliente(idHuesped[0], true),
                        Integer.parseInt(rh.spnAdultos.getValue().toString()),
                        Integer.parseInt(rh.spnNinos.getValue().toString()),
                        new Date(),
                        null,
                        Principal.idUsuarioLog.getText(),
                        valor,
                        Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "hospedajes"),
                        "create");
                if (!response.equals("")) {
                    Object[] campos = {rh.txtHuesped, rh.chkAmericana, rh.chkChalet, rh.cboMotivo};
                    cleanCampos(campos);
                    limpiarTables(rh.tblHabitaciones);
                    pr.cboFiltroHospedaje.setSelectedItem("Sin Facturar");
                    cargarHospedajes("", "");
                }
                JOptionPane.showMessageDialog(null, response);
            }
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private void addPay(boolean hospedaje, int cliente, int idH) {
        Object[] campos = null;
        String tipopago = (String) gp.cboTipoPago.getSelectedItem();
        String franquicia = (String) gp.txtBancos.getSelectedItem();
        if (tipopago.equals("-- Seleccione --")) {
            JOptionPane.showMessageDialog(null, "¡Debe seleccionar un tipo de pago!");
            return;
        }
        String[] id = tipopago.split("-");
        String[] idFranquicia = null;
        if (!franquicia.equals("-- Seleccione --")) {
            idFranquicia = franquicia.split(" ");
        } else {
            idFranquicia = new String[1];
            idFranquicia[0] = "0";
        }
        if (id[0].trim().equals("1") || id[0].trim().equals("2") || id[0].trim().equals("3")) {
            campos = new Object[2];
            campos[0] = gp.txtValor;
            campos[1] = gp.taObservaciones;
        }
        if (id[0].trim().equals("4")) {
            campos = new Object[3];
            campos[0] = gp.txtValor;
            campos[1] = gp.taObservaciones;
            campos[2] = gp.txtCheque;
        }
        if (id[0].trim().equals("5") || id[0].trim().equals("6")) {
            campos = new Object[5];
            campos[0] = gp.txtValor;
            campos[1] = gp.txtCuatroDigitos;
            campos[2] = gp.txtVoucher;
            campos[3] = gp.taObservaciones;
            campos[4] = gp.txtBancos;
        }
        if (validarCampos(campos)) {
            JOptionPane.showMessageDialog(null, "¡Hay campos vacíos!");
        } else {
            try {
                recibosDeCajaDao = new RecibosCajaDAO();
                String concepto = "";
                int id_recibo = 0;
                int idEstadoFactura = 0;
                boolean update = false;
                String response = "";
                if (!hospedaje) {
                    idEstadoFactura = 3;
                    concepto = "Pago parcial a la factura No. " + factura.getIdFactura();
                    if (fireReport) {
                        if (factura.getTotal() == Float.parseFloat(gp.txtValor.getText())) {
                            concepto = "Pago total a la factura No. " + factura.getIdFactura();
                            idEstadoFactura = 2;
                        }
                    } else if (Float.parseFloat(gp.txtTotalAPagar.getText().replace(".", "")) == Float.parseFloat(gp.txtValor.getText())) {
                        concepto = "Pago total a la factura No. " + factura.getIdFactura();
                        idEstadoFactura = 2;
                    }
                } else {
                    concepto = gp.taObservaciones.getText();
                }
                String descBanco = "";
                if (!gp.txtBancos.getSelectedItem().equals("") && gp.txtBancos.getSelectedItem() != null) {
                    if (!gp.txtCheque.getText().equals("") && gp.txtCheque.getText() != null) {
                        descBanco = "No. Cheque: " + gp.txtCheque.getText();
                    }
                }
                if (!hospedaje) {
                    id_recibo = recibosDeCajaDao.crearReciboCaja(0, factura.getIdFactura(), Utils.Utils.getNextConsecutivo(factura.getVendedor().getIdEmpresa(), 3),
                            new Date(), factura.getVendedor().getIdEmpresa(), factura.getComprador().getIdCliente(), Float.parseFloat(gp.txtValor.getText()),
                            concepto, descBanco, Integer.parseInt(id[0].trim()), Utils.Utils.getTransaccion(factura.getVendedor().getIdEmpresa(), "recibos_caja"), 0, "create");
                } else {
                    id_recibo = recibosDeCajaDao.crearReciboCaja(0, "", Utils.Utils.getNextConsecutivo(Principal.idVendedor.getText(), 3),
                            new Date(), Principal.idVendedor.getText(), cliente, Float.parseFloat(gp.txtValor.getText()),
                            concepto, descBanco, Integer.parseInt(id[0].trim()), Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "recibos_caja"), idH, "create");
                }
                if (id_recibo > 0) {
                    Utils.Utils.updateConsecutivoImprimible(Principal.idVendedor.getText(), 3);
                    if (!hospedaje) {
                        update = Utils.Utils.updateEstadoFactura(factura.getIdFactura(), idEstadoFactura);
                    }
                    pagosDao = new PagosDAO();
                    if (!hospedaje) {
                        response = pagosDao.crearPagos(0, factura.getComprador().getIdCliente(), ((id[0].trim().equals("1"))) ? 2 : 1,
                                new Date(), gp.taObservaciones.getText(), null, Integer.parseInt(id[0].trim()), 0,
                                id_recibo - 1, Float.parseFloat(gp.txtValor.getText()), 0, factura.getIdFactura(), gp.txtCuatroDigitos.getText(),
                                gp.txtVoucher.getText(), Utils.Utils.getTransaccion(factura.getVendedor().getIdEmpresa(), "pagos"), Integer.parseInt(idFranquicia[0]),
                                Principal.idUsuarioLog.getText(), 0, "create");
                    } else {
                        response = pagosDao.crearPagos(0, cliente, ((id[0].trim().equals("1"))) ? 2 : 1,
                                new Date(), gp.taObservaciones.getText(), null, Integer.parseInt(id[0].trim()), 0,
                                id_recibo - 1, Float.parseFloat(gp.txtValor.getText()), 0, "", gp.txtCuatroDigitos.getText(),
                                gp.txtVoucher.getText(), Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "pagos"), Integer.parseInt(idFranquicia[0]),
                                Principal.idUsuarioLog.getText(), idH, "create");
                    }
                    if (response.equals("Pago realizado con exito")) {
                        if (!hospedaje) {
                            if (update) {
                                Object[] objetos = {factura};
                                Object[] arrays = {detalleFacturaItems};
                                cleanObjects(objetos, arrays);
                            } else {
                                JOptionPane.showMessageDialog(null, "¡Ocurrió un error al cambiar el estado de la factura!");
                                gp.dispose();
                            }
                        }
                        JOptionPane.showMessageDialog(null, response);
                        gp.txtIsHospedaje.setText("");
                        gp.txtIsCliente.setText("");
                        pagosDao = null;
                        recibosDeCajaDao = null;
                        cleanCampos(campos);
                        gp.dispose();
                        try {
                            if (!hospedaje) {
                                Utils.Utils.generateReciboCaja(id_recibo - 1, factura.getVendedor().getIdEmpresa(), factura.getIdFactura(), "");
                            } else {
                                Utils.Utils.generateReciboCaja(id_recibo - 1, Principal.idVendedor.getText(), "", "");
                            }
                            Object[] campos2 = {pr.cboTerminoDePago, pr.clVencimientomanual, Principal.lblCliente,
                                pr.txtNota, pr.cboProductoServ, pr.txtCostoServicio, pr.cboImpuesto, pr.spCantidad2,
                                pr.txtDescuento};
                            cleanCampos(campos2);
                            if (!hospedaje) {
                                limpiarTables(pr.tblDetallefactura);
                                ocultarPaneles("mnuFacturacion");
                                cargarTablaFactura("", true);
                            }
                        } catch (JRException ex) {
                            System.out.println("error al generar recibo " + ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Ocurrió un error al registrar el pago!");
                        gp.dispose();
                    }
                }
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

    }

    private void cleanObjects(Object objects[], Object arrays[]) {
        if (objects.length > 0) {
            for (Object obj : objects) {
                obj = null;
            }
        }
        if (arrays.length > 0) {
            for (Object arr : arrays) {
                if (arr instanceof ArrayList) {
                    ((ArrayList) arr).clear();
                }
            }
        }

    }

    public void cleanCampos(Object campos[]) {
        for (Object componente : campos) {
            if (componente instanceof JTextArea) {
                ((JTextArea) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                ((JTextArea) componente).setText("");
            }
            if (componente instanceof JTextField) {
                ((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                ((JTextField) componente).setText("");
            }
            if (componente instanceof JCheckBox) {
                ((JCheckBox) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                ((JCheckBox) componente).setSelected(false);
            }
            if (componente instanceof CheckBox) {
                ((CheckBox) componente).setSelected(false);
            }
            if (componente instanceof JComboBox) {
                ((JComboBox) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                ((JComboBox) componente).setSelectedItem("-- Seleccione --");//2C6791                
            }
            if (componente instanceof JDateChooser) {
                ((JDateChooser) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));  //2C6791      
                ((JDateChooser) componente).setDate(null); //2C6791                  
            }
            if (componente instanceof JFormattedTextField) {
                ((JFormattedTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));  //2C6791  
                ((JFormattedTextField) componente).setValue(null);
            }
            if (componente instanceof JSpinner) {
                ((JSpinner) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484"))); //2C6791 
                ((JSpinner) componente).setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
            }
            if (componente instanceof JTextArea) {
                ((JTextArea) componente).setText("");  //2C6791                    
            }
            if (componente instanceof JLabel) {
                ((JLabel) componente).setText("");  //2C6791                    
            }
        }
    }

    public void cargarTablaFactura(String dato, boolean isfactura) throws SQLException {
        pr.tbFacturas.setDefaultRenderer(Object.class, new RenderTable());

        JButton btnMoreInfo = new JButton("Mas Info");

        facturaDao = new FacturasDAO();
        String Factura = "Id Cotización";
        if (isfactura) {
            Factura = "Id Factura";
        }
        String Titulos[] = {Factura, "Valor", "Creada", "Vence", "Estado", "Ver Mas"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        Object[] columna = new Object[6];
        Iterator<Facturas> nombreIterator;
        if (dato.equals("")) {
            if (isfactura) {
                nombreIterator = facturaDao.getFacturasList(Principal.idVendedor.getText()).iterator();
            } else {
                nombreIterator = facturaDao.getCotizacionesList(Principal.idVendedor.getText()).iterator();
            }
        } else {
            if (isfactura) {
                nombreIterator = Utils.Utils.getFacturasList(dato + " AND f.id_estado_factura <> 6").iterator();
            } else {
                nombreIterator = Utils.Utils.getFacturasList(dato + " AND f.id_estado_factura = 6").iterator();
            }
        }
        while (nombreIterator.hasNext()) {
            btnMoreInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            Facturas f = nombreIterator.next();
            columna[0] = f.getIdFactura();
            columna[1] = formateador.format(f.getTotal());
            columna[2] = f.getFecha();
            columna[3] = f.getVencimiento();
            columna[4] = f.getEstado().getNombre();
            columna[5] = btnMoreInfo;
            modelo.addRow(columna);
        }
        pr.tbFacturas.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tbFacturas.setRowSorter(ordenar);
        pr.tbFacturas.getColumnModel().getColumn(5).setMaxWidth(70);
        pr.tbFacturas.getColumnModel().getColumn(5).setMinWidth(70);
        pr.tbFacturas.getColumnModel().getColumn(5).setPreferredWidth(70);
        pr.tbFacturas.setModel(modelo);
        Object[] objetos = {facturaDao};
        Object[] arrays = {};
        cleanObjects(objetos, arrays);
    }

    public void limpiarTables(JTable tabla) {
        String Titulos[] = {};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        tabla.setModel(modelo);
    }

    private void cargarTblHabitaciones() {
        try {
            String Titulos[] = {"id", "Habitación", "Sencillas", "Dobles", "Tipo"};
            modelo = new DefaultTableModel(null, Titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                    return false;
                }
            };
            Object[] columna = new Object[5];
            int adultos = Integer.parseInt(rh.spnAdultos.getValue().toString());
            int ninos = Integer.parseInt(rh.spnNinos.getValue().toString());
            int personas = adultos + ninos;
            Iterator<Habitaciones> nombreIterator = Utils.Utils.getHabitacionesDisponibles(rh.chkAmericana.getState(), rh.chkChalet.getState(), personas).iterator();
            while (nombreIterator.hasNext()) {
                Habitaciones a = nombreIterator.next();
                columna[0] = a.getIdHabitacion();
                columna[1] = a.getNombre();
                columna[2] = a.getCamaSencilla();
                columna[3] = a.getCamaDoble();
                columna[4] = a.getTipoCama().getNombre();
                modelo.addRow(columna);
            }
            rh.tblHabitaciones.setModel(modelo);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
            rh.tblHabitaciones.setRowSorter(ordenar);
            rh.tblHabitaciones.getColumnModel().getColumn(0).setMaxWidth(0);
            rh.tblHabitaciones.getColumnModel().getColumn(0).setMinWidth(0);
            rh.tblHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(0);
            rh.tblHabitaciones.setModel(modelo);

        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    private boolean newFactura(String tipoDoc) {
        boolean error = false;
        if (tipoDoc.equals("factura")) {
            stauFPreview = "BORRADOR";
            try {
                pr.btnCotizar.setEnabled(false);
                String datos = Utils.Utils.requerimientosFactura(Principal.idVendedor.getText(), 1);
                String[] parts = datos.split(",");
                if (!datos.equals("")) {
                    Principal.IdResolucion.setText(parts[4]);
                    // Validar si es regimen comun
                    if (Integer.parseInt(parts[0]) == 1) {
                        // validar resolucion activa
                        if (Integer.parseInt(parts[1]) == 1) {
                            if (parts[5].equals("vigente_fecha")) {
                                if (!parts[6].equals("vigente_numeracion")) {
                                    JOptionPane.showMessageDialog(null, "¡La numeración de la resolución ha finalizado!");
                                    error = true;
                                } else {
                                    ConsecutivoFactura = parts[2] + " " + (Integer.parseInt(parts[3]));
                                    pr.txtConsecutivoFactura.setText(ConsecutivoFactura);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "¡La fecha de la resolución ha caducado!");
                                error = true;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "¡No hay resolución de facturación activa!");
                            error = true;
                        }
                    } else {
                        String datos2 = Utils.Utils.requerimientosFactura(Principal.idVendedor.getText(), 8);
                        if (datos2.equals("")) {
                            JOptionPane.showMessageDialog(null, "¡No se ha definido Numeración para Cuentas de Cobro!");
                            error = true;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se ha definido Numeración o resolución de facturación!");
                    error = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error " + ex);
            }
        } else {
            try {
                stauFPreview = "COTIZACIÓN";
                ConsecutivoFactura = Utils.Utils.getNextConsecutivo(Principal.idVendedor.getText(), 2);
                pr.txtConsecutivoFactura.setText(ConsecutivoFactura);
                pr.btnSaveAddPay.setEnabled(false);
                pr.btnSaveAndNew.setEnabled(false);
                pr.btnOlySave.setEnabled(false);
                pr.btnCotizar.setEnabled(true);
                pr.btnRetencion.setEnabled(false);
                if (ConsecutivoFactura.equals("")) {
                    JOptionPane.showMessageDialog(null, "¡No se ha definido numeración para cotización!");
                    error = true;
                }
            } catch (SQLException ex) {
                error = true;
                System.out.println("Error " + ex);
            }
        }
        return error;
    }

    public void cargarMotivos() throws SQLException {
        motivosDao = new MotivosEstadiaDAO();
        rh.cboMotivo.removeAllItems();
        rh.cboMotivo.addItem("-- Seleccione --");
        Iterator<MotivosEstadia> me = motivosDao.getMotivosEstadiaList().iterator();
        while (me.hasNext()) {
            MotivosEstadia motivo = me.next();
            rh.cboMotivo.addItem(motivo.getNombre() + " - " + motivo.getIdMotivo());
        }
        AutoCompleteDecorator.decorate(rh.cboMotivo);
    }

    public void cargarUnidadesDeMedida() throws SQLException {
        unidadDao = new UnidadesMedidaDAO();
        ps.cboUnidad.removeAllItems();
        ps.cboUnidad.addItem("-- Seleccione --");
        Iterator<UnidadesMedida> umd = unidadDao.getUnidadesMedidaList().iterator();
        while (umd.hasNext()) {
            UnidadesMedida um = umd.next();
            ps.cboUnidad.addItem(um.getNombre());
        }
        AutoCompleteDecorator.decorate(ps.cboUnidad);
    }

    private void guardarItem() throws SQLException {
        String unidad = (String) ps.cboUnidad.getSelectedItem();
        int Unidad2 = 0;
        int stock = 0;
        if (ps.chkProducto.isSelected()) {
            float tmp = (Float) (ps.spnStock.getValue());
            Unidad2 = Utils.Utils.getIdUnidad(unidad);
            stock = Math.round(tmp);
        } else {
            Unidad2 = 1;
        }

        String costoUnidad = ps.txtCostoUnidad.getText();
        costoUnidad = costoUnidad.replace(".", "");
        String precioVenta = ps.txtPrecioVenta.getText();
        precioVenta = precioVenta.replace(".", "");
        if (productosSerDao.crearProductosServicios(0,
                ps.txtNombreItem.getText(),
                ps.txtReferenciaItem.getText(),
                ps.areaDescripcion.getText(),
                Float.parseFloat(costoUnidad),
                Float.parseFloat(precioVenta),
                "",
                ps.cboGarantia.getSelectedItem().toString(),
                Unidad2,
                stock,
                ps.chkProducto.isSelected(),
                null,
                Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "productos_servicios"),
                Principal.idVendedor.getText(),
                "create")) {
            JOptionPane.showMessageDialog(null, "¡Ítem registrado correctamente!");
        }
    }

    private void limpiarFactura() {
        Object[] campos2 = {pr.cboTerminoDePago, pr.clVencimientomanual, Principal.lblCliente,
            pr.txtNota, pr.cboProductoServ, pr.txtCostoServicio, pr.cboImpuesto, pr.spCantidad2,
            pr.txtDescuento, pr.lblSubtotal, pr.lblDescuentoTotal, pr.lblTotalImpuesto, pr.lbltotalFactura};
        cleanCampos(campos2);
        limpiarTables(pr.tblDetallefactura);
        pr.txtConsecutivoFactura.setText("");
        Principal.lblCliente.setEnabled(true);
        rh.txtHuesped.setEnabled(true);
        ConsecutivoFactura = "";
        idProductoservicio = 0;
        idUnidaMedida = "";
        notaHospedaje.clear();
        try {
            cargarTablaFactura("", true);
        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }
    }

    private void cargarHospedajes(String filtro, String cliente) {
        try {
            int idCliente = 0;
            if ("Sin Facturar".equals(filtro)) {
                filtro = "";
            }
            if (!cliente.equals("")) {
                if (Utils.Utils.identificacionValida(cliente)) {
                    idCliente = Utils.Utils.getIdByIdentificacionCliente(cliente);
                } else {
                    JOptionPane.showMessageDialog(null, "¡Identificación no valida!");
                    pr.txtFiltrroIdCliente.setText("");
                    pr.cboFiltroHospedaje.setSelectedItem("Sin Facturar");
                    cargarHospedajes("", "");
                    return;
                }
            }
            String Titulos[] = {"idCliente", "IdHospedaje", "Fecha de Ingreso", "Fecha de Salida", "Total Personas", "Habitación", "Nombre Cliente", "idHabitacion", "Estado", "Factura", "Niños", "Adultos"};
            modelo = new DefaultTableModel(null, Titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                    return false;
                }
            };
            Object[] columna = new Object[12];
            Iterator<Hospedajes> nombreIterator = Utils.Utils.getHospedajeActivo(filtro, idCliente).iterator();
            while (nombreIterator.hasNext()) {
                Hospedajes h = nombreIterator.next();
                columna[0] = h.getCliente().getIdCliente();
                columna[1] = h.getIdHospedaje();
                columna[2] = sa.format(h.getFechaIngreso());
                if (h.getFechaSalida() != null) {
                    columna[3] = sa.format(h.getFechaSalida());
                } else {
                    columna[3] = "";
                }
                columna[4] = h.getTotalPesonas();
                columna[5] = h.getHabitacion().getNombre();
                columna[6] = h.getCliente().getNombreRazon();
                columna[7] = h.getHabitacion().getIdHabitacion();
                switch (filtro) {
                    case "":
                        columna[8] = "Sin facturar";
                        break;
                    case "Aprobada":
                        columna[8] = "Sin pago";
                        break;
                    case "Cobrada":
                        columna[8] = "Pago completo";
                        break;
                    case "Parcialmente Cobrada":
                        columna[8] = "Con abonos";
                        break;
                    case "Anulada":
                        columna[8] = "Factura anulada";
                        break;
                    case "Vencida":
                        columna[8] = "Factura vencida";
                        break;
                }
                if (!h.getEstado().getNombre().equals("Sin Facturar")) {
                    if (new Date().getTime() > h.getFechaSalida().getTime()) {
                        Utils.Utils.updateDisponibilidadHabitacion(h.getHabitacion().getIdHabitacion(), 0);
                    }
                }
                if (h.getFactura().getIdFactura() != null) {
                    columna[9] = h.getFactura().getIdFactura();
                } else {
                    columna[9] = "";
                }
                columna[10] = h.getNumeroAdultos();
                columna[11] = h.getNumeroNinos();
                modelo.addRow(columna);
            }
            pr.tblListaHospedaje.setModel(modelo);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
            pr.tblListaHospedaje.setRowSorter(ordenar);
            pr.tblListaHospedaje.getColumnModel().getColumn(0).setMaxWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(0).setMinWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(0).setPreferredWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(1).setMaxWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(1).setMinWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(1).setPreferredWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(7).setMaxWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(7).setMinWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(7).setPreferredWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(10).setMaxWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(10).setMinWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(10).setPreferredWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(11).setMaxWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(11).setMinWidth(0);
            pr.tblListaHospedaje.getColumnModel().getColumn(11).setPreferredWidth(0);
            pr.tblListaHospedaje.setModel(modelo);
            ocultarPaneles("mnuHospedaje");
        } catch (SQLException ex) {
            System.out.println("error " + ex);
        }
    }

    public void cargarNacionalidades() throws SQLException {
        nacionalidadDao = new NacionalidadesDAO();
        mc.cboNacionalidad.removeAllItems();
        mc.cboNacionalidad.addItem("-- Seleccione --");
        Iterator<Nacionalidades> n = nacionalidadDao.getNacionalidadList().iterator();
        while (n.hasNext()) {
            Nacionalidades pais = n.next();
            mc.cboNacionalidad.addItem(pais.getPais() + " - " + pais.getIdNacionalidad());
        }
        AutoCompleteDecorator.decorate(mc.cboNacionalidad);
    }

    private void facturarHospedaje() {
        int fila = pr.tblListaHospedaje.getSelectedRow();
        if (fila >= 0) {
            try {
                int diasHospedaje = 0;
                int idHabitacion = Integer.parseInt(pr.tblListaHospedaje.getValueAt(fila, 7).toString());
                int numeroPersonas = (int) pr.tblListaHospedaje.getValueAt(fila, 4);
                idHospedaje = Integer.parseInt(pr.tblListaHospedaje.getValueAt(fila, 1).toString());
                fechaSalida = pr.tblListaHospedaje.getValueAt(fila, 3).toString();
                if (fechaSalida.equals("")) {
                    diasHospedaje = Utils.Utils.getDifferenceDays(sa.parse(pr.tblListaHospedaje.getValueAt(fila, 2).toString()), new Date());
                    if (diasHospedaje == 0) {
                        diasHospedaje = 1;
                    }
                } else {
                    diasHospedaje = Utils.Utils.getDifferenceDays(sa.parse(pr.tblListaHospedaje.getValueAt(fila, 2).toString()), sa.parse(pr.tblListaHospedaje.getValueAt(fila, 3).toString()));
                }
                calcularCostoNoche(numeroPersonas, idHabitacion);
                float subtotal = diasHospedaje * valor;
                int idCliente = Integer.parseInt(pr.tblListaHospedaje.getValueAt(fila, 0).toString());
                Principal.lblCliente.setSelectedItem(Utils.Utils.getIdentificacionNombre(idCliente));
                Principal.lblCliente.setEnabled(false);
                cleanDetalle();
                newFactura("factura");
                addDetalleFactura(4,
                        diasHospedaje,
                        ConsecutivoFactura,
                        Utils.Utils.getImpuesto(2, subtotal, 0),
                        valor,
                        "Hospedaje",
                        0,
                        subtotal,
                        2,
                        "Noche(s)");
                ocultarPaneles("btnNewFactura");
            } catch (SQLException | ParseException ex) {
                System.out.println("error " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
        }
    }

    private void addRetencionFactura(RetencionesPagos r) throws SQLException {
        if (listRetencionesPagos.size() > 0) {
            for (int i = 0; i < listRetencionesPagos.size(); i++) {
                if (listRetencionesPagos.get(i).getRetencion().getIdRetencion() == r.getRetencion().getIdRetencion()) {
                    JOptionPane.showMessageDialog(null, "¡No se puede aplicar dos veces la misma retención a una factura!");
                    break;
                } else {
                    listRetencionesPagos.add(r);
                    JOptionPane.showMessageDialog(null, "¡Retención generada correctamente!");
                    gr.dispose();
                    break;
                }
            }
        } else {
            listRetencionesPagos.add(r);
            JOptionPane.showMessageDialog(null, "¡Retención generada correctamente!");
            gr.dispose();
        }
    }

    public boolean guardarRetenciones() throws SQLException {
        retencionesPagosDao = new RetencionesPagosDAO();
        if (!retencionesPagosDao.crearRetencionPago(listRetencionesPagos, Utils.Utils.getTransaccion(Principal.idVendedor.getText(), "retenciones_pagos"))) {
            JOptionPane.showMessageDialog(null, "¡Error al generar la retención!");
        }
        return true;
    }

    private void calcularCostoNoche(int numeroPersonas, int idHabitacion) throws SQLException {
        ArrayList<TiposDeHabitacion> tiposHabitacionFiltro = Utils.Utils.getTiposHabitacionByHabitacion(idHabitacion);
        if (numeroPersonas == 1) {
            valor = tiposHabitacionFiltro.get(0).getCostoPersona();
        }
        if (numeroPersonas == 2) {
            valor = tiposHabitacionFiltro.get(0).getCostoPareja();
        }
        if (numeroPersonas > 2) {
            float adicionales = numeroPersonas - 2;
            float valorAdicionales = tiposHabitacionFiltro.get(0).getCostoPersonaAdicional() * adicionales;
            float valorPareja = tiposHabitacionFiltro.get(0).getCostoPareja();
            valor = valorAdicionales + valorPareja;
        }
    }

    public void SLetra(Object[] campos, boolean letra) {

        for (Object campo : campos) {
            ((JTextField) campo).addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (letra) {
                        if (Character.isDigit(c)) {
                            e.consume();
                        }
                    } else if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
        }
    }

    private void anularFactura(String idfactura, String concepto) throws SQLException {
        if (Utils.Utils.anularfacturas(idfactura)) {
            facturaDao = new FacturasDAO();
            if (facturaDao.anularFacturas(idfactura, concepto, Principal.idUsuarioLog.getText(), Principal.idVendedor.getText())) {
                JOptionPane.showMessageDialog(null, "¡Factura anulada con éxito!");
                af.idfactura.setText("");
                af.taConcepto.setText("");
                af.dispose();
                cargarTablaFactura("", true);
                facturaDao = null;
            } else {
                JOptionPane.showMessageDialog(null, "¡Anulación de factura no se realizó correctamente!");
                facturaDao = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Anulación de factura no se realizó correctamente!");
            facturaDao = null;
        }
    }

    public boolean validarSoloNumeros(String valor) {
        boolean validado;
        try {
            int parseInt = Integer.parseInt(valor);
            validado = true;
        } catch (NumberFormatException e) {
            validado = false;
        }
        return validado;
    }

    public void cerrar() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(pr, "¿En realidad desea cerrar la aplicación?", "Mensaje de Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }
    }

    public void cargarUsuarios(String string, String criterio) throws SQLException {
        String Titulos[] = {"id_cliente", "Identificación", "Nombre / Razón", "Dirección", "Celular", "Nacionalidad"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        Object[] columna = new Object[6];
        Iterator<Clientes> nombreIterator = Utils.Utils.getClientes(string, criterio).iterator();
        while (nombreIterator.hasNext()) {
            Clientes c = nombreIterator.next();
            columna[0] = c.getIdCliente();
            columna[1] = c.getIdentificacion();
            columna[2] = c.getNombreRazon();
            columna[3] = c.getDireccion();
            columna[4] = c.getCelular();
            columna[5] = Utils.Utils.obtenerPaisById(c.getNacionalidad().getIdNacionalidad());
            modelo.addRow(columna);
        }
        pr.tblUsers.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tblUsers.setRowSorter(ordenar);
        pr.tblUsers.getColumnModel().getColumn(0).setMaxWidth(0);
        pr.tblUsers.getColumnModel().getColumn(0).setMinWidth(0);
        pr.tblUsers.getColumnModel().getColumn(0).setPreferredWidth(0);
        pr.tblUsers.setModel(modelo);
    }

    public void cargarClienteEdit(boolean info) throws SQLException {
        int fila = pr.tblUsers.getSelectedRow();
        if (fila >= 0) {
            int id_cliente = Integer.parseInt(pr.tblUsers.getValueAt(fila, 0).toString());
            Clientes c = Utils.Utils.datosClienteById(id_cliente);
            getModalClienteController.ClienteController();
            if (info) {
                mc.cboTipoId.setEnabled(false);
                mc.txtDocumento.setEditable(false);
                mc.cboNacionalidad.setEnabled(false);
                mc.txtNombreRazon.setEditable(false);
                mc.txtDireccion.setEditable(false);
                mc.txtTelefono.setEditable(false);
                mc.txtTelefono2.setEditable(false);
                mc.txtCorreo.setEditable(false);
                mc.txtFax.setEditable(false);
                mc.cldNacimiento.setEnabled(false);
                mc.txtCelular.setEditable(false);
                mc.txtCiudad.setEditable(false);
                mc.chkCliente.setEnabled(false);
                mc.chkProveedor.setEnabled(false);
                mc.txAreaObs.setEditable(false);
                mc.btnGuardarComprador.setVisible(false);
            } else {
                mc.cboTipoId.setEnabled(true);
                mc.txtDocumento.setEditable(true);
                mc.cboNacionalidad.setEnabled(true);
                mc.txtNombreRazon.setEditable(true);
                mc.txtDireccion.setEditable(true);
                mc.txtTelefono.setEditable(true);
                mc.txtTelefono2.setEditable(true);
                mc.txtCorreo.setEditable(true);
                mc.txtFax.setEditable(true);
                mc.cldNacimiento.setEnabled(true);
                mc.txtCelular.setEditable(true);
                mc.txtCiudad.setEditable(true);
                mc.chkCliente.setEnabled(true);
                mc.chkProveedor.setEnabled(true);
                mc.txAreaObs.setEditable(true);
                mc.btnGuardarComprador.setVisible(true);
            }
            mc.cboTipoId.setSelectedItem(Utils.Utils.tipoIdentificacionById(c.getTipo().getIdTipoIdentificacion()));
            mc.txtDocumento.setText(c.getIdentificacion());
            mc.cboNacionalidad.setSelectedItem(Utils.Utils.nacionalidadById(c.getNacionalidad().getIdNacionalidad()));
            mc.txtNombreRazon.setText(c.getNombreRazon());
            mc.txtDireccion.setText(c.getDireccion());
            mc.txtTelefono.setText(c.getTelefono1());
            mc.txtTelefono2.setText(c.getTelefono2());
            mc.txtCorreo.setText(c.getCorreo());
            mc.txtFax.setText(c.getFax());
            mc.cldNacimiento.setDate(c.getFechaNacimiento());
            mc.txtCelular.setText(c.getCelular());
            mc.txtCiudad.setSelectedItem(Utils.Utils.getCiudadById(c.getCiudad().getIdCiudad()));
            mc.chkCliente.setSelected(c.isCliente());
            mc.chkProveedor.setSelected(c.isProveedor());
            mc.idupdate.setText(Integer.toString(c.getIdCliente()));
            mc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "¡Debe seleccionar un cliente!");
        }
    }

    public void cargarTblPagosRealizados(String IdFactura, int ideHospedaje) {
        String Titulos[] = {"id_recibo_caja", "Fecha", "Cuenta", "Tipo Pago", "Valor"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        Object[] columna = new Object[5];
        Iterator<Pagos> nombreIterator = Utils.Utils.getPagos(IdFactura, ideHospedaje).iterator();
        while (nombreIterator.hasNext()) {
            Pagos p = nombreIterator.next();
            columna[0] = p.getReciboCaja().getIdReciboCaja();
            columna[1] = df.format(p.getFecha());
            columna[2] = p.getCuenta().getNombre();
            columna[3] = p.getPago().getNombre();
            columna[4] = formateador.format(p.getValor());
            modelo.addRow(columna);
        }
        pg.tblPagosRealizados.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pg.tblPagosRealizados.setRowSorter(ordenar);
        pg.tblPagosRealizados.getColumnModel().getColumn(0).setMaxWidth(0);
        pg.tblPagosRealizados.getColumnModel().getColumn(0).setMinWidth(0);
        pg.tblPagosRealizados.getColumnModel().getColumn(0).setPreferredWidth(0);
        pg.tblPagosRealizados.setModel(modelo);
    }

    private void cargarEmpresas() {
        try {
            empresasdao = new EmpresasDAO();
            String Titulos[] = {"id_empresa", "Razon", "Direccion", "Telefono"};
            modelo = new DefaultTableModel(null, Titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                    return false;
                }
            };
            Object[] columna = new Object[4];
            Iterator<Empresas> nombreIterator = empresasdao.getEmpresasList().iterator();
            while (nombreIterator.hasNext()) {
                Empresas p = nombreIterator.next();
                columna[0] = p.getIdEmpresa();
                columna[1] = p.getNombreRazon();
                columna[2] = p.getDireccion();
                columna[3] = p.getTelefono();
                modelo.addRow(columna);
            }
            emp.tblEmpresas.setModel(modelo);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
            emp.tblEmpresas.setRowSorter(ordenar);
            emp.tblEmpresas.getColumnModel().getColumn(0).setMaxWidth(0);
            emp.tblEmpresas.getColumnModel().getColumn(0).setMinWidth(0);
            emp.tblEmpresas.getColumnModel().getColumn(0).setPreferredWidth(0);
            emp.tblEmpresas.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
    }

    private void ocultarModulos() {
        TestConection tc = new TestConection();
        Thread thread = new Thread(tc);
        thread.start();
//        pr.mnuFacturacion.setVisible(false);
        pr.mnuContactos.setVisible(false);
        pr.mnuGastos.setVisible(false);
        pr.mnuReportes.setVisible(false);
        pr.mnuInventarios.setVisible(false);
    }

    public int getIntentosLogin() {
        return intentosLogin;
    }

    public void setIntentosLogin(int intentosLogin) {
        this.intentosLogin = intentosLogin;
    }
}
