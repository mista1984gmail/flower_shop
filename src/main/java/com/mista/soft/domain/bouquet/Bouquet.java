package com.mista.soft.domain.bouquet;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.flowers.Flowers;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
public class Bouquet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nameBouquet;

    @NonNull
    private int id;

    private List<Flowers> flowersList;
    private List<Accessories> accessoriesList;
}
