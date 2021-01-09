package com.jpastudy.mapping.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

/**
 * Member DTO 객체.
 *
 * @see com.jpastudy.mapping.entity.Member
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberDTO {

    private String name;
    private int age;

    public MemberDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }
}