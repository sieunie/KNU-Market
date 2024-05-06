package Gwp.KNUMarket.domain.user.application.impl;

import Gwp.KNUMarket.domain.user.application.UserService;
import Gwp.KNUMarket.domain.user.data.dto.res.UserGetProductElementRes;
import Gwp.KNUMarket.domain.user.data.dto.res.UserGetRes;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.ProductRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<UserGetRes> get(Authentication authentication) {

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        User user = optionalUser.get();

        List<UserGetProductElementRes> userGetProductElementResList = new ArrayList<>();

        for (Product product : productRepository.findAllByUser(user)) {
            userGetProductElementResList.add(new UserGetProductElementRes(
                    product.getId(), product.getTitle(), product.getImagePath(), product.getPrice(), product.getCreatedAt()
            ));
        }

        return new ResponseEntity<>(new UserGetRes(user.getName(), user.getImagePath(), user.getStarScore(), userGetProductElementResList), HttpStatus.OK);
    }
}
