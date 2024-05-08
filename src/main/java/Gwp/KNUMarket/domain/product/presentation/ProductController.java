package Gwp.KNUMarket.domain.product.presentation;

import Gwp.KNUMarket.domain.product.application.ProductService;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPatchReq;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
import Gwp.KNUMarket.domain.product.data.dto.res.ProductGetListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
@Tag(name = "상품")
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "상품 게시 API", description = "title, price는 not null")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> post(@ParameterObject @ModelAttribute ProductPostReq productPostReq, @RequestPart(required = false) MultipartFile image, @Parameter(hidden = true) Authentication authentication) {
        return productService.post(productPostReq, image, authentication);
    }

    @GetMapping("/list")
    @Operation(summary = "상품 리스트 조회 API", description = "모든 상품 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<List<ProductGetListRes>> getList() {
        return productService.getList();
    }

    @GetMapping("/search")
    @Operation(summary = "상품 리스트 키워드 검색 API", description = "키워드 포함 하는 상품 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<List<ProductGetListRes>> getSearch(@RequestParam String keyword) {
        return productService.getSearch(keyword);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "상품 정보 수정 API", description = "수정할 데이터만 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> patch(@ParameterObject @ModelAttribute ProductPatchReq productPatchReq, @RequestPart(required = false) MultipartFile image, @Parameter(hidden = true) Authentication authentication) throws NoPermissionException {
        return productService.patch(productPatchReq, image, authentication);
    }
}
