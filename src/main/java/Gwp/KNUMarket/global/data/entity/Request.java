package Gwp.KNUMarket.global.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE request SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    Product product;

    @ManyToOne
    User user;

    @Builder.Default
    Boolean accepted = Boolean.FALSE;

    @Builder.Default
    Boolean deleted = Boolean.FALSE;
}
