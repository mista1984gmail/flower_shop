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
@Builder(access = AccessLevel.PUBLIC)
public class Bouquet implements Serializable {
    private static final long serialVersionUID = 1L;

    String nameBouquet;

    @NonNull
    int id;

    List<Flowers> flowersList;
    List<Accessories> accessoriesList;
}
