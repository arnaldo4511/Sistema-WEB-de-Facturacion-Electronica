package pe.modelo.pojo;
// Generated 17-ago-2017 14:14:43 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.UniqueConstraint;

/**
 * EstadoDocumentoVenta generated by hbm2java
 */
@Entity
@Table(name = "estado_documento_venta",
        schema = "ventas",
        uniqueConstraints = @UniqueConstraint(columnNames = "nombre")
)
public class EstadoDocumentoVenta implements java.io.Serializable {

    private String codigo;
    @JsonIgnore
    private Usuario usuarioByIdUsuarioCreacion;
    @JsonIgnore
    private Usuario usuarioByIdUsuarioModificacion;
    private String nombre;
    private Date fechaCreacion;
    private Date fechaModificacion;
    @JsonIgnore
    private Set<DocumentoVenta> documentoVentas = new HashSet<DocumentoVenta>(0);
    @JsonIgnore
    private Set<ResumenVentas> resumenVentases = new HashSet<ResumenVentas>(0);

    public EstadoDocumentoVenta() {
    }

    public EstadoDocumentoVenta(String codigo, Usuario usuarioByIdUsuarioCreacion, String nombre, Date fechaCreacion) {
        this.codigo = codigo;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoDocumentoVenta(String codigo, Usuario usuarioByIdUsuarioCreacion, Usuario usuarioByIdUsuarioModificacion, String nombre, Date fechaCreacion, Date fechaModificacion, Set<DocumentoVenta> documentoVentas, Set<ResumenVentas> resumenVentases) {
        this.codigo = codigo;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.documentoVentas = documentoVentas;
        this.resumenVentases = resumenVentases;
    }

    @Id

    @Column(name = "codigo", unique = true, nullable = false, length = 3)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_creacion", nullable = false)
    public Usuario getUsuarioByIdUsuarioCreacion() {
        return this.usuarioByIdUsuarioCreacion;
    }

    public void setUsuarioByIdUsuarioCreacion(Usuario usuarioByIdUsuarioCreacion) {
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_modificacion")
    public Usuario getUsuarioByIdUsuarioModificacion() {
        return this.usuarioByIdUsuarioModificacion;
    }

    public void setUsuarioByIdUsuarioModificacion(Usuario usuarioByIdUsuarioModificacion) {
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
    }

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false, length = 29)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion", length = 29)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoDocumentoVenta")
    public Set<DocumentoVenta> getDocumentoVentas() {
        return this.documentoVentas;
    }

    public void setDocumentoVentas(Set<DocumentoVenta> documentoVentas) {
        this.documentoVentas = documentoVentas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoDocumentoVenta")
    public Set<ResumenVentas> getResumenVentases() {
        return this.resumenVentases;
    }

    public void setResumenVentases(Set<ResumenVentas> resumenVentases) {
        this.resumenVentases = resumenVentases;
    }

}
