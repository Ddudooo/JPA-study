package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * 주문 객체.
 */
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }
}