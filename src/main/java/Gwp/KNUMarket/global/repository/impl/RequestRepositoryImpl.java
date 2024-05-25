package Gwp.KNUMarket.global.repository.impl;

import Gwp.KNUMarket.domain.request.data.dto.res.RequestGetRes;
import Gwp.KNUMarket.global.repository.custom.RequestRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Gwp.KNUMarket.global.data.entity.QRequest.request;

@Repository
@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RequestGetRes> findRequestByProductId(Integer productId) {
        return jpaQueryFactory
                .select(Projections.constructor(RequestGetRes.class,
                        request.id, request.user.name, request.user.imagePath, request.createdAt))
                .from(request)
                .where(request.product.id.eq(productId))
                .orderBy(request.createdAt.asc())
                .fetch();
    }
}
