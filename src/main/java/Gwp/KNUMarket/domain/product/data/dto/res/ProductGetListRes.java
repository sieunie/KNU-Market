package Gwp.KNUMarket.domain.product.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductGetListRes {
    private String title;
    private Integer price;
    private String userName;
    private String imagePath;
    private LocalDateTime createdAt;
}
