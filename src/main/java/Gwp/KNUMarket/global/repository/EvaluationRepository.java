package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
}
