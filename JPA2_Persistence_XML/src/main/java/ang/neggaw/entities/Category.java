package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:49
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    private Long idCategory;

    @NonNull
    private String categoryName;

    @NonNull
    private String description;

    @NonNull
    private Date dateCreation;

    private List<Product> products;

}

