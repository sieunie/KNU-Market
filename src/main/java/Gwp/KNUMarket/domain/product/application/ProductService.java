package Gwp.KNUMarket.domain.product.application;

import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ResponseEntity<HttpStatus> post(ProductPostReq productPostReq, MultipartFile image, Authentication authentication);
    ResponseEntity<List<ProductGetListRes>> getList();
}
