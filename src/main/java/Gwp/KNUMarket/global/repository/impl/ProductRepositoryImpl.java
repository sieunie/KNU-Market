package Gwp.KNUMarket.global.repository.impl;

import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import Gwp.KNUMarket.global.repository.custom.ProductRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Gwp.KNUMarket.global.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductGetListRes> findAllList(Integer page) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductGetListRes.class,
                        product.id, product.title, product.price, product.user.name, product.imagePath, product.createdAt))
                .from(product)
                .orderBy(product.createdAt.desc())
                .offset(page * 8)
                .limit(8)
                .fetch();
    }

    @Override
    public List<ProductGetListRes> findListByKeyword(Integer page, String keyword) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductGetListRes.class,
                        product.id, product.title, product.price, product.user.name, product.imagePath, product.createdAt))
                .from(product)
                .where(product.title.contains(keyword))
                .orderBy(product.createdAt.desc())
                .offset(page * 8)
                .limit(8)
                .fetch();
    }
}
