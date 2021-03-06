package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dostawy")
public class Dostawy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dostawy", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_klienta", nullable = false)
    private Klienci idKlienta;

    @Column(name = "waga", nullable = false, precision = 131089)
    private BigDecimal waga;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_miary", nullable = false)
    private Miary idMiary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_owoca", nullable = false)
    private Owoce idOwoca;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "koszt_owocow", precision = 131089)
    private BigDecimal kosztOwocow;

    public BigDecimal getKosztOwocow() {
        return kosztOwocow;
    }

    public void setKosztOwocow(BigDecimal kosztOwocow) {
        this.kosztOwocow = kosztOwocow;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Owoce getIdOwoca() {
        return idOwoca;
    }

    public void setIdOwoca(Owoce idOwoca) {
        this.idOwoca = idOwoca;
    }

    public Miary getIdMiary() {
        return idMiary;
    }

    public void setIdMiary(Miary idMiary) {
        this.idMiary = idMiary;
    }

    public BigDecimal getWaga() {
        return waga;
    }

    public void setWaga(BigDecimal waga) {
        this.waga = waga;
    }

    public Klienci getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Klienci idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}