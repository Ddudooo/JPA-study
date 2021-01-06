package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;

/**
 * 주문의 배달 정보 객체.
 */
@Getter
@Setter
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}