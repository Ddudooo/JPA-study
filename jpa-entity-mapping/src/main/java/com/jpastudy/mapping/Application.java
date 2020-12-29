package com.jpastudy.mapping;

import com.jpastudy.mapping.entity.Member;
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
            Team teamA = new Team("TeamA");
            em.persist(teamA);
            /*
            Team teamB = new Team("TeamB");
            em.persist(teamB);
            */
            Member member = new Member("member1");
            member.changeTeam(teamA);
            //teamA.addMember(member);

            em.persist(member);

            //em.flush();
            //em.clear();

            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("M name = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}