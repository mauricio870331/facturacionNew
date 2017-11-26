
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
public class getModalCambiarClave {

    public static ModalCambiarClave cc = null;

    public static ModalCambiarClave getModalCambiarClave() {
        try {
            if (cc == null) {
                cc = new ModalCambiarClave(null, true);
                cc.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return cc;
    }
}
