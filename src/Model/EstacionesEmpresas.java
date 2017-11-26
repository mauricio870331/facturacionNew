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
public class EstacionesEmpresas {
    
    private int id;
    private AppEmpresas app;
    private String IdentificadorEstacion;
    private String transacion;    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getIdentificadorEstacion() {
        return IdentificadorEstacion;
    }

    public void setIdentificadorEstacion(String IdentificadorEstacion) {
        this.IdentificadorEstacion = IdentificadorEstacion;
    }

    public EstacionesEmpresas(int id, int idAppVendedor, String IdentificadorEstacion, String transacion) {
        this.id = id;
        this.app = getApp();
        this.app.setIdAppVendedor(idAppVendedor);
        this.IdentificadorEstacion = IdentificadorEstacion;
        this.transacion = transacion;
    }

   
    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public AppEmpresas getApp() {
        if (app == null) {
            app = new AppEmpresas();            
        }
        return app;
    }

    public void setApp(AppEmpresas app) {
        this.app = app;
    }
    
    
}
