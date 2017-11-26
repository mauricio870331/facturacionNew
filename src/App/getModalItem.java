/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;/**
 *
 * @author Mauricio Herrera
 */
public class getModalItem {

    public static ModalItem ps = null;

    public static ModalItem getModalItem() {
        try {
            if (ps == null) {
                ps = new ModalItem(null, true);
                ps.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return ps;
    }
}
