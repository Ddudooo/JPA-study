package com.jpastudy.mapping.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PRIVATE;

/**
 * 주소 관련 임베디드 타입.
 */
@Getter
@Setter(value = PRIVATE)
@Embeddable
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}