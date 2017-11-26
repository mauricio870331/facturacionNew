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
public class getPrincipalController {

    public static PrincipalController pc = null;

    public static PrincipalController PrincipalController() {
        try {
            if (pc == null) {
                pc = new PrincipalController();
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("PrincipalController ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return pc;
    }
}
