package com.jpastudy.ex1;

import com.jpastudy.ex1.entity.Member;
import com.jpastudy.ex1.entity.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 간단한 JPA 예제용 클래스.
 */
public class Ex1Main {

    /**
     * 메인 메소드.
     *
     * @param args 실행 변수.
     */
    public static void main(String[] args) {
        // "hello" 인 persistence 유닛을 찾아서 엔티티 매니저 팩토리를 생성
        // 해당 이름으로 영속성 관리할 팩토리 클래스 생성
        // 에플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 팩토리 클래스에서 영속성 관리객체 생성
        EntityManager em = emf.createEntityManager();

        // 영속성 관리 객체에서 트랜잭션 가져옴
        EntityTransaction tx = em.getTransaction();
        // 모든 로직상 트랙잭션 안에서 수행되어야함
        tx.begin();
        try {
            // 생성
            /*
            Member testMember = new Member();
            testMember.setId(1L);
            testMember.setName("testMember");
            // 생성한 엔티티 영속화
            em.persist(testMember);
            */
            // 변경
            /*           
            Member findedMember = em.find(Member.class, 1L);
            findedMember.setName("testMemberUpdate");
            //변경 감지후 따로 저장 호출 안해도 커밋시 체크하여 업데이트
            //em.persist(findedMember);
            */
            // JPQL
            // SQL 문법과 유사
            // JPQL 은 엔티티 객체를 대상으로 쿼리 -> 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            // SQL을 추상화 하여 특정 데이터 베이스 SQL에 의존 X
            // SQL 은 데이터 베이스 테이블을 대상으로 쿼리
            /*
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                .getResultList();
            for(Member member : resultList){
                System.out.println("member name - " + member.getName());
            }
            */
            // 1차 캐시 확인
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("TestCached");

            em.persist(member);

            Member findMember = em.find(Member.class, 2L);
            System.out.println("Find Member "+findMember.getName());
            //두번째 조회시에는 캐시에서 가져옴으로 쿼리가 나가면 안됨
            Member findMember2 = em.find(Member.class, 2L);
            System.out.println("Find Member "+findMember2.getName());
            //영속 엔티티의 동일성 보장
            System.out.println("is equal ? "+(findMember == findMember2));
            */
            // 엔티티 쓰기 지연 확인
            /*
            //엔티티 생성
            Member memberA = new Member(150L, "A");
            Member memberB = new Member(160L, "B");
            // 영속화
            em.persist(memberA);
            em.persist(memberB);
            */
            // 변경 감지
            /*
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
            
            // 필요없는 호출 -> 호출하지 않는게 좋음
            // em.persist(member);
            */

            // 준영속 테스트
            /*
            Member member = em.find(Member.class, 150L);
            member.setUsername("AAAA");

            // 준 영속 상태
            //em.detach(member);
            //em.clear();

            //em.persist(member);
            //강제로 플러시 호출 -> 이시점에서 디비 반영
            em.flush();
            */
            Member member = new Member("testA");
            member.setAge(10);
            member.setRoleType(RoleType.ADMIN);

            em.persist(member);

            System.out.println("=== BEFORE ===");
            // 트랜잭션 커밋
            tx.commit();
            System.out.println("=== AFTER ===");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}