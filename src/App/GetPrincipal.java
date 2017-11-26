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
public class GetPrincipal {

    public static Principal pr = null;

    public static Principal getPrincipal() {
        try {
            if (pr == null) {
                pr = new Principal();
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return pr;
    }
}
