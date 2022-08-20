package ru.korobko.pricelist.util.mapper;

import org.springframework.stereotype.Component;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;
import ru.korobko.pricelist.domain.entity.Product;

@Component
public class ProductDtoMapper {

    /**
     * Get request DTO from the model.
     *
     * @param product model
     * @return product request DTO
     */
    public ProductRequestDto getProductRequestDtoFromProduct(Product product) {
        ProductRequestDto productDto = new ProductRequestDto();
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        return productDto;
    }

    /**
     * Get response DTO from the model
     * @param product model
     * @return product response DTO
     */
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

    /**
     * Get product from product request DTO
     * @param productRequestDto product request DTO
     * @return product model
     */
    public Product getProductFromProductRequestDto(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCode(productRequestDto.getCode());
        product.setName(productRequestDto.getName());

        return product;
    }
}
