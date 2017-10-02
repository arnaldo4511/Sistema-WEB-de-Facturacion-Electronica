package pe.modelo.pojo;
// Generated 29-ago-2017 14:22:41 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pe.modelo.pojo.Cliente;
import pe.modelo.pojo.DocumentoVentaDetalle;
import pe.modelo.pojo.Empresa;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.Moneda;
import pe.modelo.pojo.PuntoVenta;
import pe.modelo.pojo.PuntoVentaSerie;
import pe.modelo.pojo.ResumenVentasGrupoVenta;
import pe.modelo.pojo.TipoDocumentoVenta;
import pe.modelo.pojo.Usuario;

/**
 * DocumentoVenta generated by hbm2java
 */
@Entity
@Table(name = "documento_venta",
        schema = "ventas"
)
public class DocumentoVenta implements java.io.Serializable {

    private long id;
    private Usuario usuarioByIdUsuarioModificacion;
    private Usuario usuarioByIdUsuarioCreacion;
    private Empresa empresa;
    private Moneda moneda;
    private Cliente cliente;
    private DocumentoVenta documentoVenta;
    private EstadoDocumentoVenta estadoDocumentoVenta;
    private PuntoVenta puntoVenta;
    private PuntoVentaSerie puntoVentaSerie;
    private TipoDocumentoVenta tipoDocumentoVenta;
    private TipoNotaCredito tipoNotaCredito;
    private TipoNotaDebito tipoNotaDebito;
    private String condicion;
    private String formaPago;
    private String banco;
    private String numeroPago;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String guiaRemision;
    private String numero;
    private double totalGratuito;
    private double totalDescuento;
    private double totalGrabado;
    private double totalIgv;
    private double total;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String tipoTargeta;
    private String totalLetras;
    private String guiaRemisionTransportista;
    private String placa;
    private String descripcionNota;
    private String codigoHash;
    private String codigoSunat;
    private String tipoProducto;
    @JsonIgnore
    private Set<ResumenVentasGrupoVenta> resumenVentasGrupoVentas = new HashSet<ResumenVentasGrupoVenta>(0);
    //@JsonIgnore
    private Set<DocumentoVentaDetalle> documentoVentaDetalles = new HashSet<DocumentoVentaDetalle>(0);
    @JsonIgnore
    private Set<DocumentoVenta> documentoVentas = new HashSet<DocumentoVenta>(0);

    public DocumentoVenta() {
    }

    public DocumentoVenta(long id, Usuario usuarioByIdUsuarioCreacion, Empresa empresa, Moneda moneda, EstadoDocumentoVenta estadoDocumentoVenta, PuntoVenta puntoVenta, TipoDocumentoVenta tipoDocumentoVenta, String condicion, String formaPago, Date fechaEmision, String numero, double totalGratuito, double totalDescuento, double totalGrabado, double totalIgv, double total, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.empresa = empresa;
        this.moneda = moneda;
        this.estadoDocumentoVenta = estadoDocumentoVenta;
        this.puntoVenta = puntoVenta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.condicion = condicion;
        this.formaPago = formaPago;
        this.fechaEmision = fechaEmision;
        this.numero = numero;
        this.totalGratuito = totalGratuito;
        this.totalDescuento = totalDescuento;
        this.totalGrabado = totalGrabado;
        this.totalIgv = totalIgv;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
    }

