package Gwp.KNUMarket.domain.product.application;

import Gwp.KNUMarket.domain.product.data.dto.req.ProductPatchReq;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.util.List;

public interface ProductService {

    ResponseEntity<HttpStatus> post(ProductPostReq productPostReq, MultipartFile image, Authentication authentication);
    ResponseEntity<List<ProductGetListRes>> getList(Integer page);
    ResponseEntity<List<ProductGetListRes>> getSearch(Integer page, String keyword);
    ResponseEntity<HttpStatus> patch(ProductPatchReq productPatchReq, MultipartFile image, Authentication authentication) throws NoPermissionException;
    ResponseEntity<ProductGetRes> get(Integer id);
    ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException;
    ResponseEntity<List<ProductGetListRes>> getMine(Integer page, Authentication authentication);
}
