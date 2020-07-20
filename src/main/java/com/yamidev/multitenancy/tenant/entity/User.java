package com.yamidev.multitenancy.tenant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Size(max = 100)
    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Size(max = 50)
    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;
    @Size(max = 100)
    @Column(name = "password",nullable = false)
    private String password;
    @Size(max = 10)
    @Column(name = "status",nullable = false)
    private String status;
}
