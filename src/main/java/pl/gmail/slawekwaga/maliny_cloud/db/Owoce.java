package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "owoce")
public class Owoce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owoca", nullable = false)
    private Integer id;

    @Column(name = "owoc", nullable = false, length = 256)
    private String owoc;

    @Column(name = "cena_skupu", nullable = false, precision = 131089)
    private BigDecimal cenaSkupu;

    @Column(name = "cena_sprzedazy", nullable = false, precision = 131089)
    private BigDecimal cenaSprzedazy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_miary", nullable = false)
    private Miary idMiary;

    @Column(name = "data_cen")
    private LocalDate dataCen;

    public LocalDate getDataCen() {
        return dataCen;
    }

    public void setDataCen(LocalDate dataCen) {
        this.dataCen = dataCen;
    }

    public Miary getIdMiary() {
        return idMiary;
    }

    public void setIdMiary(Miary idMiary) {
        this.idMiary = idMiary;
    }

    public BigDecimal getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(BigDecimal cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    public BigDecimal getCenaSkupu() {
        return cenaSkupu;
    }

    public void setCenaSkupu(BigDecimal cenaSkupu) {
        this.cenaSkupu = cenaSkupu;
    }

    public String getOwoc() {
        return owoc;
    }

    public void setOwoc(String owoc) {
        this.owoc = owoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}