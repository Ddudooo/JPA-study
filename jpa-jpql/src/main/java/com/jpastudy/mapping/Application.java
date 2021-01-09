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
            Team team = new Team("teamA");
            em.persist(team);

            Member member = new Member("member1");
            member.setAge(10);
            member.changeTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m left join m.team t";
            //세터 조인
            //String query = "select m from Member m, Team t where m.name = t.name";
            //String query = "select m from Member m left join m.team t on t.name = 'teamA'";
            //String query = "select m from Member m left join Team t on m.name = t.name";
            /*String query = "select "
                + "case when m.age <= 10 then '학생요금' "
                + "case when m.age >= 60 then '경로요금' "
                + "else '일반요금' end"
                + "from Member m";*/
            List<Member> memberList = em
                .createQuery(query, Member.class)
                .getResultList();
            for (Member findMember : memberList) {
                System.out.println("member = " + findMember);
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