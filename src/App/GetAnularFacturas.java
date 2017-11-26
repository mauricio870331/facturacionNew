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
public class GetAnularFacturas {

    public static AnularFacturas af = null;

    public static AnularFacturas getAnularFacturas() {
        try {
            if (af == null) {
                af = new AnularFacturas(null, true);
//                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return af;
    }
}
