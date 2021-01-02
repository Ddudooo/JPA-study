package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Item 확장 정보.
 * <p>앨범 관련 정보</p>
 *
 * @see Item
 */
@Getter
@Setter
@Entity
public class Album extends Item {

    private String artist;
    private String etc;
}