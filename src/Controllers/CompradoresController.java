/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import App.GetModalClientes;
import App.GetPrincipal;
import App.ModalClientes;
import App.Principal;
import Model.ClientesDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Innova
 */
public final class CompradoresController extends MouseAdapter implements ActionListener, KeyListener {

    private final Principal pr = GetPrincipal.getPrincipal();
    private final ModalClientes mc = GetModalClientes.getModalCliente();    
    ClientesDAO cdao = null;
    DefaultTableModel modelo = new DefaultTableModel();

    public CompradoresController() throws SQLException {     
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if (e.getSource() == mc.txtFindCliente) {
//            listarClientes(mc.txtFindCliente.getText());
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() == 2) {
////            int fila = mc.tbCompradores.getSelectedRow();
////            Principal.lblCliente.setText(mc.tbCompradores.getValueAt(fila, 0).toString() + " " + mc.tbCompradores.getValueAt(fila, 1).toString());
////            mc.dispose();
////            System.out.println("Se ha hecho doble click");
//        }
    }

//    private void listarClientes(String param) {
//        cdao = new ClientesDAO();
//        try {
//            String Titulos[] = {"Documento", "Nombre"};
//            modelo = new DefaultTableModel(null, Titulos) {
//                @Override
//                public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
//                    return false;
//                }
//            };
//            Object[] columna = new Object[2];
//            Iterator<Clientes> c = cdao.getClientesList(param).iterator();
//            while (c.hasNext()) {
//                Clientes next = c.next();
//                columna[0] = next.getIdCliente();
//                columna[1] = next.getNombreRazon();
//                modelo.addRow(columna);
//            }
//            mc.tbCompradores.setModel(modelo);
//
//        } catch (SQLException ex) {
//
//        }
//    }

//    public void cargarAsignaturas(JTable tbArea, String dato) throws SQLException {
//        String Titulos[] = {"Id", "Nombre"};
//        modelo = new DefaultTableModel(null, Titulos) {
//            @Override
//            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
//                return false;
//            }
//        };
//        Object[] columna = new Object[3];
//        Iterator<Asignaturas> nombreIterator = asignaturadao.getListAsignaturas(dato).iterator();
//        while (nombreIterator.hasNext()) {
//            Asignaturas a = nombreIterator.next();
//            columna[0] = a.getAsignatura();
//            columna[1] = a.getNombreAsignatura();
//            modelo.addRow(columna);
//        }
//        tbArea.setModel(modelo);
//        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
//        tbArea.setRowSorter(ordenar);
//        tbArea.getColumnModel().getColumn(0).setMaxWidth(0);
//        tbArea.getColumnModel().getColumn(0).setMinWidth(0);
//        tbArea.getColumnModel().getColumn(0).setPreferredWidth(0);
//        tbArea.setModel(modelo);
//    }
//    private void cargarCiudades() throws SQLException {
//        mc.cboCiudad.removeAllItems();
//        ciudadDao = new CiudadesDAO();
//        Iterator<Ciudades> c = ciudadDao.getCiudadesList().iterator();
//        while (c.hasNext()) {
//            Ciudades ciudad = c.next();
//            mc.cboCiudad.addItem(ciudad.getIdCiudad() + " - " + ciudad.getNombre());
//        }
//    }
}
