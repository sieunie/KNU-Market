package Gwp.KNUMarket.global.repository.custom;

import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductGetListRes> findAllList();
    List<ProductGetListRes> findListByKeyword(String keyword);
}
