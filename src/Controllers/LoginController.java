/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import App.CheckList;
import App.GenerarPagos;
import App.GenerarRetencion;
import App.GetChecklist;
import App.GetLogin;
import App.GetPrincipal;
import App.Login;
import App.ModalCambiarClave;
import App.ModalClientes;
import App.ModalItem;
import App.ModalNumeracion;
import App.ModalVincularUsuarios;
import App.Principal;
import App.RegistrarHuesped;
import App.ResolucionFacturacion;
import App.SelectTipoHospedaje;
import Model.ModulosLicenciasDAO;
import Model.UsuariosApp;
import Model.UsuariosAppDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Innova
 */
public final class LoginController implements ActionListener {

    Login lg;
    UsuariosApp ua;
    UsuariosAppDAO usersDao;
    ModulosLicenciasDAO mdLicencia;
    private final Principal pr = GetPrincipal.getPrincipal();
    private final CheckList ch = GetChecklist.getCheck();
    private int intentosLogin = 0;

    public LoginController() throws IOException, InterruptedException {
//        Utils.Utils.getIP();
//        Utils.Utils.getMac();
        lg = GetLogin.getLogin();
        lg.btnLogin.addActionListener(this);
        lg.btnCancelar.addActionListener(this);
        lg.txtUser.addActionListener(this);
        lg.txtPass.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lg.btnCancelar) {
            System.exit(0);
        }
        if (e.getSource() == lg.txtUser) {
            lg.txtUser.transferFocus();
        }
        if (e.getSource() == lg.txtPass) {
            lg.txtPass.transferFocus();
        }
        if (e.getSource() == lg.btnLogin) {
            try {
                usersDao = new UsuariosAppDAO();
                ua = usersDao.getUsuariosApp(lg.txtUser.getText(), Arrays.toString(lg.txtPass.getPassword()));
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
                    pr.setTitle("PymesApp Versión 0.6 compilación 05032017");
                    pr.setVisible(true);
                    pr.setLocationRelativeTo(null);
                    Principal.idVendedor.setText(ua.getVendedor().getIdEmpresa());
                    Principal.idUsuarioLog.setText(ua.getIdUsuario());
                    Principal.idVendedor.setVisible(false);
                    Principal.idUsuarioLog.setVisible(false);
                    pr.clVencimientomanual.setVisible(false);
                    Principal.IdResolucion.setVisible(false);
                    this.lg.dispose();
                    getPrincipalController.PrincipalController();
                    int rol = Utils.Utils.getRolbyIdentificacion(Principal.idUsuarioLog.getText());
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
                }
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }
        if (e.getSource() == ch.btnNext) {
            CheckList.pnWelcome.setVisible(false);
            CheckList.pnCheck.setVisible(true);
            if (Utils.Utils.comprobarConexion()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("error " + ex);
                }
                CheckList.lblConexion.setIcon(null);
                CheckList.lblConexion.setText("OK");
                CheckList.lblConexion.setForeground(Color.GREEN);
                CheckList.lblConexion.setBackground(Color.BLACK);
                CheckList.lblConexion.setOpaque(true);
                CheckList.lblModConexion.setIcon(null);
                CheckList.lblModConexion.setText("ONLINE");
                CheckList.lblModConexion.setForeground(Color.GREEN);
                CheckList.lblModConexion.setBackground(Color.BLACK);
                CheckList.lblModConexion.setOpaque(true);
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("error gatos" + ex);
                }
                CheckList.lblConexion.setIcon(null);
                CheckList.lblConexion.setText("¡Sin Conexión a Internet!");
                CheckList.lblConexion.setForeground(Color.red);
                CheckList.lblModConexion.setIcon(null);
                CheckList.lblModConexion.setText("OFFLINE");
                CheckList.lblModConexion.setForeground(Color.red);
            }
            try {
                if (Utils.Utils.readLicence().size() > 0) {
                    CheckList.lblLicencia.setIcon(null);
                    CheckList.lblLicencia.setText(Utils.Utils.readLicence().get(0));
                    CheckList.lblLicencia.setForeground(Color.GREEN);
                    CheckList.lblLicencia.setBackground(Color.BLACK);
                    CheckList.lblLicencia.setOpaque(true);
                } else {
                    CheckList.lblLicencia.setIcon(null);
                    CheckList.lblLicencia.setText("¡Sin Licencia!");
                    CheckList.lblLicencia.setForeground(Color.red);
                    ch.btnAddLicense.setText("+");
                }
            } catch (IOException ex) {
                System.out.println("error " + ex);
            }
        }
        if (e.getSource() == ch.btnAddLicense) {
        }
    }

    public int getIntentosLogin() {
        return intentosLogin;
    }

    public void setIntentosLogin(int intentosLogin) {
        this.intentosLogin = intentosLogin;
    }

    private void ocultarModulos() {
        pr.mnuFacturacion.setVisible(false);
        pr.mnuContactos.setVisible(false);
        pr.mnuGastos.setVisible(false);
        pr.mnuReportes.setVisible(false);
        pr.mnuInventarios.setVisible(false);
    }

    private void cerrarSesion() {
        ModalItem ps = null;
        Principal pr = null;
        ModalClientes mc = null;
        SelectTipoHospedaje st = null;
        ResolucionFacturacion rf = null;
        ModalVincularUsuarios vu = null;
        ModalNumeracion mn = null;
        ModalCambiarClave cc = null;
        RegistrarHuesped rh = null;
        GenerarPagos gp = null;
        GenerarRetencion gr = null;

    }

}
