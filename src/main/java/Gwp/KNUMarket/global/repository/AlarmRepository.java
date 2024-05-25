package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
}
