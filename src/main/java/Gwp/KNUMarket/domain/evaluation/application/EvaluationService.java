package Gwp.KNUMarket.domain.evaluation.application;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;

public interface EvaluationService {
    void post(User user, Product product);
}
