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
public class GetChecklist {
     public static CheckList ch = null;

    public static CheckList getCheck() {
        try {
            if (ch == null) {
                ch = new CheckList();
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return ch;
    }
}
