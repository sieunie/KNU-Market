package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.Request;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.custom.RequestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>, RequestRepositoryCustom {

    Boolean existsByUserAndProduct(User user, Product product);
}
