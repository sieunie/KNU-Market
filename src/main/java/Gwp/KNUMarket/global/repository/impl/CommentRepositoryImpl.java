package Gwp.KNUMarket.global.repository.impl;

import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import Gwp.KNUMarket.global.repository.custom.CommentRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Gwp.KNUMarket.global.data.entity.QComment.comment;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentGetRes> findCommentsByProductId(Integer productId) {
        return jpaQueryFactory
                .select(Projections.constructor(CommentGetRes.class,
                        comment.user.name, comment.user.imagePath, comment.content, comment.createdAt, comment.isSecret))
                .from(comment)
                .where(comment.product.id.eq(productId))
                .orderBy(comment.createdAt.asc())
                .fetch();
    }
}
