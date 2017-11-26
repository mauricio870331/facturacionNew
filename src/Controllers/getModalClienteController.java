package Controllers;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio Herrera
 */
public class getModalClienteController {

    public static ModalClienteController mcc = null;

    public static ModalClienteController ClienteController() {
        try {
            if (mcc == null) {
                mcc = new ModalClienteController();                               
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("Modal Cliente Controlller ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mcc;
    }
}
