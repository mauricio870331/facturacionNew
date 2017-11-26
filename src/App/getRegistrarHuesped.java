package App;

public class getRegistrarHuesped {

    public static RegistrarHuesped rh = null;

    public static RegistrarHuesped getRegistrarHuesped() {
        try {
            if (rh == null) {
                rh = new RegistrarHuesped(null, true);
                rh.setLocationRelativeTo(null);                
//                System.out.println("pr fue instanceado");
            } else {
                System.out.println("lg ya estaba  instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return rh;
    }
}

