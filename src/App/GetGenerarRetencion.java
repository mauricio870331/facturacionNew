/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Innova
 */
public class GetGenerarRetencion {

    public static GenerarRetencion gr = null;

    public static GenerarRetencion getGenerarRtencion() {
        try {
            if (gr == null) {
                gr = new GenerarRetencion(null, true);
//                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return gr;
    }
}
