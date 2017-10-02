package pe.modelo.pojo.funcion;
// Generated 22-sep-2017 11:46:24 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FnConDocumentoVenta generated by hbm2java
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "FnConDocumentoVenta",
            query = "SELECT * FROM ventas.fn_con_documento_venta(:id_empresa,:anhio,:mes,:dia_inicio,:dia_fin,:inicio_correlativo)",
            //resultClass = FnConDocumentoVenta.class,
            resultSetMapping = "mapping"
    )
})
@SqlResultSetMapping(name = "mapping", entities = {
    @EntityResult(entityClass = FnConDocumentoVenta.class, fields = {
        @FieldResult(name = "numFila", column = "num_fila")
        ,@FieldResult(name = "orden", column = "orden")
        ,@FieldResult(name = "codigoTipoDocumentoVenta", column = "codigo_tipo_documento_venta")
        ,@FieldResult(name = "codigoEstadoDocumentoVenta", column = "codigo_estado_documento_venta")
        ,@FieldResult(name = "fechaEmision", column = "fecha_emision")
        ,@FieldResult(name = "serie", column = "serie")
        ,@FieldResult(name = "numero", column = "numero")
        ,@FieldResult(name = "campo", column = "campo")
        ,@FieldResult(name = "subDiario", column = "sub_diario")
        ,@FieldResult(name = "numeroComprobante", column = "numero_comprobante")
        ,@FieldResult(name = "fechaComprobante", column = "fecha_comprobante")
        ,@FieldResult(name = "codigoMoneda", column = "codigo_moneda")
        ,@FieldResult(name = "glosaPrincipal", column = "glosa_principal")
        ,@FieldResult(name = "tipoCambio", column = "tipo_cambio")
        ,@FieldResult(name = "tipoConversion", column = "tipo_conversion")
        ,@FieldResult(name = "flagConversion", column = "flag_conversion")
        ,@FieldResult(name = "fechaTipoCambio", column = "fecha_tipo_cambio")
        ,@FieldResult(name = "cuentaContable", column = "cuenta_contable")
        ,@FieldResult(name = "codigoAnexo", column = "codigo_anexo")
        ,@FieldResult(name = "codigoCentroCosto", column = "codigo_centro_costo")
        ,@FieldResult(name = "debeHaber", column = "debe_haber")
        ,@FieldResult(name = "importeOriginal", column = "importe_original")
        ,@FieldResult(name = "importeDolares", column = "importe_dolares")
        ,@FieldResult(name = "importeSoles", column = "importe_soles")
        ,@FieldResult(name = "tipDoc", column = "tip_doc")
        ,@FieldResult(name = "numDoc", column = "num_doc")
        ,@FieldResult(name = "fecDoc", column = "fec_doc")
        ,@FieldResult(name = "fecVenc", column = "fec_venc")
        ,@FieldResult(name = "codigoArea", column = "codigo_area")
        ,@FieldResult(name = "glosaDetalle", column = "glosa_detalle")
        ,@FieldResult(name = "codAnexoAuxiliar", column = "cod_anexo_auxiliar")
        ,@FieldResult(name = "medioPago", column = "medio_pago")
        ,@FieldResult(name = "tipDocRef", column = "tip_doc_ref")
        ,@FieldResult(name = "numDocRef", column = "num_doc_ref")
        ,@FieldResult(name = "fechaDocRef", column = "fecha_doc_ref")
        ,@FieldResult(name = "nroMaq", column = "nro_maq")
        ,@FieldResult(name = "baseImponibleRef", column = "base_imponible_ref")
        ,@FieldResult(name = "igvDocProvision", column = "igv_doc_provision")
        ,@FieldResult(name = "tipoRef", column = "tipo_ref")
        ,@FieldResult(name = "numSerieCaja", column = "num_serie_caja")
        ,@FieldResult(name = "fechaOp", column = "fecha_op")
        ,@FieldResult(name = "tipTasa", column = "tip_tasa")
        ,@FieldResult(name = "tasaDetPer", column = "tasa_det_per")
        ,@FieldResult(name = "importDetPerDol", column = "import_det_per_dol")
        ,@FieldResult(name = "importDetPerSol", column = "import_det_per_sol")
        ,@FieldResult(name = "tipCambioF", column = "tip_cambio_f")
        ,@FieldResult(name = "igvSCredFiscal", column = "igv_s_cred_fiscal")})})

@Table(name = "fn_con_documento_venta(1,2017,7,1,28,1)",
        schema = "ventas"
)
public class FnConDocumentoVenta implements java.io.Serializable {

