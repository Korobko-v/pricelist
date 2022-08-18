package ru.korobko.pricelist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceDto {

    private String productCode;

    private Integer priceNumber;

    private Integer departmentNumber;

    private Date begin;

    private Date end;

    private Long value;
}
