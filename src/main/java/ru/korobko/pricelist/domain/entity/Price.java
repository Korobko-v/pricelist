package ru.korobko.pricelist.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productCode;

    @Column
    private Integer priceNumber;

    @Column
    private Integer departmentNumber;

    @Column
    private Date begin;

    @Column
    private Date end;

    @Column
    private Long value;

    @ManyToOne
    @JoinColumn(name="product_code", nullable=false)
    private Product product;
}
