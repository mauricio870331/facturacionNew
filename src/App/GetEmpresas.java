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
public class GetEmpresas {

    public static ModalEmpresas emp = null;

    public static ModalEmpresas getEmpresas() {
        try {
            if (emp == null) {
                emp = new ModalEmpresas(null, true);
//                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return emp;
    }
}
