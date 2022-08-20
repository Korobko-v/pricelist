package ru.korobko.pricelist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;
import ru.korobko.pricelist.domain.entity.Price;
import ru.korobko.pricelist.domain.entity.Product;
import ru.korobko.pricelist.repository.PriceRepository;
import ru.korobko.pricelist.repository.ProductRepository;
import ru.korobko.pricelist.service.IProductService;
import ru.korobko.pricelist.util.mapper.PriceDtoMapper;
import ru.korobko.pricelist.util.mapper.ProductDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ProductDtoMapper productDtoMapper;

    @Autowired
    private PriceDtoMapper priceDtoMapper;

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> all = productRepository.findAll();
        return all
                .stream()
                .map(product -> this.productDtoMapper.getProductResponseDtoFromProduct(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = this.productDtoMapper.getProductFromProductRequestDto(productRequestDto);
        Product savedProduct = productRepository.save(product);
        return this.productDtoMapper.getProductResponseDtoFromProduct(savedProduct);
    }

    @Override
    public ProductResponseDto addPrice(Long productId, PriceRequestDto priceRequestDto) {
        Product product = this.productRepository.findById(productId).orElseThrow();
        Price price = product.checkAndCreateOrUpdatePrice(this.priceDtoMapper.getPriceFromPriceRequestDto(priceRequestDto));
        Product save = this.productRepository.save(product);
        Price savedPrice = this.priceRepository.save(price);
        return this.productDtoMapper.getProductResponseDtoFromProduct(save);
    }

    @Override
    @Transactional
    public Product getById(Long id) {
        return this.productRepository.findById(id).orElseThrow();
    }

}
