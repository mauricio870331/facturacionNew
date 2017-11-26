/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author soluc
 */
public class ConsecutivosImprimibles {
    
    private int idConsecutivo;
    private String prefijo;
    private int consecutivo;
    private TiposImprimibles imprimible;
    private Empresas vendedor;
    private String transacion;

    public int getIdConsecutivo() {
        return idConsecutivo;
    }

    public void setIdConsecutivo(int idConsecutivo) {
        this.idConsecutivo = idConsecutivo;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public TiposImprimibles getImprimible() {
        if (imprimible == null) {
            imprimible = new TiposImprimibles();
        }
        return imprimible;
    }

    public void setImprimible(TiposImprimibles imprimible) {
        this.imprimible = imprimible;
    }

    public Empresas getVendedor() {
        if (vendedor ==  null) {
            vendedor = new Empresas();
        }
        return vendedor;
    }

    public void setVendedor(Empresas vendedor) {
        this.vendedor = vendedor;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public ConsecutivosImprimibles(int idConsecutivo, String prefijo, int consecutivo, int idTipoImprimible, String idEmpresa, String transacion) {
        this.idConsecutivo = idConsecutivo;
        this.prefijo = prefijo;
        this.consecutivo = consecutivo;
        this.imprimible = getImprimible();
        this.imprimible.setIdTipoImprimible(idTipoImprimible);
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.transacion = transacion;
    }    
    
    
}
