package com.jpastudy.mapping;

import com.jpastudy.mapping.entity.Member;
import com.jpastudy.mapping.entity.Team;

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
            Member member = new Member("hello");
            Team team = new Team("team");
            em.persist(team);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("m = "+ findMember.getTeam().getClass());
            System.out.println("==============");
            findMember.getTeam().getName();
            System.out.println("==============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}