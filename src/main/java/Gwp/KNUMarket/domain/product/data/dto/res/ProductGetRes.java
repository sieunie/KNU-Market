package Gwp.KNUMarket.domain.product.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductGetRes {
    private String title;
    private Integer price;
    private String description;
    private String imagePath;
    private Integer userId;
    private String userName;
    private String userImagePath;
    private Integer starScore;
    private LocalDateTime createdAt;
}
