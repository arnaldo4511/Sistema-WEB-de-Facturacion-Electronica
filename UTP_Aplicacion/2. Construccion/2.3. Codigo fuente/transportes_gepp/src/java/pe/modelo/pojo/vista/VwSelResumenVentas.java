package pe.modelo.pojo.vista;
// Generated 29-ago-2017 18:25:42 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VwSelDocumentoVenta generated by hbm2java
 */
@Entity
@Table(name = "vw_sel_resumen_ventas",
        schema = "ventas"
)
public class VwSelResumenVentas implements java.io.Serializable {

    private Long id;
    private String tipo;
    private String codigoEstado;
    private String nombreEstado;
    private Date fechaEmision;
    private String numero;
    private Long idEmpresa;
    private String ticketSunat;
    private String estadoSunat;

    public VwSelResumenVentas() {
    }

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "tipo", insertable = false, updatable = false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = "codigo_estado", insertable = false, updatable = false)
    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    @Column(name = "nombre_estado", insertable = false, updatable = false)
    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @Column(name = "fecha_emision", insertable = false, updatable = false)
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Column(name = "numero", insertable = false, updatable = false)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "id_empresa", insertable = false, updatable = false)
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Column(name = "ticket_sunat", insertable = false, updatable = false)
    public String getTicketSunat() {
        return ticketSunat;
    }

    public void setTicketSunat(String ticketSunat) {
        this.ticketSunat = ticketSunat;
    }

    @Column(name = "estado_sunat", insertable = false, updatable = false)
    public String getEstadoSunat() {
        return estadoSunat;
    }

    public void setEstadoSunat(String estadoSunat) {
        this.estadoSunat = estadoSunat;
    }

}
