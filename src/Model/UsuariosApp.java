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
public final class UsuariosApp {

    private String idUsuario;
    private TiposDeIdentificacion tipoIdentiicacion;
    private Empresas vendedor;
    private Roles rol;
    private String password;
    private String nombres;
    private String apellidos;
    private EstadosUsuarios estadoUsuario;
    private String transacion;

    public UsuariosApp(String idUsuario, int idTipoIdentificacion, String idEmpresa, int idRol, String password, String nombres, String apellidos, int idEstadoUsuario, String transacion) {
        this.idUsuario = idUsuario;
        this.tipoIdentiicacion = getTipoIdentiicacion();
        this.tipoIdentiicacion.setIdTipoIdentificacion(idTipoIdentificacion);
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.rol = getRol();
        this.rol.setIdRol(idRol);
        this.estadoUsuario = getEstadoUsuario();
        this.estadoUsuario.setIdEstadoUsuario(idEstadoUsuario);
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.transacion = transacion;

    }

    UsuariosApp() {       
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public TiposDeIdentificacion getTipoIdentiicacion() {
        if (tipoIdentiicacion == null) {
            tipoIdentiicacion = new TiposDeIdentificacion();
        }
        return tipoIdentiicacion;
    }

    public void setTipoIdentiicacion(TiposDeIdentificacion tipoIdentiicacion) {
        this.tipoIdentiicacion = tipoIdentiicacion;
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

    public Roles getRol() {
        if (rol == null) {
            rol = new Roles();
        }
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public EstadosUsuarios getEstadoUsuario() {
        if (estadoUsuario == null) {
            estadoUsuario = new EstadosUsuarios();
        }
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadosUsuarios estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }    

}
