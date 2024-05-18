package Gwp.KNUMarket.domain.comment.application.impl;

import Gwp.KNUMarket.domain.comment.application.CommentService;
import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;
import Gwp.KNUMarket.global.data.entity.Comment;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.CommentRepository;
import Gwp.KNUMarket.global.repository.ProductRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<HttpStatus> post(CommentPostReq commentPostReq, Authentication authentication) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Product> optionalProduct = productRepository.findById(commentPostReq.getProductId());

        if (optionalProduct.isEmpty())
            throw new NoSuchElementException();

        Comment comment = Comment.builder()
                .user(optionalUser.get())
                .product(optionalProduct.get())
                .content(commentPostReq.getContent())
                .isSecret(commentPostReq.getIsSecret())
                .build();

        commentRepository.save(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommentGetRes>> get(Integer productId) {
        return new ResponseEntity<>(commentRepository.findCommentsByProductId(productId), HttpStatus.OK);
    }
}