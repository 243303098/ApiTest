package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.p2p_mobile_addr;

public interface p2p_mobile_addrMapper {

    p2p_mobile_addr selectByMobile(String mobile);

    p2p_mobile_addr selectByCityCode(String cityCode);

    p2p_mobile_addr selectByProvinceCode(String provinceCode);

    p2p_mobile_addr selectByOtherProvinceCode(String provinceCode);

    p2p_mobile_addr selectByOtherCityCode(String cityCode);

}