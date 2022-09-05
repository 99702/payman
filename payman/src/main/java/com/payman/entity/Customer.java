package com.payman.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.payman.enumeration.Gender;
import com.payman.enumeration.Province;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name", nullable = false)
    @NotBlank(message = "full name cannot be blank")
    @Size(min = 2, max = 32, message = "full name must be between 2 and 32 characters long")
    private String fullName;


    @Column(name="dob",  nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name="province",  nullable = false)
    @Enumerated(EnumType.STRING)
    private Province province;


    @Column(name="mobile", unique = true, nullable = false)
    @NotBlank(message = "district cannot be blank")
    private String mobile;

    @Column(name="citizenship_no",  nullable = false, unique = true)
    @NotBlank(message = "citizenship cannot be blank")
    private String citizenshipNo;

    @Column(name="enabled")
    private  Boolean enabled = false;

    @Column(name="password",  nullable = false, length=1000)
    @NotBlank(message = "password cannot be blank")
    private String password;

    @Column(name = "type")
    private String type = "customer";

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="reset_password_token")
    private String resetPasswordToken;
}