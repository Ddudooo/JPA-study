package com.jpastudy.mapping;

import com.jpastudy.mapping.dto.MemberDTO;
import com.jpastudy.mapping.entity.Member;

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
            Member member = new Member("member1");
            member.setAge(10);
            em.persist(member);
            List<MemberDTO> memberDTOList = em.createQuery(
                "select new com.jpastudy.mapping.dto.MemberDTO(m.name, m.age) from Member m where m.name = 'member1'",
                MemberDTO.class).getResultList();
            for (MemberDTO memberDTO : memberDTOList) {
                System.out.println("member dto = " + memberDTO);
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