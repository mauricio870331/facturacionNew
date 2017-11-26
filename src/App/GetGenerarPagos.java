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
public class GetGenerarPagos {

    public static GenerarPagos gp = null;

    public static GenerarPagos getGenerarPagos() {
        try {
            if (gp == null) {
                gp = new GenerarPagos(null, true);
//                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return gp;
    }
}
