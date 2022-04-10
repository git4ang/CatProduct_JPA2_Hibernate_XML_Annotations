package ang.neggaw.entities;

import lombok.*;

import java.io.Serializable;

/**
 * author by: ANG
 * since: 10/04/2022 10:50
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    private Long idProduct;

    @NonNull
    private String productName;

    @NonNull
    private String description;

    @NonNull
    private double price;

    @NonNull
    private long idCategory;

    private Category category;
}

