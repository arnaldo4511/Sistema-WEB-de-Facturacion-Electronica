package pe.modelo.pojo;
// Generated 17-ago-2017 14:14:43 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ResumenVentasGrupoVenta generated by hbm2java
 */
@Entity
@Table(name="resumen_ventas_grupo_venta"
    ,schema="ventas"
)
public class ResumenVentasGrupoVenta  implements java.io.Serializable {


     private long id;
     private Usuario usuarioByIdUsuarioCreacion;
     private Usuario usuarioByIdUsuarioModificacion;
     private DocumentoVenta documentoVenta;
     private ResumenVentasGrupo resumenVentasGrupo;
     private Date fechaCreacion;
     private Date fechaModificacion;

    public ResumenVentasGrupoVenta() {
    }

	
    public ResumenVentasGrupoVenta(long id, Usuario usuarioByIdUsuarioCreacion, DocumentoVenta documentoVenta, ResumenVentasGrupo resumenVentasGrupo, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.documentoVenta = documentoVenta;
        this.resumenVentasGrupo = resumenVentasGrupo;
        this.fechaCreacion = fechaCreacion;
    }
    public ResumenVentasGrupoVenta(long id, Usuario usuarioByIdUsuarioCreacion, Usuario usuarioByIdUsuarioModificacion, DocumentoVenta documentoVenta, ResumenVentasGrupo resumenVentasGrupo, Date fechaCreacion, Date fechaModificacion) {
       this.id = id;
       this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
       this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
       this.documentoVenta = documentoVenta;
       this.resumenVentasGrupo = resumenVentasGrupo;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
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
    @JoinColumn(name="id_usuario_creacion", nullable=false)
    public Usuario getUsuarioByIdUsuarioCreacion() {
        return this.usuarioByIdUsuarioCreacion;
    }
    
    public void setUsuarioByIdUsuarioCreacion(Usuario usuarioByIdUsuarioCreacion) {
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
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
    @JoinColumn(name="id_documento_venta", nullable=false)
    public DocumentoVenta getDocumentoVenta() {
        return this.documentoVenta;
    }
    
    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_resumen_ventas_grupo", nullable=false)
    public ResumenVentasGrupo getResumenVentasGrupo() {
        return this.resumenVentasGrupo;
    }
    
    public void setResumenVentasGrupo(ResumenVentasGrupo resumenVentasGrupo) {
        this.resumenVentasGrupo = resumenVentasGrupo;
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




}


