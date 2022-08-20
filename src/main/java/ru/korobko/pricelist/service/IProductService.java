package ru.korobko.pricelist.service;

import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;
import ru.korobko.pricelist.domain.entity.Product;

import java.util.List;

public interface IProductService {

    List<ProductResponseDto> findAll();

    ProductResponseDto create(ProductRequestDto productRequestDto);

    ProductResponseDto addPrice(Long productId, PriceRequestDto priceRequestDto);

    Product getById(Long id);

    List<PriceResponseDto> getAllPricesForProduct(Long productId);
}
