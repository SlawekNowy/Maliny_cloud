package pl.gmail.slawekwaga.maliny_cloud.db;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "miary")
public class Miary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miary", nullable = false)
    private Integer id;

    @Column(name = "nazwa_miary", nullable = false, length = 256)
    private String nazwaMiary;

    @Column(name = "mnoznik_miary", nullable = false, precision = 131089)
    private BigDecimal mnoznikMiary;

    @Column(name = "skrot_miary", nullable = false, length = 10)
    private String skrotMiary;

    @Column(name = "jest_metryczna", nullable = false)
    private Boolean jestMetryczna = false;

    public Boolean getJestMetryczna() {
        return jestMetryczna;
    }

    public void setJestMetryczna(Boolean jestMetryczna) {
        this.jestMetryczna = jestMetryczna;
    }

    public String getSkrotMiary() {
        return skrotMiary;
    }

    public void setSkrotMiary(String skrotMiary) {
        this.skrotMiary = skrotMiary;
    }

    public BigDecimal getMnoznikMiary() {
        return mnoznikMiary;
    }

    public void setMnoznikMiary(BigDecimal mnoznikMiary) {
        this.mnoznikMiary = mnoznikMiary;
    }

    public String getNazwaMiary() {
        return nazwaMiary;
    }

    public void setNazwaMiary(String nazwaMiary) {
        this.nazwaMiary = nazwaMiary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}