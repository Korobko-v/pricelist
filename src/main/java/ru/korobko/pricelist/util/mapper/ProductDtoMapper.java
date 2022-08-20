package ru.korobko.pricelist.util.mapper;

import org.springframework.stereotype.Component;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;
import ru.korobko.pricelist.domain.entity.Product;
import ru.korobko.pricelist.util.exception.NoCurrentPriceException;

@Component
public class ProductDtoMapper {

    /**
     * Get DTO from model.
     *
     * @param product model
     * @return product DTO
     * @throws NoCurrentPriceException
     */
    public ProductRequestDto getProductRequestDtoFromProduct(Product product) {
        ProductRequestDto productDto = new ProductRequestDto();
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        return productDto;
    }

    public ProductResponseDto getProductResponseDtoFromProduct(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setCode(product.getCode());
        productResponseDto.setName(product.getName());
        if (product.getPrices() != null && product.hasCurrentPrice()) {
            productResponseDto.setCurrentPriceValue(product.getCurrentPrice().getValue());
        }
        return productResponseDto;
    }

    public Product getProductFromProductRequestDto(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCode(productRequestDto.getCode());
        product.setName(productRequestDto.getName());

        return product;
    }
}
