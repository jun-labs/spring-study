package project.study.caffeine.core.web.city.presentation.response;

import project.study.caffeine.core.domain.city.entity.District;

public class DistrictResponse {

    private final Long districtId;
    private final String districtName;
    private final String cityName;

    private DistrictResponse(District district) {
        this.districtId = district.getDistrictId();
        this.districtName = district.getName();
        this.cityName = district.getCityName();
    }

    public static DistrictResponse of(District district) {
        return new DistrictResponse(district);
    }

    public Long getDistrictId() {
        return districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getCityName() {
        return cityName;
    }
}
