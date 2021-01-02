package com.jpastudy.mapping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 상속 받는 객체 매핑용.
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