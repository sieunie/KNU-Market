package Gwp.KNUMarket.global.repository;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByUser(User user);
}
