package Gwp.KNUMarket.domain.request.application.impl;

import Gwp.KNUMarket.domain.alarm.application.AlarmService;
import Gwp.KNUMarket.domain.evaluation.application.EvaluationService;
import Gwp.KNUMarket.domain.request.application.RequestService;
import Gwp.KNUMarket.domain.request.data.dto.res.RequestGetRes;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.Request;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.data.enums.AlarmType;
import Gwp.KNUMarket.global.repository.ProductRepository;
import Gwp.KNUMarket.global.repository.RequestRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final AlarmService alarmService;
    private final EvaluationService evaluationService;

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

        Product product = optionalProduct.get();

        if (requestRepository.existsByUserAndProduct(optionalUser.get(), product))
            throw new DuplicateRequestException();

        Request request = Request.builder()
                .user(optionalUser.get())
                .product(product)
                .build();

        requestRepository.save(request);

        alarmService.post(product, product.getUser(), optionalUser.get(), AlarmType.REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RequestGetRes>> get(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty())
            throw new NoSuchElementException();

        return new ResponseEntity<>(requestRepository.findRequestByProductId(productId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> patch(Integer id, Authentication authentication) throws NoPermissionException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Request> optionalRequest = requestRepository.findById(id);

        if (optionalRequest.isEmpty())
            throw new NoSuchElementException();

        Request request = optionalRequest.get();

        if (request.getUser() != optionalUser.get())
            throw new NoPermissionException();

        request.setAccepted(Boolean.TRUE);
        requestRepository.save(request);

        request.getProduct().setSold(Boolean.TRUE);
        productRepository.save(request.getProduct());

        alarmService.post(request.getProduct(), request.getUser(), optionalUser.get(), AlarmType.ACCEPT);
        evaluationService.post(request.getUser(), request.getProduct());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Request> optionalRequest = requestRepository.findById(id);

        if (optionalRequest.isEmpty())
            throw new NoSuchElementException();

        Request request = optionalRequest.get();

        if (request.getUser() != optionalUser.get())
            throw new NoPermissionException();

        requestRepository.delete(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
