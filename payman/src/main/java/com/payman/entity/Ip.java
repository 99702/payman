package com.payman.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Ip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="address")
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name="fk_customer", referencedColumnName = "id")
    private Customer customer;
}