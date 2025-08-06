package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 30)
    private String nickName;

    @Column(length=50, nullable = false, unique = true)
    private String uid;

    @Column(length = 100)
    private String pic;

    @Column(length = 100,  nullable = false)
    private String upw;
}
