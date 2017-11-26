/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author soluc
 */
public class Logs {

    private int idLog;
    private Date fecha;
    private UsuariosApp usuario;
    private String actividad;
    private Empresas vendedor;
    private String transacion;

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuariosApp getUsuario() {
        if (usuario == null) {
            usuario = new UsuariosApp();
        }
        return usuario;
    }

    public void setUsuario(UsuariosApp usuario) {
        this.usuario = usuario;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Empresas getVendedor() {
        if (vendedor == null) {
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

    public Logs(int idLog, Date fecha, String idUsuario, String actividad, String idEmpresa, String transacion) {
        this.idLog = idLog;
        this.fecha = fecha;
        this.usuario = getUsuario();
        this.usuario.setIdUsuario(idUsuario);
        this.actividad = actividad;
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.transacion = transacion;
    }
        
}
