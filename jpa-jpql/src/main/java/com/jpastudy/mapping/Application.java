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

            //fetch 조인
            String query = "select m from Member m join fetch m.team";
            //조인
            //String query = "select m from Member m left join m.team t";
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
                Team memberTeam = findMember.getTeam();
                System.out.println("team = " + memberTeam.getName());
            }

            em.clear();
            //일대다 fetch 조인의 경우 값이 n개
            //team A 중복
            //query = "select t from Team t join fetch t.members";

            query = "select distinct t from Team t join fetch t.members";

            List<Team> teamList = em
                .createQuery(query, Team.class)
                .getResultList();
            for (Team findTeam : teamList) {
                System.out.println("team = " + findTeam);
                List<Member> findTeamMembers = findTeam.getMembers();
                for(Member findMember : findTeamMembers) {
                    System.out.println("member = " + findMember);
                }
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