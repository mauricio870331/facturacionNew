/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Adalberto Gomez
 */
public class FacturasAnuladas {
    
 private int idFacturaAnulada;
 private Facturas factura;
 private String concepto;
 private UsuariosApp usuario;
 private Date fecha;
 private String transacion;

    public int getIdFacturaAnulada() {
        return idFacturaAnulada;
    }

    public void setIdFacturaAnulada(int idFacturaAnulada) {
        this.idFacturaAnulada = idFacturaAnulada;
    }

    public Facturas getFactura() {
        if (factura == null) {
            factura = new Facturas();
        }
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public FacturasAnuladas(int idFacturaAnulada, String idFactura, String concepto, String idusuario, Date fecha, String transacion) {
        this.idFacturaAnulada = idFacturaAnulada;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.concepto = concepto;
        this.usuario = getUsuario();
        this.usuario.setIdUsuario(idusuario);
        this.fecha = fecha;
        this.transacion = transacion;
    } 
 
    
}
