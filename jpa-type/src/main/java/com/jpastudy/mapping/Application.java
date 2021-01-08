package com.jpastudy.mapping;

import com.jpastudy.mapping.entity.Address;
import com.jpastudy.mapping.entity.Member;
import com.jpastudy.mapping.entity.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 간단한 JPA 예제용 클래스.
 */
public class Application {

    /**
     * 메인 메소드.
     *
     * @param args 실행 변수.
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member("test");
            member.setAddress(new Address("city", "street", "zip"));
            member.setPeriod(new Period());
            em.persist(member);

            Address oldAddress = member.getAddress();
            Address newAddress = new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}