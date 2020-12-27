package com.jpastudy.ex1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 테스트용 간단한 엔티티 모델.
 */
@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    //JPA 상에서 내부적으로 리플렉션 활용해서 사용하여 기본 생성자가 필요함
    protected Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}