    private long numFila;
    private Double baseImponibleRef;
    private String campo;
    private String codAnexoAuxiliar;
    private String codigoAnexo;
    private String codigoArea;
    private String codigoCentroCosto;
    private String codigoEstadoDocumentoVenta;
    private String codigoMoneda;
    private String codigoTipoDocumentoVenta;
    private String cuentaContable;
    private String debeHaber;
    private String fecDoc;
    private String fecVenc;
    private String fechaComprobante;
    private String fechaDocRef;
    private Date fechaEmision;
    private String fechaOp;
    private String fechaTipoCambio;
    private String flagConversion;
    private String glosaDetalle;
    private String glosaPrincipal;
    private Double igvDocProvision;
    private String igvSCredFiscal;
    private String importDetPerDol;
    private String importDetPerSol;
    private Double importeDolares;
    private Double importeOriginal;
    private Double importeSoles;
    private String medioPago;
    private String nroMaq;
    private String numDoc;
    private String numDocRef;
    private String numSerieCaja;
    private String numero;
    private String numeroComprobante;
    private Integer orden;
    private String serie;
    private String subDiario;
    private String tasaDetPer;
    private String tipCambioF;
    private String tipDoc;
    private String tipDocRef;
    private String tipTasa;
    private String tipoCambio;
    private String tipoConversion;
    private String tipoRef;

    public FnConDocumentoVenta() {
    }

    public FnConDocumentoVenta(long numFila) {
        this.numFila = numFila;
    }

    public FnConDocumentoVenta(long numFila, Double baseImponibleRef, String campo, String codAnexoAuxiliar, String codigoAnexo, String codigoArea, String codigoCentroCosto, String codigoEstadoDocumentoVenta, String codigoMoneda, String codigoTipoDocumentoVenta, String cuentaContable, String debeHaber, String fecDoc, String fecVenc, String fechaComprobante, String fechaDocRef, Date fechaEmision, String fechaOp, String fechaTipoCambio, String flagConversion, String glosaDetalle, String glosaPrincipal, Double igvDocProvision, String igvSCredFiscal, String importDetPerDol, String importDetPerSol, Double importeDolares, Double importeOriginal, Double importeSoles, String medioPago, String nroMaq, String numDoc, String numDocRef, String numSerieCaja, String numero, String numeroComprobante, Integer orden, String serie, String subDiario, String tasaDetPer, String tipCambioF, String tipDoc, String tipDocRef, String tipTasa, String tipoCambio, String tipoConversion, String tipoRef) {
        this.numFila = numFila;
        this.baseImponibleRef = baseImponibleRef;
        this.campo = campo;
        this.codAnexoAuxiliar = codAnexoAuxiliar;
        this.codigoAnexo = codigoAnexo;
        this.codigoArea = codigoArea;
        this.codigoCentroCosto = codigoCentroCosto;
        this.codigoEstadoDocumentoVenta = codigoEstadoDocumentoVenta;
        this.codigoMoneda = codigoMoneda;
        this.codigoTipoDocumentoVenta = codigoTipoDocumentoVenta;
        this.cuentaContable = cuentaContable;
        this.debeHaber = debeHaber;
        this.fecDoc = fecDoc;
        this.fecVenc = fecVenc;
        this.fechaComprobante = fechaComprobante;
        this.fechaDocRef = fechaDocRef;
        this.fechaEmision = fechaEmision;
        this.fechaOp = fechaOp;
        this.fechaTipoCambio = fechaTipoCambio;
        this.flagConversion = flagConversion;
        this.glosaDetalle = glosaDetalle;
        this.glosaPrincipal = glosaPrincipal;
        this.igvDocProvision = igvDocProvision;
        this.igvSCredFiscal = igvSCredFiscal;
        this.importDetPerDol = importDetPerDol;
        this.importDetPerSol = importDetPerSol;
        this.importeDolares = importeDolares;
        this.importeOriginal = importeOriginal;
        this.importeSoles = importeSoles;
        this.medioPago = medioPago;
        this.nroMaq = nroMaq;
        this.numDoc = numDoc;
        this.numDocRef = numDocRef;
        this.numSerieCaja = numSerieCaja;
        this.numero = numero;
        this.numeroComprobante = numeroComprobante;
        this.orden = orden;
        this.serie = serie;
        this.subDiario = subDiario;
        this.tasaDetPer = tasaDetPer;
        this.tipCambioF = tipCambioF;
        this.tipDoc = tipDoc;
        this.tipDocRef = tipDocRef;
        this.tipTasa = tipTasa;
        this.tipoCambio = tipoCambio;
        this.tipoConversion = tipoConversion;
        this.tipoRef = tipoRef;
    }

