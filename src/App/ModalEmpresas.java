/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Model.Ciudades;

/**
 *
 * @author Mauricio Herrera
 */
public class ModalEmpresas extends javax.swing.JDialog {

    /**
     * Creates new form Empresas
     */
    public ModalEmpresas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupEmpresas = new javax.swing.JPopupMenu();
        editarLogo = new javax.swing.JMenuItem();
        imagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpresas = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        empresa = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        web = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        idEmpresa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        cboCiudad = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ajustarpeso = new javax.swing.JCheckBox();
        regimen = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();

        editarLogo.setText("Cambiar Logo");
        popupEmpresas.add(editarLogo);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empresas");
        setPreferredSize(new java.awt.Dimension(573, 409));
        setResizable(false);
        setSize(new java.awt.Dimension(573, 409));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        imagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 11, 163, 123));

        tblEmpresas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEmpresas.setComponentPopupMenu(popupEmpresas);
        jScrollPane1.setViewportView(tblEmpresas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 180, 535, 192));

        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 75, -1));

        empresa.setPreferredSize(new java.awt.Dimension(20, 20));
        getContentPane().add(empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 36, 300, 20));
        getContentPane().add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 111, 120, 20));
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 61, 120, -1));
        getContentPane().add(web, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 86, 300, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Dirección:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 10, 60, 20));
        getContentPane().add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 120, 20));
        getContentPane().add(idEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 11, 120, 20));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Correo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 111, 60, 20));

        jTextField7.setPreferredSize(new java.awt.Dimension(20, 20));
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 36, 300, -1));

        getContentPane().add(cboCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 111, 120, 20));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Nit:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, 60, 20));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Empresa:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 36, 60, 20));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Telefono:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 61, 60, 20));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Sitio Web:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 86, 60, 20));
        getContentPane().add(celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 61, 120, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Celular:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 61, 60, 20));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Ciudad:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 111, 60, 20));

        ajustarpeso.setText("Ajustar Peso");
        getContentPane().add(ajustarpeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        regimen.setText("Regimen Comun");
        getContentPane().add(regimen, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 140, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 75, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModalEmpresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalEmpresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalEmpresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalEmpresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModalEmpresas dialog = new ModalEmpresas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox ajustarpeso;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox<Ciudades> cboCiudad;
    public javax.swing.JTextField celular;
    public javax.swing.JTextField correo;
    public javax.swing.JTextField direccion;
    public javax.swing.JMenuItem editarLogo;
    public javax.swing.JTextField empresa;
    public javax.swing.JTextField idEmpresa;
    public javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPopupMenu popupEmpresas;
    public javax.swing.JCheckBox regimen;
    public javax.swing.JTable tblEmpresas;
    public javax.swing.JTextField telefono;
    public javax.swing.JTextField web;
    // End of variables declaration//GEN-END:variables
}
