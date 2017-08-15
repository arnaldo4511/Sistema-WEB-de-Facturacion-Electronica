package pe.modelo.pojo;
// Generated 15-ago-2017 17:21:31 by Hibernate Tools 4.3.1


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
 * DocumentoVentaDetalle generated by hbm2java
 */
@Entity
@Table(name="documento_venta_detalle"
    ,schema="ventas"
)
public class DocumentoVentaDetalle  implements java.io.Serializable {


     private long id;
     private Usuario usuarioByIdUsuarioModificacion;
     private Usuario usuarioByIdUsuarioCreacion;
     private Producto producto;
     private Unidad unidad;
     private DocumentoVenta documentoVenta;
     private double precioUnitario;
     private double descuentoUnitario;
     private double fleteUnitario;
     private double bonificacionUnitario;
     private double precioFinal;
     private double cantidad;
     private boolean descuento;
     private boolean gratuito;
     private double totalDescuento;
     private double totalGrabado;
     private double totalIgv;
     private double total;
     private Date fechaCreacion;
     private Date fechaModificacion;

    public DocumentoVentaDetalle() {
    }

	
    public DocumentoVentaDetalle(long id, Usuario usuarioByIdUsuarioCreacion, DocumentoVenta documentoVenta, double precioUnitario, double descuentoUnitario, double fleteUnitario, double bonificacionUnitario, double precioFinal, double cantidad, boolean descuento, boolean gratuito, double totalDescuento, double totalGrabado, double totalIgv, double total, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.documentoVenta = documentoVenta;
        this.precioUnitario = precioUnitario;
        this.descuentoUnitario = descuentoUnitario;
        this.fleteUnitario = fleteUnitario;
        this.bonificacionUnitario = bonificacionUnitario;
        this.precioFinal = precioFinal;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.gratuito = gratuito;
        this.totalDescuento = totalDescuento;
        this.totalGrabado = totalGrabado;
        this.totalIgv = totalIgv;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
    }
    public DocumentoVentaDetalle(long id, Usuario usuarioByIdUsuarioModificacion, Usuario usuarioByIdUsuarioCreacion, Producto producto, Unidad unidad, DocumentoVenta documentoVenta, double precioUnitario, double descuentoUnitario, double fleteUnitario, double bonificacionUnitario, double precioFinal, double cantidad, boolean descuento, boolean gratuito, double totalDescuento, double totalGrabado, double totalIgv, double total, Date fechaCreacion, Date fechaModificacion) {
       this.id = id;
       this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
       this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
       this.producto = producto;
       this.unidad = unidad;
       this.documentoVenta = documentoVenta;
       this.precioUnitario = precioUnitario;
       this.descuentoUnitario = descuentoUnitario;
       this.fleteUnitario = fleteUnitario;
       this.bonificacionUnitario = bonificacionUnitario;
       this.precioFinal = precioFinal;
       this.cantidad = cantidad;
       this.descuento = descuento;
       this.gratuito = gratuito;
       this.totalDescuento = totalDescuento;
       this.totalGrabado = totalGrabado;
       this.totalIgv = totalIgv;
       this.total = total;
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
    @JoinColumn(name="id_producto")
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_unidad")
    public Unidad getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_documento_venta", nullable=false)
    public DocumentoVenta getDocumentoVenta() {
        return this.documentoVenta;
    }
    
    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

    
    @Column(name="precio_unitario", nullable=false, precision=17, scale=17)
    public double getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    
    @Column(name="descuento_unitario", nullable=false, precision=17, scale=17)
    public double getDescuentoUnitario() {
        return this.descuentoUnitario;
    }
    
    public void setDescuentoUnitario(double descuentoUnitario) {
        this.descuentoUnitario = descuentoUnitario;
    }

    
    @Column(name="flete_unitario", nullable=false, precision=17, scale=17)
    public double getFleteUnitario() {
        return this.fleteUnitario;
    }
    
    public void setFleteUnitario(double fleteUnitario) {
        this.fleteUnitario = fleteUnitario;
    }

    
    @Column(name="bonificacion_unitario", nullable=false, precision=17, scale=17)
    public double getBonificacionUnitario() {
        return this.bonificacionUnitario;
    }
    
    public void setBonificacionUnitario(double bonificacionUnitario) {
        this.bonificacionUnitario = bonificacionUnitario;
    }

    
    @Column(name="precio_final", nullable=false, precision=17, scale=17)
    public double getPrecioFinal() {
        return this.precioFinal;
    }
    
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    
    @Column(name="cantidad", nullable=false, precision=17, scale=17)
    public double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    
    @Column(name="descuento", nullable=false)
    public boolean isDescuento() {
        return this.descuento;
    }
    
    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    
    @Column(name="gratuito", nullable=false)
    public boolean isGratuito() {
        return this.gratuito;
    }
    
    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    
    @Column(name="total_descuento", nullable=false, precision=17, scale=17)
    public double getTotalDescuento() {
        return this.totalDescuento;
    }
    
    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    
    @Column(name="total_grabado", nullable=false, precision=17, scale=17)
    public double getTotalGrabado() {
        return this.totalGrabado;
    }
    
    public void setTotalGrabado(double totalGrabado) {
        this.totalGrabado = totalGrabado;
    }

    
    @Column(name="total_igv", nullable=false, precision=17, scale=17)
    public double getTotalIgv() {
        return this.totalIgv;
    }
    
    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }

    
    @Column(name="total", nullable=false, precision=17, scale=17)
    public double getTotal() {
        return this.total;
    }
    
    public void setTotal(double total) {
        this.total = total;
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


