package project.study.caffeine.core.domain.city.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private City city;

    public Long getDistrictId() {
        return districtId;
    }

    public City getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return city.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District district)) return false;
        return getDistrictId().equals(district.getDistrictId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDistrictId());
    }

    @Override
    public String toString() {
        return districtId.toString();
    }
}