    @Id

    @Column(name = "num_fila", unique = true, nullable = false)
    public long getNumFila() {
        return this.numFila;
    }

    public void setNumFila(long numFila) {
        this.numFila = numFila;
    }

    @Column(name = "base_imponible_ref", precision = 17, scale = 17)
    public Double getBaseImponibleRef() {
        return this.baseImponibleRef;
    }

    public void setBaseImponibleRef(Double baseImponibleRef) {
        this.baseImponibleRef = baseImponibleRef;
    }

    @Column(name = "campo")
    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    @Column(name = "cod_anexo_auxiliar")
    public String getCodAnexoAuxiliar() {
        return this.codAnexoAuxiliar;
    }

    public void setCodAnexoAuxiliar(String codAnexoAuxiliar) {
        this.codAnexoAuxiliar = codAnexoAuxiliar;
    }

    @Column(name = "codigo_anexo")
    public String getCodigoAnexo() {
        return this.codigoAnexo;
    }

    public void setCodigoAnexo(String codigoAnexo) {
        this.codigoAnexo = codigoAnexo;
    }

    @Column(name = "codigo_area")
    public String getCodigoArea() {
        return this.codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    @Column(name = "codigo_centro_costo")
    public String getCodigoCentroCosto() {
        return this.codigoCentroCosto;
    }

    public void setCodigoCentroCosto(String codigoCentroCosto) {
        this.codigoCentroCosto = codigoCentroCosto;
    }

    @Column(name = "codigo_estado_documento_venta")
    public String getCodigoEstadoDocumentoVenta() {
        return this.codigoEstadoDocumentoVenta;
    }

    public void setCodigoEstadoDocumentoVenta(String codigoEstadoDocumentoVenta) {
        this.codigoEstadoDocumentoVenta = codigoEstadoDocumentoVenta;
    }

    @Column(name = "codigo_moneda")
    public String getCodigoMoneda() {
        return this.codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @Column(name = "codigo_tipo_documento_venta")
    public String getCodigoTipoDocumentoVenta() {
        return this.codigoTipoDocumentoVenta;
    }

    public void setCodigoTipoDocumentoVenta(String codigoTipoDocumentoVenta) {
        this.codigoTipoDocumentoVenta = codigoTipoDocumentoVenta;
    }

    @Column(name = "cuenta_contable")
    public String getCuentaContable() {
        return this.cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    @Column(name = "debe_haber")
    public String getDebeHaber() {
        return this.debeHaber;
    }

    public void setDebeHaber(String debeHaber) {
        this.debeHaber = debeHaber;
    }

    @Column(name = "fec_doc")
    public String getFecDoc() {
        return this.fecDoc;
    }

    public void setFecDoc(String fecDoc) {
        this.fecDoc = fecDoc;
    }

    @Column(name = "fec_venc")
    public String getFecVenc() {
        return this.fecVenc;
    }

    public void setFecVenc(String fecVenc) {
        this.fecVenc = fecVenc;
    }

    @Column(name = "fecha_comprobante")
    public String getFechaComprobante() {
        return this.fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    @Column(name = "fecha_doc_ref")
    public String getFechaDocRef() {
        return this.fechaDocRef;
    }

    public void setFechaDocRef(String fechaDocRef) {
        this.fechaDocRef = fechaDocRef;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_emision", length = 29)
    public Date getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Column(name = "fecha_op")
    public String getFechaOp() {
        return this.fechaOp;
    }

    public void setFechaOp(String fechaOp) {
        this.fechaOp = fechaOp;
    }

    @Column(name = "fecha_tipo_cambio")
    public String getFechaTipoCambio() {
        return this.fechaTipoCambio;
    }

    public void setFechaTipoCambio(String fechaTipoCambio) {
        this.fechaTipoCambio = fechaTipoCambio;
    }

    @Column(name = "flag_conversion")
    public String getFlagConversion() {
        return this.flagConversion;
    }

    public void setFlagConversion(String flagConversion) {
        this.flagConversion = flagConversion;
    }

    @Column(name = "glosa_detalle")
    public String getGlosaDetalle() {
        return this.glosaDetalle;
    }

    public void setGlosaDetalle(String glosaDetalle) {
        this.glosaDetalle = glosaDetalle;
    }

    @Column(name = "glosa_principal")
    public String getGlosaPrincipal() {
        return this.glosaPrincipal;
    }

    public void setGlosaPrincipal(String glosaPrincipal) {
        this.glosaPrincipal = glosaPrincipal;
    }

    @Column(name = "igv_doc_provision", precision = 17, scale = 17)
    public Double getIgvDocProvision() {
        return this.igvDocProvision;
    }

    public void setIgvDocProvision(Double igvDocProvision) {
        this.igvDocProvision = igvDocProvision;
    }

    @Column(name = "igv_s_cred_fiscal")
    public String getIgvSCredFiscal() {
        return this.igvSCredFiscal;
    }

    public void setIgvSCredFiscal(String igvSCredFiscal) {
        this.igvSCredFiscal = igvSCredFiscal;
    }

    @Column(name = "import_det_per_dol")
    public String getImportDetPerDol() {
        return this.importDetPerDol;
    }

    public void setImportDetPerDol(String importDetPerDol) {
        this.importDetPerDol = importDetPerDol;
    }

    @Column(name = "import_det_per_sol")
    public String getImportDetPerSol() {
        return this.importDetPerSol;
    }

    public void setImportDetPerSol(String importDetPerSol) {
        this.importDetPerSol = importDetPerSol;
    }

    @Column(name = "importe_dolares", precision = 17, scale = 17)
    public Double getImporteDolares() {
        return this.importeDolares;
    }

    public void setImporteDolares(Double importeDolares) {
        this.importeDolares = importeDolares;
    }

    @Column(name = "importe_original", precision = 17, scale = 17)
    public Double getImporteOriginal() {
        return this.importeOriginal;
    }

    public void setImporteOriginal(Double importeOriginal) {
        this.importeOriginal = importeOriginal;
    }

    @Column(name = "importe_soles", precision = 17, scale = 17)
    public Double getImporteSoles() {
        return this.importeSoles;
    }

    public void setImporteSoles(Double importeSoles) {
        this.importeSoles = importeSoles;
    }

    @Column(name = "medio_pago")
    public String getMedioPago() {
        return this.medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    @Column(name = "nro_maq")
    public String getNroMaq() {
        return this.nroMaq;
    }

    public void setNroMaq(String nroMaq) {
        this.nroMaq = nroMaq;
    }

    @Column(name = "num_doc")
    public String getNumDoc() {
        return this.numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    @Column(name = "num_doc_ref")
    public String getNumDocRef() {
        return this.numDocRef;
    }

    public void setNumDocRef(String numDocRef) {
        this.numDocRef = numDocRef;
    }

    @Column(name = "num_serie_caja")
    public String getNumSerieCaja() {
        return this.numSerieCaja;
    }

    public void setNumSerieCaja(String numSerieCaja) {
        this.numSerieCaja = numSerieCaja;
    }

    @Column(name = "numero")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "numero_comprobante")
    public String getNumeroComprobante() {
        return this.numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    @Column(name = "orden")
    public Integer getOrden() {
        return this.orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Column(name = "serie")
    public String getSerie() {
        return this.serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Column(name = "sub_diario")
    public String getSubDiario() {
        return this.subDiario;
    }

    public void setSubDiario(String subDiario) {
        this.subDiario = subDiario;
    }

    @Column(name = "tasa_det_per")
    public String getTasaDetPer() {
        return this.tasaDetPer;
    }

    public void setTasaDetPer(String tasaDetPer) {
        this.tasaDetPer = tasaDetPer;
    }

    @Column(name = "tip_cambio_f")
    public String getTipCambioF() {
        return this.tipCambioF;
    }

    public void setTipCambioF(String tipCambioF) {
        this.tipCambioF = tipCambioF;
    }

    @Column(name = "tip_doc")
    public String getTipDoc() {
        return this.tipDoc;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    @Column(name = "tip_doc_ref")
    public String getTipDocRef() {
        return this.tipDocRef;
    }

    public void setTipDocRef(String tipDocRef) {
        this.tipDocRef = tipDocRef;
    }

    @Column(name = "tip_tasa")
    public String getTipTasa() {
        return this.tipTasa;
    }

    public void setTipTasa(String tipTasa) {
        this.tipTasa = tipTasa;
    }

    @Column(name = "tipo_cambio")
    public String getTipoCambio() {
        return this.tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    @Column(name = "tipo_conversion")
    public String getTipoConversion() {
        return this.tipoConversion;
    }

    public void setTipoConversion(String tipoConversion) {
        this.tipoConversion = tipoConversion;
    }

    @Column(name = "tipo_ref")
    public String getTipoRef() {
        return this.tipoRef;
    }

    public void setTipoRef(String tipoRef) {
        this.tipoRef = tipoRef;
    }

}
