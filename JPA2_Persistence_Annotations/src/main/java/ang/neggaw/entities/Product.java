package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author by: ANG
 * since: 10/04/2022 11:04
 */

@Entity
@Table(name = "products_pers_ann_tb")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NonNull
    private String productName;

    @NonNull
    private String description;

    @NonNull
    private double price;

    @NonNull
    @Transient
    private long idCategory;

    @ManyToOne
    @JoinTable(name = "category_products_pers_ann_tb",
            joinColumns = @JoinColumn(name = "idProduct", referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    )
    @ToString.Exclude
    private Category category;

}
