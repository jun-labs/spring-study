package project.study.jpa.core.web.city.presentation.response

import project.study.jpa.core.domain.city.entity.District

class DistrictResponse(district: District) {

    private val districtId: Long = district.getDistrictId()!!
    private val districtName: String = district.getName()
    private val cityName: String = district.getCityName()

    fun getDistrictId(): Long {
        return districtId
    }

    fun getDistrictName(): String {
        return districtName
    }

    fun getCityName(): String {
        return cityName
    }
}
