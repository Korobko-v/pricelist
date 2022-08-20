package ru.korobko.pricelist.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer priceNumber;

    @Column
    private Integer departmentNumber;

    @Column
    private Date beginDate;

    @Column
    private Date endDate;

    @Column
    private Long value;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public static int comparePricePriorities (Price p1, Price p2){
        if(p1.getPriceNumber() > p2.getPriceNumber())
            return 1;
        return -1;
    }
}
