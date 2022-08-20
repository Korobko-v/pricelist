package ru.korobko.pricelist.service;

import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;

import java.util.List;

public interface IPriceService {

    List<PriceResponseDto> findAll();

    PriceResponseDto create(PriceRequestDto priceRequestDto);
}
