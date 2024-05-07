package Gwp.KNUMarket.domain.product.presentation;

import Gwp.KNUMarket.domain.product.application.ProductService;
import Gwp.KNUMarket.domain.product.data.dto.req.ProductPostReq;
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
}
