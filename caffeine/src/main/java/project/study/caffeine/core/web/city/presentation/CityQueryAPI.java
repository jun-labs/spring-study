package project.study.caffeine.core.web.city.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study.caffeine.core.web.city.application.CityQueryService;
import project.study.caffeine.core.web.city.presentation.response.DistrictResponse;

import java.util.List;

import static project.study.caffeine.common.key.CacheKey.DISTRICT;

@RestController
@RequestMapping("/api/districts")
public class CityQueryAPI {

    private final CityQueryService cityQueryService;

    public CityQueryAPI(CityQueryService cityQueryService) {
        this.cityQueryService = cityQueryService;
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictResponse> searchDistrict(@PathVariable Long districtId) {
        DistrictResponse data = cityQueryService.findDistrictById(districtId);
        return ResponseEntity.ok(data);
    }

    @GetMapping
    public ResponseEntity<List<DistrictResponse>> searchAllDistricts() {
        List<DistrictResponse> data = cityQueryService.searchAllDistricts();
        return ResponseEntity.ok(data);
    }


    @GetMapping("/eviction-specific")
    public ResponseEntity<Void> evictCache() {
        cityQueryService.evictCache(DISTRICT);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/eviction-all")
    public ResponseEntity<Void> evictAllCache() {
        cityQueryService.evictAllCache();
        return ResponseEntity.ok().build();
    }
}
