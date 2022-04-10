package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:29
 */

@Entity
@Table(name = "categories_HAnn_tb")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NonNull
    private String categoryName;

    @NonNull
    private String description;

    @NonNull
    private Date dateCreation;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Product> products;

}
