package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Comment;
import Gwp.KNUMarket.global.repository.custom.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentRepositoryCustom {
}
