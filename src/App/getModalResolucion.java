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
public class getModalResolucion {

    public static ResolucionFacturacion mr = null;

    public static ResolucionFacturacion getModalResolucion() {
        try {
            if (mr == null) {
                mr = new ResolucionFacturacion(null, true);
                mr.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mr;
    }
}

