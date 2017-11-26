package App;

public class getModalTarifasHospedaje {

    public static ModalTarifasHospedaje mth = null;

    public static ModalTarifasHospedaje getModalTarifasHospedaje() {
        try {
            if (mth == null) {
                mth = new ModalTarifasHospedaje(null, true);
                mth.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("ModalTarifasUsuario ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mth;
    }
}
