package App;

public class getModalVincularUsuarios {

    public static ModalVincularUsuarios mvu = null;

    public static ModalVincularUsuarios getModalVincularUsuarios() {
        try {
            if (mvu == null) {
                mvu = new ModalVincularUsuarios(null, true);
                mvu.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mvu;
    }
}
