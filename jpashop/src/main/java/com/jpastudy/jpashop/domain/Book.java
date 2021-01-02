package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Item 확장 정보.
 * <p>책 관련 정보</p>
 *
 * @see Item
 */
@Getter
@Setter
@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}