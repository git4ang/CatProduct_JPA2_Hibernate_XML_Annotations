package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author by: ANG
 * since: 10/04/2022 12:29
 */

@Entity
@Table(name = "products_HAnn_tb")
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
    @JoinTable(name = "category_product_HAnno_tb", joinColumns = @JoinColumn(name = "idProduct"), inverseJoinColumns = @JoinColumn(name = "idCategory"))
    private Category category;

}

