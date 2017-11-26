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
public class ComprobantesEgreso {
    
    private int idComprobanteEgreso;
    private int consecutivo;
    private Ciudades ciudad;
    private Date fecha;
    private float valor;
    private String concepto;
    private String beneficiario;
    private Empresas vendedor;
    private TiposDePagos pago;
    private EntidadesBancarias entidad;
    private String cheque;
    private String transacion;

    ComprobantesEgreso() {
       
    }

    public int getIdComprobanteEgreso() {
        return idComprobanteEgreso;
    }

    public void setIdComprobanteEgreso(int idComprobanteEgreso) {
        this.idComprobanteEgreso = idComprobanteEgreso;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Ciudades getCiudad() {
        if (ciudad == null) {
            ciudad = new Ciudades();
        }
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
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

    public TiposDePagos getPago() {
        if (pago == null) {
            pago = new TiposDePagos();
        }
        return pago;
    }

    public void setPago(TiposDePagos pago) {
        this.pago = pago;
    }

    public EntidadesBancarias getEntidad() {
        if (entidad == null) {
            entidad = new EntidadesBancarias();
        }
        return entidad;
    }

    public void setEntidad(EntidadesBancarias entidad) {
        this.entidad = entidad;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public ComprobantesEgreso(int idComprobanteEgreso, int consecutivo, int idCiudad, Date fecha, float valor, String concepto, String beneficiario, String idEmpresa, int idTipoPago, int idEntidadBancaria, String cheque, String transacion) {
        this.idComprobanteEgreso = idComprobanteEgreso;
        this.consecutivo = consecutivo;
        this.ciudad = getCiudad();
        this.ciudad.setIdCiudad(idCiudad);
        this.fecha = fecha;
        this.valor = valor;
        this.concepto = concepto;
        this.beneficiario = beneficiario;
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.pago = getPago();
        this.pago.setIdTipoPago(idTipoPago);
        this.entidad = getEntidad();
        this.entidad.setIdEntidadBancaria(idEntidadBancaria);
        this.cheque = cheque;
        this.transacion = transacion;
    }
    
    
    
    
    
    
    
}
