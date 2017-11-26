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
public class RecibosCaja {

    private int idReciboCaja;
    private Facturas factura;
    private int consecutivo;
    private Date fecha;
    private Empresas empresa;
    private Clientes cliente;
    private float valor;
    private String concepto;
    private String cheque;
    private TiposDePagos tipoPago;
    private String transacion;
    private Hospedajes hospedaje;

    RecibosCaja() {
        
    }

    public int getIdReciboCaja() {
        return idReciboCaja;
    }

    public void setIdReciboCaja(int idReciboCaja) {
        this.idReciboCaja = idReciboCaja;
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

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Clientes getCliente() {
        if (cliente == null) {
            cliente = new Clientes();
        }
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public TiposDePagos getTipoPago() {
        if (tipoPago == null) {
            tipoPago = new TiposDePagos();
        }
        return tipoPago;
    }

    public void setTipoPago(TiposDePagos tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public RecibosCaja(int idReciboCaja, String idFactura, int consecutivo, Date fecha, String idEmpresa, int idCliente, float valor, String concepto, String cheque, int idTipoPago, String transacion, int idHospedaje) {
        this.idReciboCaja = idReciboCaja;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.consecutivo = consecutivo;
        this.fecha = fecha;
        this.empresa = getEmpresa();
        this.empresa.setIdEmpresa(idEmpresa);
        this.cliente = getCliente();
        this.cliente.setIdCliente(idCliente);
        this.valor = valor;
        this.concepto = concepto;
        this.cheque = cheque;
        this.tipoPago = getTipoPago();
        this.tipoPago.setIdTipoPago(idTipoPago);
        this.transacion = transacion;
        this.hospedaje = getHospedaje();
        this.hospedaje.setIdHospedaje(idHospedaje);
    }

    public Hospedajes getHospedaje() {
        if (hospedaje == null) {
            hospedaje = new Hospedajes();
        }
        return hospedaje;
    }

    public void setHospedaje(Hospedajes hospedaje) {
        this.hospedaje = hospedaje;
    }
       

}
