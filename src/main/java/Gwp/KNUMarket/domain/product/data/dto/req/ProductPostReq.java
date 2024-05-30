package Gwp.KNUMarket.domain.product.data.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPostReq {
    @NotNull
    private String title;
    @NotNull
    private Integer price;
    private String description;
}
