package com.payman.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="balance")
@Data
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account", referencedColumnName = "id", unique = true)
    private Account account;
    private BigDecimal balance = new BigDecimal(0);
}