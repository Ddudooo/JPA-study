package com.jpastudy.mapping;

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

            /*
            List<Member> memberList = em
                .createQuery("select m from Member m where m.name like '%kim%'", Member.class)
                .getResultList();
            */

            /*
            //알아보기가 힘듬.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);

            String name = "kim";
            if (name != null) {
                cq = cq.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member> memberList = em.createQuery(cq).getResultList();
            */
            /*
            List<Member> memberList = em.createNativeQuery(
                "select MEMBER_ID, city, street, zipcode from MEMBER where name like '%kim%'",
                Member.class).getResultList();
             */

            Member member = new Member("member1");
            member.setAge(10);
            em.persist(member);

            /*
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10", Member.class);

            List<Member> result = query.getResultList();
            //결과가 정확히 하나 일때, 아니면 에러 발생.
            Member singleResult = query.getSingleResult();
            */
            /*
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.name = :username", Member.class);
            query.setParameter("username", "member1");

            Member singleResult = query.getSingleResult();
            */
            Member singleResult = em
                .createQuery("select m from Member m where m.name = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
            System.out.println("single = " + singleResult.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}