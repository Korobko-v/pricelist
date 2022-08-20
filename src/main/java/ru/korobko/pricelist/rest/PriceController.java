package ru.korobko.pricelist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;
import ru.korobko.pricelist.service.IPriceService;

import java.util.List;

@RestController
@RequestMapping("/price_list")
public class PriceController {

    private final IPriceService priceService;

    @Autowired
    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public Page<PriceResponseDto> getAllPrices(
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

        List<PriceResponseDto> priceRequestDtoList = this.priceService.findAll();

        return new PageImpl<>(priceRequestDtoList, pageableWithSorting, priceRequestDtoList.size());
    }

    @PostMapping
    public PriceResponseDto create(@RequestBody PriceRequestDto priceRequestDto) {
        return this.priceService.create(priceRequestDto);
    }
}
