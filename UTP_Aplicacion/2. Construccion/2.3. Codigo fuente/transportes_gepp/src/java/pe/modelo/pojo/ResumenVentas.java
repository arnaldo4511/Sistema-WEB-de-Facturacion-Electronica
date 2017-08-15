package pe.modelo.pojo;
// Generated 15-ago-2017 17:21:31 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ResumenVentas generated by hbm2java
 */
@Entity
@Table(name="resumen_ventas"
    ,schema="ventas"
)
public class ResumenVentas  implements java.io.Serializable {


     private long id;
     private Usuario usuarioByIdUsuarioModificacion;
     private Usuario usuarioByIdUsuarioCreacion;
     private Empresa empresa;
     private EstadoDocumentoVenta estadoDocumentoVenta;
     private String tipo;
     private Date fechaEmision;
     private String numero;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private Set<ResumenVentasGrupo> resumenVentasGrupos = new HashSet<ResumenVentasGrupo>(0);

    public ResumenVentas() {
    }

	
    public ResumenVentas(long id, Usuario usuarioByIdUsuarioCreacion, Empresa empresa, EstadoDocumentoVenta estadoDocumentoVenta, String tipo, Date fechaEmision, String numero, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.empresa = empresa;
        this.estadoDocumentoVenta = estadoDocumentoVenta;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
    }
    public ResumenVentas(long id, Usuario usuarioByIdUsuarioModificacion, Usuario usuarioByIdUsuarioCreacion, Empresa empresa, EstadoDocumentoVenta estadoDocumentoVenta, String tipo, Date fechaEmision, String numero, Date fechaCreacion, Date fechaModificacion, Set<ResumenVentasGrupo> resumenVentasGrupos) {
       this.id = id;
       this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
       this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
       this.empresa = empresa;
       this.estadoDocumentoVenta = estadoDocumentoVenta;
       this.tipo = tipo;
       this.fechaEmision = fechaEmision;
       this.numero = numero;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
       this.resumenVentasGrupos = resumenVentasGrupos;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_modificacion")
    public Usuario getUsuarioByIdUsuarioModificacion() {
        return this.usuarioByIdUsuarioModificacion;
    }
    
    public void setUsuarioByIdUsuarioModificacion(Usuario usuarioByIdUsuarioModificacion) {
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_creacion", nullable=false)
    public Usuario getUsuarioByIdUsuarioCreacion() {
        return this.usuarioByIdUsuarioCreacion;
    }
    
    public void setUsuarioByIdUsuarioCreacion(Usuario usuarioByIdUsuarioCreacion) {
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_empresa", nullable=false)
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_estado_documento_venta", nullable=false)
    public EstadoDocumentoVenta getEstadoDocumentoVenta() {
        return this.estadoDocumentoVenta;
    }
    
    public void setEstadoDocumentoVenta(EstadoDocumentoVenta estadoDocumentoVenta) {
        this.estadoDocumentoVenta = estadoDocumentoVenta;
    }

    
    @Column(name="tipo", nullable=false, length=3)
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_emision", nullable=false, length=13)
    public Date getFechaEmision() {
        return this.fechaEmision;
    }
    
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    
    @Column(name="numero", nullable=false, length=8)
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", nullable=false, length=29)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_modificacion", length=29)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="resumenVentas")
    public Set<ResumenVentasGrupo> getResumenVentasGrupos() {
        return this.resumenVentasGrupos;
    }
    
    public void setResumenVentasGrupos(Set<ResumenVentasGrupo> resumenVentasGrupos) {
        this.resumenVentasGrupos = resumenVentasGrupos;
    }




}


