package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;

@Entity
@Table(name = "klienci")
public class Klienci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta", nullable = false)
    private Integer id;

    @Column(name = "nazwisko", nullable = false, length = 100)
    private String nazwisko;

    @Column(name = "imie", nullable = false, length = 100)
    private String imie;

    @Column(name = "drugie_imie", length = 100)
    private String drugieImie;

    @Column(name = "pesel", nullable = false, length = 11)
    private String pesel;

    @Column(name = "nip", length = 12)
    private String nip;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDrugieImie() {
        return drugieImie;
    }

    public void setDrugieImie(String drugieImie) {
        this.drugieImie = drugieImie;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}