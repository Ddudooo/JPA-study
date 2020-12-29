package com.jpastudy.jpashop;

import com.jpastudy.jpashop.domain.Order;
import com.jpastudy.jpashop.domain.OrderItem;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 테스트용 어플리케이션.
 */
@Slf4j
public class Application {

    /**
     * 메인 메소드.
     *
     * @param args 파라미터
     */
    public static void main(String[] args) {
        log.info("Application start...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Order order = new Order();
            //order.addOrderItem(new OrderItem());
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

