/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Innova
 */
public class ActividadesEmpresas {

    private int idActividadEmpresa;
    private Empresas ven;
    private ActividadesEconomicas ae;
    private String transacion;   
    
    
    public Empresas getVen() {
        if (ven == null) {
            ven = new Empresas();
        }
        return ven;
    }

    public ActividadesEconomicas getAe() {
        if (ae == null) {
            ae = new ActividadesEconomicas();
        }
        return ae;
    }

    public void setAe(ActividadesEconomicas ae) {
        this.ae = ae;
    }
    
     public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public ActividadesEmpresas(int idActividadEmpresa, String idEmpresa, int idActividadEconomica, String transacion) {
        this.idActividadEmpresa = idActividadEmpresa;
        this.ven = getVen();
        this.ven.setIdEmpresa(idEmpresa);        
        this.ae = getAe();
        this.ae.setIdActividadEconomica(idActividadEconomica);
        this.transacion = transacion;
    }

    public String getTransacion() {
        return transacion;
    }

    public int getIdActividadEmpresa() {
        return idActividadEmpresa;
    }

    public void setIdActividadEmpresa(int idActividadEmpresa) {
        this.idActividadEmpresa = idActividadEmpresa;
    }
   
}

