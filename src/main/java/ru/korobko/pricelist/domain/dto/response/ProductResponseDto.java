package ru.korobko.pricelist.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {

    private Long id;

    private String code;

    private String name;

    private Long currentPriceValue;
}
