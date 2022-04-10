package ang.neggaw.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:11
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Category implements Serializable {

    private Long idCategory;

    @NonNull
    private String categoryName;

    @NonNull
    private String description;

    @NonNull
    private Date dateCreation;

    @ToString.Exclude
    private List<Product> products;

}
