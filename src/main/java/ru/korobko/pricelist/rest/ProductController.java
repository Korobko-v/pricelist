package ru.korobko.pricelist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;
import ru.korobko.pricelist.service.IProductService;
import ru.korobko.pricelist.util.mapper.ProductDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductResponseDto> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortingFields", defaultValue = "firstName") String[] sortingFields,
            @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection
    ) {
        Pageable pageableWithSorting = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Direction.fromString(sortDirection), sortingFields)
        );

        List<ProductResponseDto> productResponseDtoList = this.productService.findAll();

        return new PageImpl<>(productResponseDtoList, pageableWithSorting, productResponseDtoList.size());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return this.productService.create(productRequestDto);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto addPrice(@PathVariable(name = "id") Long id, @RequestBody PriceRequestDto priceRequestDto) {
        return this.productService.addPrice(id, priceRequestDto);
    }

}
