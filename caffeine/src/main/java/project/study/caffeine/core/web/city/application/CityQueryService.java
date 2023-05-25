package project.study.caffeine.core.web.city.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.caffeine.core.domain.city.entity.District;
import project.study.caffeine.core.domain.city.persistence.DistrictJpaRepository;
import project.study.caffeine.core.web.city.presentation.response.DistrictResponse;

import java.util.List;

@Slf4j
@Service
public class CityQueryService {

    private final DistrictJpaRepository districtJpaRepository;

    public CityQueryService(DistrictJpaRepository districtJpaRepository) {
        this.districtJpaRepository = districtJpaRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "district")
    public DistrictResponse findDistrictById(Long districtId) {
        District findDistrict = districtJpaRepository.findDistrictByDistrictId(districtId)
                .orElseThrow();
        return DistrictResponse.of(findDistrict);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "district")
    public List<DistrictResponse> searchAllDistricts() {
        return districtJpaRepository.findAll().stream()
                .map(DistrictResponse::of)
                .toList();
    }

    @CacheEvict(value = "district", key = "#district")
    public void evictCache(String district) {
        log.info("Evict Specific Cache.");
    }

    @CacheEvict(value = {"district"}, allEntries = true)
    public void evictAllCache() {
        log.info("Evict All Caches.");
    }
}