    public DocumentoVenta(long id, Usuario usuarioByIdUsuarioModificacion, Usuario usuarioByIdUsuarioCreacion, Empresa empresa, Moneda moneda, Cliente cliente, DocumentoVenta documentoVenta, EstadoDocumentoVenta estadoDocumentoVenta, PuntoVenta puntoVenta, PuntoVentaSerie puntoVentaSerie, TipoDocumentoVenta tipoDocumentoVenta, String condicion, String formaPago, String banco, String numeroPago, Date fechaEmision, Date fechaVencimiento, String guiaRemision, String numero, double totalGratuito, double totalDescuento, double totalGrabado, double totalIgv, double total, Date fechaCreacion, Date fechaModificacion, String tipoTargeta, String totalLetras, Set<ResumenVentasGrupoVenta> resumenVentasGrupoVentas, Set<DocumentoVentaDetalle> documentoVentaDetalles, Set<DocumentoVenta> documentoVentas, TipoNotaCredito tipoNotaCredito, TipoNotaDebito tipoNotaDebito) {
        this.id = id;
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.empresa = empresa;
        this.moneda = moneda;
        this.cliente = cliente;
        this.documentoVenta = documentoVenta;
        this.estadoDocumentoVenta = estadoDocumentoVenta;
        this.puntoVenta = puntoVenta;
        this.puntoVentaSerie = puntoVentaSerie;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.condicion = condicion;
        this.formaPago = formaPago;
        this.banco = banco;
        this.numeroPago = numeroPago;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.guiaRemision = guiaRemision;
        this.numero = numero;
        this.totalGratuito = totalGratuito;
        this.totalDescuento = totalDescuento;
        this.totalGrabado = totalGrabado;
        this.totalIgv = totalIgv;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.tipoTargeta = tipoTargeta;
        this.totalLetras = totalLetras;
        this.resumenVentasGrupoVentas = resumenVentasGrupoVentas;
        this.documentoVentaDetalles = documentoVentaDetalles;
        this.documentoVentas = documentoVentas;
        this.tipoNotaCredito = tipoNotaCredito;
        this.tipoNotaDebito = tipoNotaDebito;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_modificacion")
    public Usuario getUsuarioByIdUsuarioModificacion() {
        return this.usuarioByIdUsuarioModificacion;
    }

    public void setUsuarioByIdUsuarioModificacion(Usuario usuarioByIdUsuarioModificacion) {
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
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
    @JoinColumn(name = "id_empresa", nullable = false)
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_moneda", nullable = false)
    public Moneda getMoneda() {
        return this.moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_documento_venta_ref")
    public DocumentoVenta getDocumentoVenta() {
        return this.documentoVenta;
    }

    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_estado_documento_venta", nullable = false)
    public EstadoDocumentoVenta getEstadoDocumentoVenta() {
        return this.estadoDocumentoVenta;
    }

    public void setEstadoDocumentoVenta(EstadoDocumentoVenta estadoDocumentoVenta) {
        this.estadoDocumentoVenta = estadoDocumentoVenta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    public PuntoVenta getPuntoVenta() {
        return this.puntoVenta;
    }

    public void setPuntoVenta(PuntoVenta puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie")
    public PuntoVentaSerie getPuntoVentaSerie() {
        return this.puntoVentaSerie;
    }

    public void setPuntoVentaSerie(PuntoVentaSerie puntoVentaSerie) {
        this.puntoVentaSerie = puntoVentaSerie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_documento_venta", nullable = false)
    public TipoDocumentoVenta getTipoDocumentoVenta() {
        return this.tipoDocumentoVenta;
    }

    public void setTipoDocumentoVenta(TipoDocumentoVenta tipoDocumentoVenta) {
        this.tipoDocumentoVenta = tipoDocumentoVenta;
    }

    @Column(name = "condicion", nullable = false, length = 3)
    public String getCondicion() {
        return this.condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Column(name = "forma_pago", nullable = false, length = 3)
    public String getFormaPago() {
        return this.formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Column(name = "banco", length = 3)
    public String getBanco() {
        return this.banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Column(name = "numero_pago", length = 20)
    public String getNumeroPago() {
        return this.numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision", nullable = false, length = 13)
    public Date getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vencimiento", length = 13)
    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Column(name = "guia_remision", length = 20)
    public String getGuiaRemision() {
        return this.guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    @Column(name = "numero", length = 8)
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "total_gratuito", nullable = false, precision = 17, scale = 17)
    public double getTotalGratuito() {
        return this.totalGratuito;
    }

    public void setTotalGratuito(double totalGratuito) {
        this.totalGratuito = totalGratuito;
    }

    @Column(name = "total_descuento", nullable = false, precision = 17, scale = 17)
    public double getTotalDescuento() {
        return this.totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    @Column(name = "total_grabado", nullable = false, precision = 17, scale = 17)
    public double getTotalGrabado() {
        return this.totalGrabado;
    }

    public void setTotalGrabado(double totalGrabado) {
        this.totalGrabado = totalGrabado;
    }

    @Column(name = "total_igv", nullable = false, precision = 17, scale = 17)
    public double getTotalIgv() {
        return this.totalIgv;
    }

    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }

    @Column(name = "total", nullable = false, precision = 17, scale = 17)
    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    @Column(name = "tipo_targeta", length = 3)
    public String getTipoTargeta() {
        return this.tipoTargeta;
    }

    public void setTipoTargeta(String tipoTargeta) {
        this.tipoTargeta = tipoTargeta;
    }

    @Column(name = "total_letras")
    public String getTotalLetras() {
        return this.totalLetras;
    }

    public void setTotalLetras(String totalLetras) {
        this.totalLetras = totalLetras;
    }

    @Column(name = "guia_remision_transportista")
    public String getGuiaRemisionTransportista() {
        return this.guiaRemisionTransportista;
    }

    public void setGuiaRemisionTransportista(String guiaRemisionTransportista) {
        this.guiaRemisionTransportista = guiaRemisionTransportista;
    }

    @Column(name = "placa")
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Column(name = "descripcion_nota")
    public String getDescripcionNota() {
        return this.descripcionNota;
    }

    public void setDescripcionNota(String descripcionNota) {
        this.descripcionNota = descripcionNota;
    }

    @Column(name = "codigo_hash")
    public String getCodigoHash() {
        return this.codigoHash;
    }

    public void setCodigoHash(String codigoHash) {
        this.codigoHash = codigoHash;
    }

    @Column(name = "codigo_sunat")
    public String getCodigoSunat() {
        return this.codigoSunat;
    }

    public void setCodigoSunat(String codigoSunat) {
        this.codigoSunat = codigoSunat;
    }

    @Column(name = "tipo_producto")
    public String getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_nota_credito")
    public TipoNotaCredito getTipoNotaCredito() {
        return tipoNotaCredito;
    }

    public void setTipoNotaCredito(TipoNotaCredito tipoNotaCredito) {
        this.tipoNotaCredito = tipoNotaCredito;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_nota_debito")
    public TipoNotaDebito getTipoNotaDebito() {
        return tipoNotaDebito;
    }

    public void setTipoNotaDebito(TipoNotaDebito tipoNotaDebito) {
        this.tipoNotaDebito = tipoNotaDebito;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentoVenta")
    public Set<ResumenVentasGrupoVenta> getResumenVentasGrupoVentas() {
        return this.resumenVentasGrupoVentas;
    }

    public void setResumenVentasGrupoVentas(Set<ResumenVentasGrupoVenta> resumenVentasGrupoVentas) {
        this.resumenVentasGrupoVentas = resumenVentasGrupoVentas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentoVenta")
    public Set<DocumentoVentaDetalle> getDocumentoVentaDetalles() {
        return this.documentoVentaDetalles;
    }

    public void setDocumentoVentaDetalles(Set<DocumentoVentaDetalle> documentoVentaDetalles) {
        this.documentoVentaDetalles = documentoVentaDetalles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentoVenta")
    public Set<DocumentoVenta> getDocumentoVentas() {
        return this.documentoVentas;
    }

    public void setDocumentoVentas(Set<DocumentoVenta> documentoVentas) {
        this.documentoVentas = documentoVentas;
    }
}
