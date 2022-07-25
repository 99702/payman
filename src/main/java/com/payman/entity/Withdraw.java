package com.payman.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="withdraw")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="from_fk_account", referencedColumnName = "id")
    private Account from;

    private BigDecimal amount;


    private String ip;
    private double latitude;
    private double longitude;
    private String screen;
    private String userAgent;
    private String clientDateTime;
    private String language;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="to_fk_account", referencedColumnName = "id")
    private Account to;

}
