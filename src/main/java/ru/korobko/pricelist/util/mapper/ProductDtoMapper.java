package ru.korobko.pricelist.util.mapper;

import ru.korobko.pricelist.domain.dto.ProductDto;
import ru.korobko.pricelist.domain.entity.Product;
import ru.korobko.pricelist.util.exception.NoCurrentPriceException;

import java.util.Date;

public class ProductDtoMapper {

    /**
     * Get DTO from model.
     * @param product model
     * @return product DTO
     * @throws NoCurrentPriceException
     */
    public static ProductDto getProductDtoFromProduct(Product product) throws NoCurrentPriceException {
        ProductDto productDto = new ProductDto();
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        try {
            productDto.setCurrentPrice(product.getPrices()
                    .stream()
                    .filter(price -> !price.getBegin().after(new Date()) && !price.getEnd().after(new Date()))
                    .findFirst().orElseThrow().getValue());
        } catch (Exception e) {
            throw new NoCurrentPriceException("No price for this product or all prices are expired/unavailable. Need to set a new price");
        }

        return productDto;
    }
}
