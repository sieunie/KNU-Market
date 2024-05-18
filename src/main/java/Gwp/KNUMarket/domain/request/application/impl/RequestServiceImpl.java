package Gwp.KNUMarket.domain.request.application.impl;

import Gwp.KNUMarket.domain.request.application.RequestService;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.Request;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.ProductRepository;
import Gwp.KNUMarket.global.repository.RequestRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final RequestRepository requestRepository;

    @Override
    public ResponseEntity<HttpStatus> post(Integer productId, Authentication authentication) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty())
            throw new NoSuchElementException();

        Request request = Request.builder()
                .user(optionalUser.get())
                .product(optionalProduct.get())
                .build();

        requestRepository.save(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
