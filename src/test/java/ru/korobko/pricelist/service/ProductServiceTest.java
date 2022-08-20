package ru.korobko.pricelist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.pricelist.domain.dto.request.PriceRequestDto;
import ru.korobko.pricelist.domain.dto.request.ProductRequestDto;
import ru.korobko.pricelist.domain.dto.response.ProductResponseDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    public void testCreateTheProductAndAddDifferentPrices() throws ParseException {
        ProductRequestDto productRequestDto = new ProductRequestDto();
        Assertions.assertEquals(0, productService.findAll().size());

        productRequestDto.setCode("001");
        productRequestDto.setName("meat");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Long id = productService.create(productRequestDto).getId();


        Assertions.assertEquals(1, productService.findAll().size());

        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setPriceNumber(1);
        priceRequestDto.setValue(1000L);
        priceRequestDto.setDepartmentNumber(1);
        priceRequestDto.setBegin(new Date());
        priceRequestDto.setEnd(simpleDateFormat.parse("2025-10-10T00:00:00"));

        productService.addPrice(id, priceRequestDto);

        Assertions.assertEquals(1000L, productService.getById(id).getCurrentPrice().getValue());

        //New price with the same value and different end date
        PriceRequestDto newPriceWithTheSameValue = new PriceRequestDto();
        newPriceWithTheSameValue.setPriceNumber(1);
        newPriceWithTheSameValue.setValue(1000L);
        newPriceWithTheSameValue.setDepartmentNumber(1);
        newPriceWithTheSameValue.setBegin(new Date());
        newPriceWithTheSameValue.setEnd(simpleDateFormat.parse("2025-10-12T00:00:00"));

        productService.addPrice(id, newPriceWithTheSameValue);

        //added the same value, price updated, no new price in list
        Assertions.assertEquals(1, productService.getById(id).getPrices().size());
        //the end date has been changed
        Assertions.assertEquals(simpleDateFormat.parse("2025-10-12T00:00:00"),
                productService.getById(id).getCurrentPrice().getEndDate());

        //new price with the different value and beginDate before previous endDate to make sure
        //that it's having been created and changing the dates correctly
        PriceRequestDto differentValue = new PriceRequestDto();

        differentValue.setPriceNumber(1);
        differentValue.setValue(2000L);
        differentValue.setDepartmentNumber(1);
        Date differentStart = simpleDateFormat.parse("2024-09-09T00:00:00");
        differentValue.setBegin(differentStart);
        Date differentEnd = simpleDateFormat.parse("2026-10-12T00:00:00");
        differentValue.setEnd(differentEnd);

        productService.addPrice(id, differentValue);

        //different value added, new price created
        Assertions.assertEquals(2, productService.getById(id).getPrices().size());
        //end date of current date changed to the start of the new price period
        Assertions.assertEquals(differentStart,
                productService.getById(id).getCurrentPrice().getEndDate());
    }

}
