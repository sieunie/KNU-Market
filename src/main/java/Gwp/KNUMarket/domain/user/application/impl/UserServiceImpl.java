package Gwp.KNUMarket.domain.user.application.impl;

import Gwp.KNUMarket.domain.user.application.UserService;
import Gwp.KNUMarket.domain.user.data.dto.res.UserGetProductElementRes;
import Gwp.KNUMarket.domain.user.data.dto.res.UserGetRes;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${image.upload-path}")
    private String uploadPath;
    @Value("${image.site-path}")
    private String sitePath;

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

    @Override
    public ResponseEntity<HttpStatus> patch(String name, MultipartFile image, Authentication authentication) {

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        User user = optionalUser.get();

        if (image != null) {
            String imageName = saveImage(image);
            user.setImagePath(sitePath + imageName);
        }

        if (name != null) {
            user.setName(name);
        }

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> getExist(String name) {
        return new ResponseEntity<>(userRepository.existsByName(name), HttpStatus.OK);
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
