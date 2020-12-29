package com.jpastudy.mapping.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 테스트용 간단한 엔티티 모델.
 * <p>
 * 테스트용으로 Getter, Setter 제한을 두지 않음
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String name;
    /*
    @Column(name = "TEAM_ID")
    private Long TeamId;
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
        team.getMembers().add(this);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}