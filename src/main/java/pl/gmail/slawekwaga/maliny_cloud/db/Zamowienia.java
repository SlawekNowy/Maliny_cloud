package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "koszt_zamowienia", nullable = false, precision = 131089)
    private BigDecimal kosztZamowienia;

    @Column(name = "waga_zamowienia", nullable = false, precision = 131089)
    private BigDecimal wagaZamowienia;

    @ManyToOne
    @JoinColumn(name = "id_miary")
    private Miary idMiary;

    @ManyToOne
    @JoinColumn(name = "id_owoca")
    private Owoce idOwoca;

    @Column(name = "data_zamowienia")
    private LocalDate dataZamowienia;

    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
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