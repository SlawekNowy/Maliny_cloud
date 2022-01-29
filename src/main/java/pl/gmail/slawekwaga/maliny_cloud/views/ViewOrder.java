package pl.gmail.slawekwaga.maliny_cloud.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "view_orders")
public class ViewOrder {

    @Id
    @Column(name = "id_zamowienia")
    private Integer idZamowienia;

    @Column(name = "nazwa_firmy", length = 250)
    private String nazwaFirmy;

    @Column(name = "koszt_zamowienia", precision = 131089)
    private BigDecimal kosztZamowienia;

    @Column(name = "waga_zamowienia", precision = 131089)
    private BigDecimal wagaZamowienia;

    @Column(name = "skrot_miary", length = 10)
    private String skrotMiary;

    @Column(name = "owoc", length = 256)
    private String owoc;

    public String getOwoc() {
        return owoc;
    }

    public String getSkrotMiary() {
        return skrotMiary;
    }

    public BigDecimal getWagaZamowienia() {
        return wagaZamowienia;
    }

    public BigDecimal getKosztZamowienia() {
        return kosztZamowienia;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public Integer getIdZamowienia() {
        return idZamowienia;
    }
}