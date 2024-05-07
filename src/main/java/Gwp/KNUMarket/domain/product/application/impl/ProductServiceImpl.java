package Gwp.KNUMarket.domain.product.application.impl;

import Gwp.KNUMarket.domain.product.application.ProductService;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.ProductRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Value("${image.upload-path}")
    private String uploadPath;
    @Value("${image.site-path}")
    private String sitePath;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<HttpStatus> post(ProductPostReq productPostReq, MultipartFile image, Authentication authentication) {

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        String imagePath = null;

        if (image != null)
            imagePath = sitePath + saveImage(image);

        Product product = Product.builder()
                .price(productPostReq.getPrice())
                .title(productPostReq.getTitle())
                .imagePath(imagePath)
                .description(productPostReq.getDescription())
                .user(optionalUser.get())
                .build();

        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String saveImage(MultipartFile image) {
        String originalName = image.getOriginalFilename();
        String newName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalName);
        String newPath = uploadPath + newName;
        Path filePath = Paths.get(newPath);

        try {
            Files.copy(image.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newName;
    }
}
