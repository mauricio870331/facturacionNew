package App;

/**
 *
 * @author Mauricio Herrera
 */
public class getModalReporteFacturacion {

    public static ModalReporteFacturacion rf = null;

    public static ModalReporteFacturacion getModalReporteFacturacion() {
        try {
            if (rf == null) {
                rf = new ModalReporteFacturacion(null, false);
                rf.setLocationRelativeTo(null);
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return rf;
    }
}
