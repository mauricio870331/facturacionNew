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
public class IpPublicas {
    
    private int ip;
    private Empresas vendedor;
    private String ipPublica;
    private String transacion;

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
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

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public IpPublicas(int ip, String idEmpresa, String ipPublica, String transacion) {
        this.ip = ip;
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.ipPublica = ipPublica;
        this.transacion = transacion;
    }    
        
}
