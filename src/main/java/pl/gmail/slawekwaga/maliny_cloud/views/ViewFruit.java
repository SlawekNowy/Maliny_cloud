package pl.gmail.slawekwaga.maliny_cloud.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "view_fruits")
public class ViewFruit {
    @Id
    @Column(name = "id_owoca")
    private Integer idOwoca;

    @Column(name = "owoc", length = 256)
    private String owoc;

    @Column(name = "skrot_miary", length = 10)
    private String skrotMiary;

    @Column(name = "cena_skupu", precision = 131089)
    private BigDecimal cenaSkupu;

    @Column(name = "cena_sprzedazy", precision = 131089)
    private BigDecimal cenaSprzedazy;

    @Column(name = "data_cen")
    private LocalDate dataCen;

    public LocalDate getDataCen() {
        return dataCen;
    }

    public BigDecimal getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public BigDecimal getCenaSkupu() {
        return cenaSkupu;
    }

    public String getSkrotMiary() {
        return skrotMiary;
    }

    public String getOwoc() {
        return owoc;
    }

    public Integer getIdOwoca() {
        return idOwoca;
    }
}