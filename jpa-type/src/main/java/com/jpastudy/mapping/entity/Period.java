package com.jpastudy.mapping.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.persistence.Embeddable;

/**
 * 기간 관련 임베디드 타입.
 */
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isWork() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            return true;
        }
        return false;
    }
}