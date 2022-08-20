package ru.korobko.pricelist.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Price> prices;

    /**
     * Check if the product has a current price (began and not expired)
     * @return {@code true} if the product ahs a current price, {@code false} otherwise
     */
    public boolean hasCurrentPrice() {
        for (Price price : this.getPrices()) {
            Date now = new Date();
            if (!price.getBeginDate().after(now) && !price.getEndDate().before(now)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get current price
     * @return price
     */
    public Price getCurrentPrice() {
            return this.prices
                    .stream()
                    .filter(price -> !price.getBeginDate().after(new Date())
                            && !price.getEndDate().before(new Date()))
                    .min(Price::comparePricePriorities).orElseThrow();
    }

    /**
     * Checks if the product has price with given value and updates the price or creates a new one
     * @param price new price
     * @return created or updated price
     */
    public Price checkAndCreateOrUpdatePrice(Price price) {
        if (this.getPrices() == null || this.getPrices().isEmpty()) {
            List<Price> prices = new ArrayList<>();
            prices.add(price);
            this.setPrices(prices);
            return price;
        }
        for (Price pr : this.getPrices()) {
            if (pr.getValue().equals(price.getValue())) {
                pr.setEndDate(price.getEndDate());
                return pr;
            }
        }

        Price currentPrice = this.getCurrentPrice();
        currentPrice.setEndDate(price.getBeginDate());
        List<Price> prices = this.getPrices();
        prices.add(price);
        this.setPrices(prices);
        return price;
    }

}
