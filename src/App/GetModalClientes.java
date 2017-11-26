/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Mauricio Herrera
 */
public class GetModalClientes {

    public static ModalClientes mc = null;

    public static ModalClientes getModalCliente() {
        try {
            if (mc == null) {
                mc = new ModalClientes(null, true);
                mc.setLocationRelativeTo(null);
                mc.setTitle("Gestion de clientes");
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mc;
    }
}
