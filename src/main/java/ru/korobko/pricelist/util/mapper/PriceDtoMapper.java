package ru.korobko.pricelist.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;
import ru.korobko.pricelist.domain.entity.Price;
import ru.korobko.pricelist.repository.ProductRepository;

@Component
public class PriceDtoMapper {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get request DTO from model.
     * @param price model
     * @return price request DTO
     */
    public PriceResponseDto getPriceRequestDtoFromPrice(Price price) {
        PriceResponseDto priceRequestDto = new PriceResponseDto();
        priceRequestDto.setPriceNumber(price.getPriceNumber());
        priceRequestDto.setBegin(price.getBeginDate());
        priceRequestDto.setEnd(price.getEndDate());
        priceRequestDto.setDepartmentNumber(price.getDepartmentNumber());
        priceRequestDto.setValue(price.getValue());

        return priceRequestDto;
    }

    /**
     * Get response DTO from model.
     * @param price model
     * @return price response DTO
     */
    public PriceResponseDto getPriceResponseDtoFromPrice(Price price) {
        PriceResponseDto priceResponseDto = new ru.korobko.pricelist.domain.dto.response.PriceResponseDto();
        priceResponseDto.setPriceNumber(price.getPriceNumber());
        priceResponseDto.setBegin(price.getBeginDate());
        priceResponseDto.setEnd(price.getEndDate());
        priceResponseDto.setDepartmentNumber(price.getDepartmentNumber());
        priceResponseDto.setValue(price.getValue());

        return priceResponseDto;
    }

    /**
     * Get price model from request DTO
     * @param priceRequestDto request DTO
     * @return price model
     */
    public Price getPriceFromPriceRequestDto(PriceRequestDto priceRequestDto) {
        Price price = new Price();
        price.setPriceNumber(priceRequestDto.getPriceNumber());
        price.setBeginDate(priceRequestDto.getBegin());
        price.setEndDate(priceRequestDto.getEnd());
        price.setDepartmentNumber(priceRequestDto.getDepartmentNumber());
        price.setValue(priceRequestDto.getValue());

        return price;
    }
}
