package ru.korobko.pricelist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.response.PriceResponseDto;
import ru.korobko.pricelist.domain.entity.Price;
import ru.korobko.pricelist.repository.PriceRepository;
import ru.korobko.pricelist.service.IPriceService;
import ru.korobko.pricelist.util.mapper.PriceDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService implements IPriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceDtoMapper priceDtoMapper;

    @Override
    public List<PriceResponseDto> findAll() {
        return priceRepository.findAll()
                .stream()
                .map(price -> this.priceDtoMapper.getPriceResponseDtoFromPrice(price))
                .collect(Collectors.toList());
    }

    @Override
    public PriceResponseDto create(PriceRequestDto priceRequestDto) {
        Price createdPrice = this.priceRepository.save(this.priceDtoMapper.getPriceFromPriceRequestDto(priceRequestDto));
        return priceDtoMapper.getPriceResponseDtoFromPrice(createdPrice);
    }
}
