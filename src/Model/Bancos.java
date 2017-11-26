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
public class Bancos {
    
    private int idBanco;
    private Cuentas cuenta;
    private String nombreCuenta;
    private String numeroCuenta;
    private float saldo;
    private Date fecha;
    private String descripcion;
    private Empresas ven;
    private String transacion;

    public Bancos(int idBanco, int idCuenta, String nombreCuenta, String numeroCuenta, float saldo, Date fecha, String descripcion, String idEmpresa, String transacion) {
        this.idBanco = idBanco;
        this.cuenta = getCuenta();
        this.cuenta.setIdCuenta(idCuenta);
        this.nombreCuenta = nombreCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.ven = getVen();
        this.ven.setIdEmpresa(idEmpresa);        
        this.transacion = transacion;
    }      
    
    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public Cuentas getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuentas();
        }
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }
    
    
    
    
    
    
    
}
