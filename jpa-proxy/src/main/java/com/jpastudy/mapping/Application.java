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
            Member member2 = new Member("hello2");

            Team team = new Team("team");
            member.setTeam(team);
            team.getMembers().add(member);
            em.persist(member);
            em.persist(member2);
            em.persist(team);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            //System.out.println("m1== m2" + (findMember.getClass() == findMember2.getClass()));

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember));

            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("findMember.team.name = " + findMember.getTeam().getName());
            System.out.println("===========================================================");
            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("findMember.team.name = " + findMember.getTeam().getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}