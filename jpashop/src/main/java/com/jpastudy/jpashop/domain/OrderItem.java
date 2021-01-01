package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 주문 정보 객체.
 */
@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    //@Column(name = "ORDER_ID")
    //private Long orderId;
    @JoinColumn(name = "ORDER_ID")
    @ManyToOne
    private Order order;
    //@Column(name = "ITEM_ID")
    //private Long itemId;
    @JoinColumn(name = "ITEM_ID")
    @ManyToOne
    private Item item;
    private int orderPrice;
    private int count;
}