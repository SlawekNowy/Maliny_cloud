package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;

@Entity
@Table(name = "firmy")
public class Firmy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_firmy", nullable = false)
    private Integer id;

    @Column(name = "nazwa_firmy", nullable = false, length = 250)
    private String nazwaFirmy;

    @Column(name = "adres_firmy", nullable = false, length = 250)
    private String adresFirmy;

    @Column(name = "nip", nullable = false, length = 12)
    private String nip;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getAdresFirmy() {
        return adresFirmy;
    }

    public void setAdresFirmy(String adresFirmy) {
        this.adresFirmy = adresFirmy;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}