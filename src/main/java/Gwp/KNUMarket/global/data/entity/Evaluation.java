package Gwp.KNUMarket.global.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE evaluation SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToOne
    private Product product;

    private Integer evaluate;

    @Builder.Default
    private Boolean deleted = Boolean.FALSE;
}
