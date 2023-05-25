package project.study.caffeine.core.domain.city.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column
    private String name;

    public Long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        return getCityId().equals(city.getCityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityId());
    }

    @Override
    public String toString() {
        return cityId.toString();
    }
}
