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
import javax.persistence.UniqueConstraint;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name="producto"
    ,schema="almacen"
    , uniqueConstraints = @UniqueConstraint(columnNames="nombre") 
)
public class Producto  implements java.io.Serializable {


     private long id;
     private Usuario usuarioByIdUsuarioModificacion;
     private Usuario usuarioByIdUsuarioCreacion;
     private Unidad unidad;
     private Empresa empresa;
     private String nombre;
     private String descripcion;
     private double precioVenta;
     private double precioCompra;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private Set<DocumentoVentaDetalle> documentoVentaDetalles = new HashSet<DocumentoVentaDetalle>(0);

    public Producto() {
    }

	
    public Producto(long id, Usuario usuarioByIdUsuarioCreacion, Unidad unidad, String nombre, String descripcion, double precioVenta, double precioCompra, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.unidad = unidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.fechaCreacion = fechaCreacion;
    }
    public Producto(long id, Usuario usuarioByIdUsuarioModificacion, Usuario usuarioByIdUsuarioCreacion, Unidad unidad, Empresa empresa, String nombre, String descripcion, double precioVenta, double precioCompra, Date fechaCreacion, Date fechaModificacion, Set<DocumentoVentaDetalle> documentoVentaDetalles) {
       this.id = id;
       this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
       this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
       this.unidad = unidad;
       this.empresa = empresa;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.precioVenta = precioVenta;
       this.precioCompra = precioCompra;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
       this.documentoVentaDetalles = documentoVentaDetalles;
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
    @JoinColumn(name="codigo_unidad", nullable=false)
    public Unidad getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_empresa")
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    @Column(name="nombre", unique=true, nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="descripcion", nullable=false, length=250)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="precio_venta", nullable=false, precision=17, scale=17)
    public double getPrecioVenta() {
        return this.precioVenta;
    }
    
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    
    @Column(name="precio_compra", nullable=false, precision=17, scale=17)
    public double getPrecioCompra() {
        return this.precioCompra;
    }
    
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="producto")
    public Set<DocumentoVentaDetalle> getDocumentoVentaDetalles() {
        return this.documentoVentaDetalles;
    }
    
    public void setDocumentoVentaDetalles(Set<DocumentoVentaDetalle> documentoVentaDetalles) {
        this.documentoVentaDetalles = documentoVentaDetalles;
    }




}


