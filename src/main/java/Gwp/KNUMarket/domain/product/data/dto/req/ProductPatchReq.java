package Gwp.KNUMarket.domain.product.data.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPatchReq {
    @NotNull
    private Integer id;
    private String title;
    private Integer price;
    private String description;
}
