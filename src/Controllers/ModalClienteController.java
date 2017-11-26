/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import App.GetModalClientes;
import App.ModalClientes;
import App.Principal;
import App.RegistrarHuesped;
import App.getRegistrarHuesped;
import Model.ClientesDAO;
import Utils.Utils;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.CheckBox;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Mauricio Herrera
 */
public final class ModalClienteController implements ActionListener, FocusListener {

    private final RegistrarHuesped rh = getRegistrarHuesped.getRegistrarHuesped();
    private final ModalClientes mc = GetModalClientes.getModalCliente();
    ClientesDAO compradoresdao = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Object[] componentes = null;

    public ModalClienteController() {
        mc.btnGuardarComprador.addActionListener(this);
        mc.cboTipoId.addActionListener(this);
        Object[] letras = {mc.txtNombreRazon};
        Object[] numeros = {mc.txtCelular, mc.txtFax, mc.txtTelefono, mc.txtTelefono2};
        SLetra(letras, true);
        SLetra(numeros, false);
    }

    private String guardarComprador() {
        String response = "";
        int countErrors = 0;
        if (mc.cboNacionalidad.getSelectedItem().equals("Colombia - 52") || mc.cboNacionalidad.getSelectedItem().equals("-- Seleccione --")) {
            componentes = new Object[8];
            componentes[0] = mc.txtNombreRazon;
            componentes[1] = mc.cboTipoId;
            componentes[2] = mc.txtCelular;
            componentes[3] = mc.txtCorreo;
            componentes[4] = mc.txtDireccion;
            componentes[5] = mc.txtDocumento;
            componentes[6] = mc.cboNacionalidad;
            componentes[7] = mc.txtCiudad;            
        } else {
            componentes = new Object[5];
            componentes[0] = mc.txtNombreRazon;
            componentes[1] = mc.cboTipoId;
            componentes[2] = mc.txtDocumento;
            componentes[3] = mc.cboNacionalidad;
            componentes[4] = mc.cldNacimiento;
            mc.txtCiudad.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
            mc.txtDireccion.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
        }
        for (Object componente : componentes) {
            if (componente instanceof JTextField) {
                ((JTextField) componente).addFocusListener(this);
            }
            if (componente instanceof JComboBox) {
                ((JComboBox) componente).addFocusListener(this);
            }
            if (componente instanceof JDateChooser) {
                ((JDateChooser) componente).addFocusListener(this);
            }
        }
        for (Object componente : componentes) {
            if (componente instanceof JTextField) {
                boolean equals = ((JTextField) componente).getText().equals("");
                if (equals) {
                    countErrors++;
                    ((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));  //2C6791                  
                } else {
                    ((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
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
        }
        if (countErrors == 0) {
            try {
                String tipoDoc = mc.cboTipoId.getSelectedItem().toString();
                String[] parts = tipoDoc.split("-");
                String tipoNacionalidad = mc.cboNacionalidad.getSelectedItem().toString();
                String[] pais = tipoNacionalidad.split(" - ");
                int idCiudad = Utils.getIdCiudad((String) mc.txtCiudad.getSelectedItem());
                if (idCiudad == 0) {
                    idCiudad = 1116;
                }
                compradoresdao = new ClientesDAO();
                response = compradoresdao.crearCliente(mc.idupdate.getText(),
                        mc.txtNombreRazon.getText(), mc.txtDocumento.getText(),
                        mc.txtDireccion.getText(), idCiudad, mc.txtCorreo.getText(),
                        mc.txtTelefono.getText(), mc.txtTelefono2.getText(),
                        mc.txtFax.getText(), mc.txtCelular.getText(),
                        mc.chkCliente.isSelected(), mc.chkProveedor.isSelected(), mc.txAreaObs.getText(),
                        Principal.idVendedor.getText(), Integer.parseInt(parts[0]),
                        Utils.getTransaccion(Principal.idVendedor.getText(), "clientes"),
                        Integer.parseInt(pais[1]), mc.cldNacimiento.getDate(),
                        (mc.idupdate.getText().equals("")) ? "create" : "update");
                getPrincipalController.PrincipalController().cargarClientes();
                Principal.lblCliente.setSelectedItem(mc.txtDocumento.getText() + " " + mc.txtNombreRazon.getText());
                rh.txtHuesped.setSelectedItem(mc.txtDocumento.getText() + " " + mc.txtNombreRazon.getText());
                Principal.lblCliente.setEditable(false);
                rh.txtHuesped.setEditable(false);
            } catch (SQLException e) {
                System.out.println("error 2" + e);
            }
        } else {
            response = "¡Hay campos vacíos!";
        }
        return response;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == mc.cboTipoId) {
            mc.txtDocumento.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
            mc.txtDocumento.setText("");
        }
        if (e.getSource() == mc.btnGuardarComprador) {
            if (mc.cboTipoId.getSelectedItem().equals("1-Cédula de Ciudadanía") || mc.cboTipoId.getSelectedItem().equals("3-NIT") || mc.cboTipoId.getSelectedItem().equals("--Seleccione--") || mc.cboTipoId.getSelectedItem() == null) {
                if (!mc.txtDocumento.getText().equals("")) {
                    if (!Utils.voucherValido(mc.txtDocumento.getText(), 10)) {
                        JOptionPane.showMessageDialog(null, "Documento no valido!");
                        mc.txtDocumento.setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));
                        return;
                    } else {
                        mc.txtDocumento.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
                    }
                }
            }
            if (!validateEmail(mc.txtCorreo.getText()) && !mc.txtCorreo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "¡Correo no valido!");
                mc.txtCorreo.setBorder(BorderFactory.createLineBorder(Color.decode("#EE1313")));
                return;
            } else {
                mc.txtCorreo.setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));
            }
            String r = guardarComprador();
            JOptionPane.showMessageDialog(null, r);
            if (!r.equals("¡Hay campos vacíos!")) {
                Object[] campos = {mc.txtCelular, mc.txtCiudad, mc.txtCorreo, mc.txtDireccion, mc.txtDocumento, mc.txtFax, mc.txtNombreRazon,
                    mc.txtTelefono, mc.txtTelefono2, mc.cboTipoId, mc.chkProveedor, mc.cboNacionalidad, mc.txAreaObs, mc.idupdate, mc.cldNacimiento};
                cleanCampos(campos);
                mc.dispose();
            }
            try {
                getPrincipalController.PrincipalController().cargarUsuarios("", "");
            } catch (SQLException ex) {
                System.out.println("error " + ex);
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

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        for (int i = 0; i < componentes.length; i++) {
            if (e.getSource() == componentes[i]) {
                if (componentes[i] instanceof JTextField) {
                    ((JTextField) componentes[i]).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));  //2C6791 
                } else {
                    ((JComboBox) componentes[i]).setBorder(BorderFactory.createLineBorder(Color.decode("#848484")));  //2C6791 
                }
            }
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

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
