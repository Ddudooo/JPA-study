package com.jpastudy.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;

/**
 * 엔티티 공통속성.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}