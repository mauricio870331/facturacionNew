/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

public class getModalActualizarHospedaje {

    public static ModalActualizarHospedaje ah = null;

    public static ModalActualizarHospedaje getModalActualizarHospedaje() {
        try {
            if (ah == null) {
                ah = new ModalActualizarHospedaje(null, true);
                ah.setLocationRelativeTo(null);
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("Modal actualizar hospedaje ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return ah;
    }
}
