package ru.korobko.pricelist.util.mapper;

import ru.korobko.pricelist.domain.dto.PriceDto;
import ru.korobko.pricelist.domain.entity.Price;

public class PriceDtoMapper {

    /**
     * Get DTO from model.
     * @param price model
     * @return price DTO
     */
    public static PriceDto getPriceDtoFromPrice(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPriceNumber(price.getPriceNumber());
        priceDto.setBegin(price.getBegin());
        priceDto.setEnd(price.getEnd());
        priceDto.setDepartmentNumber(price.getDepartmentNumber());
        priceDto.setProductCode(price.getProduct().getCode());
        priceDto.setValue(price.getValue());

        return priceDto;
    }
}
