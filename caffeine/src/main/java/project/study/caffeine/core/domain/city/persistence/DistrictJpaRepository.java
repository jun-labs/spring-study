package project.study.caffeine.core.domain.city.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.study.caffeine.core.domain.city.entity.District;

import java.util.Optional;

public interface DistrictJpaRepository extends JpaRepository<District, Long> {

    @Query("SELECT d FROM district d INNER JOIN FETCH d.city WHERE d.districtId = :districtId")
    Optional<District> findDistrictByDistrictId(@Param("districtId") Long districtId);
}
