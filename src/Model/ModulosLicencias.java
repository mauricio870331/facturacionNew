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
public class ModulosLicencias {
    
    private int id;
    private Modulos modulo;
    private Licencias licencia;
    private String transacion;

    ModulosLicencias(int aInt) {        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modulos getModulo() {
        if (modulo == null) {
            modulo = new Modulos();
        }
        return modulo;
    }

    public void setModulo(Modulos modulo) {
        this.modulo = modulo;
    }

    public Licencias getLicencia() {
        if (licencia == null) {
            licencia = new Licencias();
        }
        return licencia;
    }

    public void setLicencia(Licencias licencia) {
        this.licencia = licencia;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public ModulosLicencias(int id, int id_modulo, String id_licencia, String transacion) {
        this.id = id;
        this.modulo = getModulo();
        this.modulo.setIdModulo(id_modulo);
        this.licencia = getLicencia();
        this.licencia.setIdLicencia(id_licencia);
        this.transacion = transacion;
    }    
        
}
