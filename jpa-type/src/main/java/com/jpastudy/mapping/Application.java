package com.jpastudy.mapping;

import com.jpastudy.mapping.entity.Address;
import com.jpastudy.mapping.entity.AddressEntity;
import com.jpastudy.mapping.entity.Member;

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
            member.setHomeAddress(new Address("home", "street", "zip"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "zip"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "zip"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==================START=======================");
            Member findMember = em.find(Member.class, member.getId());

            /*
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String favoriteFood : favoriteFoods){
                System.out.println("favorite food = " + favoriteFood);
            }
            */
            Address oldHome = findMember.getHomeAddress();
            findMember
                .setHomeAddress(new Address("newHome", oldHome.getStreet(), oldHome.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("보쌈");

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "zip"));
            findMember.getAddressHistory().add(new AddressEntity("old3", "street", "zip"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}