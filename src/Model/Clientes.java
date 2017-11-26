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
public class Clientes {

    private int idCliente;
    private String nombreRazon = "";
    private String identificacion = "";
    private String direccion;
    private Ciudades ciudad;
    private String correo;
    private String telefono1;
    private String telefono2;
    private String fax;
    private String celular;
    private boolean cliente;
    private boolean proveedor;
    private String observaciones;
    private Empresas vendedor;
    private TiposDeIdentificacion tipo;
    private String transacion;
    private Nacionalidades nacionalidad;
    private Date fechaNacimiento;    

    Clientes() {

    }

    public Clientes(int idCliente, String nombreRazon, String identificacion, String direccion, 
            int idCiudad, String correo, String telefono1,
            String telefono2, String fax, 
            String celular, boolean cliente, 
            boolean proveedor, String observaciones, String idEmpresa, int idTipoDeIdentificacion,
            String transacion, int idNacionalidad, Date fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombreRazon = nombreRazon;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.ciudad = getCiudad();
        this.ciudad.setIdCiudad(idCiudad);
        this.correo = correo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.fax = fax;
        this.celular = celular;
        this.cliente = cliente;
        this.proveedor = proveedor;
        this.observaciones = observaciones;
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.tipo = getTipo();
        this.tipo.setIdTipoIdentificacion(idTipoDeIdentificacion);
        this.transacion = transacion;
        this.nacionalidad = getNacionalidad();
        this.nacionalidad.setIdNacionalidad(idNacionalidad);       
        this.fechaNacimiento = fechaNacimiento;
       
    }

    public Clientes(String identificacion, String nombreRazon) {
        this.identificacion = identificacion;
        this.nombreRazon = nombreRazon;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreRazon() {
        return nombreRazon;
    }

    public void setNombreRazon(String nombreRazon) {
        this.nombreRazon = nombreRazon;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean isProveedor() {
        return proveedor;
    }

    public void setProveedor(boolean proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public TiposDeIdentificacion getTipo() {
        if (tipo == null) {
            tipo = new TiposDeIdentificacion();
        }
        return tipo;
    }

    public void setTipo(TiposDeIdentificacion tipo) {
        this.tipo = tipo;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Nacionalidades getNacionalidad() {
        if (nacionalidad == null) {
            nacionalidad = new Nacionalidades();
        }
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidades nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }  

}
