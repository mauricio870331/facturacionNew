/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.InputStream;

/**
 *
 * @author soluc
 */
public class ProductosServicios {

    private int idProductoServicio;
    private String nombre;
    private String referencia;
    private String descripcion;
    private float costoUnidad;
    private float precioVenta;
    private InputStream imagen;
    private String garantia;
    private UnidadesMedida unidad;
    private int stock;
    private boolean servicio;
    private String codigoBarra;
    private String transacion;
    private String patch;
    private Empresas empresa;

    public ProductosServicios(int idProductoServicio, String nombre, String referencia, String descripcion, float costoUnidad, float precioVenta, InputStream imagen, String garantia, int idUnidadMedida, int stock, boolean servicio, String codigoBarra, String transacion, String id_empresa) {
        this.idProductoServicio = idProductoServicio;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.costoUnidad = costoUnidad;
        this.precioVenta = precioVenta;
        this.imagen = imagen;
        this.garantia = garantia;
        this.unidad = getUnidad();
        this.unidad.setIdUnidadMedida(idUnidadMedida);
        this.stock = stock;
        this.servicio = servicio;
        this.codigoBarra = codigoBarra;
        this.transacion = transacion;
        this.empresa = getEmpresa();
        this.empresa.setIdEmpresa(id_empresa);
    }
    
    
    
    

    ProductosServicios() {

    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public int getIdProductoServicio() {
        return idProductoServicio;
    }

    public void setIdProductoServicio(int idProductoServicio) {
        this.idProductoServicio = idProductoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(float costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public UnidadesMedida getUnidad() {
        if (unidad == null) {
            unidad = new UnidadesMedida();
        }
        return unidad;
    }

    public void setUnidad(UnidadesMedida unidad) {
        this.unidad = unidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isServicio() {
        return servicio;
    }

    public void setServicio(boolean servicio) {
        this.servicio = servicio;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
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

}
