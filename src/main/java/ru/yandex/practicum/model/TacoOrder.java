package ru.yandex.practicum.model;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder implements Serializable {


    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt = new Date();

    @Column(value = "delivery_name")
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @Column(value = "delivery_street")
    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @Column(value = "delivery_city")
    @NotBlank(message = "City is required")
    private String deliveryCity;

    @Column(value = "delivery_state")
    @NotBlank(message = "State id required")
    private String deliveryState;

    @Column(value = "delivery_zip")
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @Column(value = "cc_number")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column(value = "cc_expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Column(value = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
