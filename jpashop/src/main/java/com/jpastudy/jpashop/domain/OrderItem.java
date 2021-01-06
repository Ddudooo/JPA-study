package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

/**
 * 주문 정보 객체.
 */
@Getter
@Setter
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    @JoinColumn(name = "ORDER_ID")
    @ManyToOne(fetch = LAZY)
    private Order order;
    @JoinColumn(name = "ITEM_ID")
    @ManyToOne(fetch = LAZY)
    private Item item;
    private int orderPrice;
    private int count;
}