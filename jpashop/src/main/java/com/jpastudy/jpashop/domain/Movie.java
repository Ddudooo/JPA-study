package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Item 확장 정보.
 * <p>영화 관련 정보</p>
 *
 * @see Item
 */
@Getter
@Setter
@Entity
public class Movie extends Item {

    private String director;
    private String actor;
}