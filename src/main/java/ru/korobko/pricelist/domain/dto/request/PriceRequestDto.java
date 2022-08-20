package ru.korobko.pricelist.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {

    private Long productId;

    private Integer priceNumber;

    private Integer departmentNumber;

    private Date begin;

    private Date end;

    private Long value;
}
