/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Adalberto Gomez
 */
public class RetencionesEmpresas {

    private int idRetencionEmpresa;
    private Retenciones retencion;
    private Empresas empresa;

    public int getIdRetencionEmpresa() {
        return idRetencionEmpresa;
    }

    public void setIdRetencionEmpresa(int idRetencionEmpresa) {
        this.idRetencionEmpresa = idRetencionEmpresa;
    }

    public Retenciones getRetencion() {
        if (retencion == null) {
            retencion = new Retenciones();
        }
        return retencion;
    }

    public void setRetencion(Retenciones retencion) {
        this.retencion = retencion;
    }

    public Empresas getEmpresa() {
        if (empresa == null) {
            empresa = new Empresas();
        }
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public RetencionesEmpresas(int idRetencionEmpresa, int idRetencion, String idEmpresa) {
        this.idRetencionEmpresa = idRetencionEmpresa;
        this.retencion = getRetencion();
        this.retencion.setIdRetencion(idRetencion);
        this.empresa = getEmpresa();
        this.empresa.setIdEmpresa(idEmpresa);
    }

}
