package ru.korobko.pricelist.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDto {

    private Long id;

    private Long productId;

    private Integer priceNumber;

    private Integer departmentNumber;

    private Date begin;

    private Date end;

    private Long value;
}
