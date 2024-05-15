package Gwp.KNUMarket.domain.product.application.impl;

import Gwp.KNUMarket.domain.product.application.ProductService;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPatchReq;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetRes;
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

import javax.naming.NoPermissionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public ResponseEntity<List<ProductGetListRes>> getList(Integer page) {
        return new ResponseEntity<>(productRepository.findAllList(page), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductGetListRes>> getSearch(Integer page, String keyword) {
        return new ResponseEntity<>(productRepository.findListByKeyword(page, keyword), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> patch(ProductPatchReq productPatchReq, MultipartFile image, Authentication authentication) throws NoPermissionException {

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Product> optionalProduct = productRepository.findById(productPatchReq.getId());

        if (optionalProduct.isEmpty())
            throw new NoSuchElementException();

        Product product = optionalProduct.get();

        if (product.getUser() != optionalUser.get())
            throw new NoPermissionException();

        if (productPatchReq.getTitle() != null)
            product.setTitle(productPatchReq.getTitle());

        if (productPatchReq.getPrice() != null)
            product.setPrice(productPatchReq.getPrice());

        if (productPatchReq.getDescription() != null)
            product.setDescription(productPatchReq.getDescription());

        if (image != null) {
            String imageName = saveImage(image);
            product.setImagePath(sitePath + imageName);
        }

        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductGetRes> get(Integer id) {
        ProductGetRes productGetRes = productRepository.findProductById(id);

        if (productGetRes == null)
            throw new NoSuchElementException();

        return new ResponseEntity<>(productGetRes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException{
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty())
            throw new NoSuchElementException();

        Product product = optionalProduct.get();

        if (product.getUser() != optionalUser.get())
            throw new NoPermissionException();

        productRepository.delete(product);

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
