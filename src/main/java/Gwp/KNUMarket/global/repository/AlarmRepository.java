package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Alarm;
import Gwp.KNUMarket.global.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
    List<Alarm> findByUser(User user);
}
