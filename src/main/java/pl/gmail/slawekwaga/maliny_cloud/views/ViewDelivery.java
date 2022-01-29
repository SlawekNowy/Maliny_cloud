package pl.gmail.slawekwaga.maliny_cloud.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "view_deliveries")
public class ViewDelivery {
    @Id
    @Column(name = "id_dostawy")
    private Integer idDostawy;

    @Column(name = "imiona_i_nazwisko")
    private String imionaINazwisko;

    @Column(name = "owoc", length = 256)
    private String owoc;

    @Column(name = "koszt_owocow", precision = 131089)
    private BigDecimal kosztOwocow;

    @Column(name = "waga", precision = 131089)
    private BigDecimal waga;

    @Column(name = "skrot_miary", length = 10)
    private String skrotMiary;

    @Column(name = "id_klienta")
    private Integer idKlienta;

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public String getSkrotMiary() {
        return skrotMiary;
    }

    public BigDecimal getWaga() {
        return waga;
    }

    public BigDecimal getKosztOwocow() {
        return kosztOwocow;
    }

    public String getOwoc() {
        return owoc;
    }

    public String getImionaINazwisko() {
        return imionaINazwisko;
    }

    public Integer getIdDostawy() {
        return idDostawy;
    }
}