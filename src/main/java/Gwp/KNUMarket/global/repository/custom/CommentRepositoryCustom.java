package Gwp.KNUMarket.global.repository.custom;

import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentGetRes> findCommentsByProductId(Integer productId);
}
