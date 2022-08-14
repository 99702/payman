package com.payman.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Keys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Lob
    private String RSAKey;
}
