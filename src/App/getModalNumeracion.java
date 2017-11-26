
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
public class getModalNumeracion {

    public static ModalNumeracion mn = null;

    public static ModalNumeracion getModalNumeracion() {
        try {
            if (mn == null) {
                mn = new ModalNumeracion(null, true);
                mn.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mn;
    }
}