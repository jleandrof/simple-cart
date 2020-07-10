package com.example.simpleecommerce.products.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue()
    private Long id = 0L;

    private final String uuid = UUID.randomUUID().toString();

    @Column(columnDefinition = "varchar(280)")
    private String name;

    @Column(columnDefinition = "varchar(512)")
    private String description;

    @Range(min = 0)
    private Long price;

    private Boolean visible;

    private final Date entryDate = new Date();

    public Product(String name, String description, Long price, Boolean visible) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.visible = visible;
    }
}
