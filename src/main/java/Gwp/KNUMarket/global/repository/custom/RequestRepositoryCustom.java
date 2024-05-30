package Gwp.KNUMarket.global.repository.custom;

import Gwp.KNUMarket.domain.request.data.dto.res.RequestGetRes;

import java.util.List;

public interface RequestRepositoryCustom {
    List<RequestGetRes> findRequestByProductId(Integer productId);
}
