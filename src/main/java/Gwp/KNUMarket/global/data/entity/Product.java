package Gwp.KNUMarket.global.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    private String imagePath;

    @NotNull
    private String title;

    @NotNull
    private Integer price;

    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Boolean sold = Boolean.FALSE;

    private Boolean deleted = Boolean.FALSE;
}
