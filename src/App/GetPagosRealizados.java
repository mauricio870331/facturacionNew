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
public class GetPagosRealizados {

    public static PagosRealizados pg = null;

    public static PagosRealizados getPagosRealizados() {
        try {
            if (pg == null) {
                pg = new PagosRealizados(null, true);
            }
        } catch (Exception e) {
            System.out.println("error = bb " + e);
        }
        return pg;
    }
}
