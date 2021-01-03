package com.jpastudy.mapping.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;

/**
 * 엔티티 공통 정보.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}