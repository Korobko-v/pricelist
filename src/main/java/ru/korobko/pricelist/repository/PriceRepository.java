package ru.korobko.pricelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korobko.pricelist.domain.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
