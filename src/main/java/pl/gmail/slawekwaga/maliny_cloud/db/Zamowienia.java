package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "zamowienia")
public class Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienia", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_zamawiajacego")
    private Firmy idZamawiajacego;

    @ManyToOne
    @JoinColumn(name = "id_klienta")
    private Klienci idKlienta;

    @Column(name = "koszt_zamowienia", nullable = false, precision = 131089)
    private BigDecimal kosztZamowienia;

    @Column(name = "waga_zamowienia", nullable = false, precision = 131089)
    private BigDecimal wagaZamowienia;

    @ManyToOne
    @JoinColumn(name = "id_miary")
    private Miary idMiary;

    public Miary getIdMiary() {
        return idMiary;
    }

    public void setIdMiary(Miary idMiary) {
        this.idMiary = idMiary;
    }

    public BigDecimal getWagaZamowienia() {
        return wagaZamowienia;
    }

    public void setWagaZamowienia(BigDecimal wagaZamowienia) {
        this.wagaZamowienia = wagaZamowienia;
    }

    public BigDecimal getKosztZamowienia() {
        return kosztZamowienia;
    }

    public void setKosztZamowienia(BigDecimal kosztZamowienia) {
        this.kosztZamowienia = kosztZamowienia;
    }

    public Klienci getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Klienci idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Firmy getIdZamawiajacego() {
        return idZamawiajacego;
    }

    public void setIdZamawiajacego(Firmy idZamawiajacego) {
        this.idZamawiajacego = idZamawiajacego;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}