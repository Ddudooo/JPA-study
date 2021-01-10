package com.jpastudy.mapping;

import com.jpastudy.mapping.entity.Member;
import com.jpastudy.mapping.entity.MemberType;
import com.jpastudy.mapping.entity.Team;

import java.util.List;
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
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            Team teamC = new Team("teamC");

            em.persist(teamA);
            em.persist(teamB);
            em.persist(teamC);

            Member member = new Member("member1");
            member.setAge(10);
            member.changeTeam(teamA);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member("member2");
            member2.setAge(10);
            member2.changeTeam(teamA);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);

            Member member3 = new Member("member3");
            member3.setAge(10);
            member3.changeTeam(teamB);
            member3.setType(MemberType.ADMIN);
            em.persist(member3);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age=20")
                .executeUpdate();

            em.clear();

            System.out.println("resultCount = " + resultCount);

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.age = " + findMember.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}