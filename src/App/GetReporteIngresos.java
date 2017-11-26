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
public class GetReporteIngresos {

    public static ReporteIngresos ri = null;

    public static ReporteIngresos getReporteIngresos() {
        try {
            if (ri == null) {
                ri = new ReporteIngresos(null, false);
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return ri;
    }
}
