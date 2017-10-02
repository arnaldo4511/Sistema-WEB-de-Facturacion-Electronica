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
            name = "FnVentaXPlacaAnual",
            query = "SELECT * FROM ventas.fn_venta_x_placa_anual(:par_id_empresa, :par_anhio)",
            //resultClass = FnConDocumentoVenta.class,
            resultSetMapping = "mappingFnVentaXPlacaAnual"
    )
})
@SqlResultSetMapping(name = "mappingFnVentaXPlacaAnual", entities = {
    @EntityResult(entityClass = FnVentaXPlacaAnual.class, fields = {
        @FieldResult(name = "numFila", column = "num_fila"),
        @FieldResult(name = "idEmpresa", column = "id_empresa"),
        @FieldResult(name = "anhio", column = "anhio"),
        @FieldResult(name = "nombreProducto", column = "nombre_producto"),
        @FieldResult(name = "placa", column = "placa"),
        @FieldResult(name = "totalMes1", column = "total_mes_1"),
        @FieldResult(name = "totalMes2", column = "total_mes_2"),
        @FieldResult(name = "totalMes3", column = "total_mes_3"),
        @FieldResult(name = "totalMes4", column = "total_mes_4"),
        @FieldResult(name = "totalMes5", column = "total_mes_5"),
        @FieldResult(name = "totalMes6", column = "total_mes_6"),
        @FieldResult(name = "totalMes7", column = "total_mes_7"),
        @FieldResult(name = "totalMes8", column = "total_mes_8"),
        @FieldResult(name = "totalMes9", column = "total_mes_9"),
        @FieldResult(name = "totalMes10", column = "total_mes_10"),
        @FieldResult(name = "totalMes11", column = "total_mes_11"),
        @FieldResult(name = "totalMes12", column = "total_mes_12"),
        @FieldResult(name = "totalMeses", column = "total_meses")})})

@Table(name = "fn_venta_x_placa_anual(1, 2017)",
        schema = "ventas"
)
public class FnVentaXPlacaAnual implements java.io.Serializable {

    private long numFila;
    private Long idEmpresa;
    private Integer anhio;
    private String nombreProducto;
    private String placa;
    private Double totalMes1;
    private Double totalMes2;
    private Double totalMes3;
    private Double totalMes4;
    private Double totalMes5;
    private Double totalMes6;
    private Double totalMes7;
    private Double totalMes8;
    private Double totalMes9;
    private Double totalMes10;
    private Double totalMes11;
    private Double totalMes12;
    private Double totalMeses;

    public FnVentaXPlacaAnual() {
    }

    @Id
    @Column(name = "num_Fila", insertable = false, updatable = false)
    public long getNumFila() {
        return numFila;
    }

    public void setNumFila(long numFila) {
        this.numFila = numFila;
    }

    @Column(name = "placa", insertable = false, updatable = false)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Column(name = "nombre_producto", insertable = false, updatable = false)
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Column(name = "id_empresa", insertable = false, updatable = false)
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Column(name = "anhio", insertable = false, updatable = false)
    public Integer getAnhio() {
        return anhio;
    }

    public void setAnhio(Integer anhio) {
        this.anhio = anhio;
    }

    @Column(name = "total_mes_1", insertable = false, updatable = false)
    public Double getTotalMes1() {
        return totalMes1;
    }

    public void setTotalMes1(Double totalMes1) {
        this.totalMes1 = totalMes1;
    }

    @Column(name = "total_mes_2", insertable = false, updatable = false)
    public Double getTotalMes2() {
        return totalMes2;
    }

    public void setTotalMes2(Double totalMes2) {
        this.totalMes2 = totalMes2;
    }

    @Column(name = "total_mes_3", insertable = false, updatable = false)
    public Double getTotalMes3() {
        return totalMes3;
    }

    public void setTotalMes3(Double totalMes3) {
        this.totalMes3 = totalMes3;
    }

    @Column(name = "total_mes_4", insertable = false, updatable = false)
    public Double getTotalMes4() {
        return totalMes4;
    }

    public void setTotalMes4(Double totalMes4) {
        this.totalMes4 = totalMes4;
    }

    @Column(name = "total_mes_5", insertable = false, updatable = false)
    public Double getTotalMes5() {
        return totalMes5;
    }

    public void setTotalMes5(Double totalMes5) {
        this.totalMes5 = totalMes5;
    }

    @Column(name = "total_mes_6", insertable = false, updatable = false)
    public Double getTotalMes6() {
        return totalMes6;
    }

    public void setTotalMes6(Double totalMes6) {
        this.totalMes6 = totalMes6;
    }

    @Column(name = "total_mes_7", insertable = false, updatable = false)
    public Double getTotalMes7() {
        return totalMes7;
    }

    public void setTotalMes7(Double totalMes7) {
        this.totalMes7 = totalMes7;
    }

    @Column(name = "total_mes_8", insertable = false, updatable = false)
    public Double getTotalMes8() {
        return totalMes8;
    }

    public void setTotalMes8(Double totalMes8) {
        this.totalMes8 = totalMes8;
    }

    @Column(name = "total_mes_9", insertable = false, updatable = false)
    public Double getTotalMes9() {
        return totalMes9;
    }

    public void setTotalMes9(Double totalMes9) {
        this.totalMes9 = totalMes9;
    }

    @Column(name = "total_mes_10", insertable = false, updatable = false)
    public Double getTotalMes10() {
        return totalMes10;
    }

    public void setTotalMes10(Double totalMes10) {
        this.totalMes10 = totalMes10;
    }

    @Column(name = "total_mes_11", insertable = false, updatable = false)
    public Double getTotalMes11() {
        return totalMes11;
    }

    public void setTotalMes11(Double totalMes11) {
        this.totalMes11 = totalMes11;
    }

    @Column(name = "total_mes_12", insertable = false, updatable = false)
    public Double getTotalMes12() {
        return totalMes12;
    }

    public void setTotalMes12(Double totalMes12) {
        this.totalMes12 = totalMes12;
    }

    @Column(name = "total_meses", insertable = false, updatable = false)
    public Double getTotalMeses() {
        return totalMeses;
    }

    public void setTotalMeses(Double totalMeses) {
        this.totalMeses = totalMeses;
    }

}
