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
public class AppEmpresas {
    
    private int idAppVendedor;
    private String versionInstalada;
    private Licencias lic;
    private Empresas ven;    
    private String transacion;    

    AppEmpresas() {        
    }
    

    public int getIdAppVendedor() {
        return idAppVendedor;
    }

    public void setIdAppVendedor(int idAppVendedor) {
        this.idAppVendedor = idAppVendedor;
    }

    public String getVersionInstalada() {
        return versionInstalada;
    }

    public void setVersionInstalada(String versionInstalada) {
        this.versionInstalada = versionInstalada;
    }

    public Licencias getLic() {
        if (lic == null) {
           lic = new Licencias(); 
        }
        return lic;
    }

    public void setLic(Licencias lic) {
        this.lic = lic;
    }

    public Empresas getVen() {
        if (ven == null) {
            ven = new Empresas();
        }
        return ven;
    }

    public void setVen(Empresas ven) {
        this.ven = ven;
    }   


    public AppEmpresas(int idAppVendedor, String versionInstalada, String idLicencia, String idEmpresa, String transacion) {
        this.idAppVendedor = idAppVendedor;
        this.versionInstalada = versionInstalada;
        this.lic = getLic();
        this.lic.setIdLicencia(idLicencia);
        this.ven = getVen();
        this.ven.setIdEmpresa(idEmpresa);
        this.transacion = transacion;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    } 
    
       
    
}
