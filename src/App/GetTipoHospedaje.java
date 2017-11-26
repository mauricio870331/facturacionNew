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
public class GetTipoHospedaje {

    public static SelectTipoHospedaje st = null;

    public static SelectTipoHospedaje getModalTipoHospedaje() {
        try {
            if (st == null) {
                st = new SelectTipoHospedaje(null, true);
                st.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return st;
    }
}
