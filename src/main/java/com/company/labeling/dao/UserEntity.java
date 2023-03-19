package com.company.labeling.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_rd")
@Data
public class UserEntity{

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @Column(name = "user_id_rd", nullable = false, unique = true)
    private int id;
    @Column(name = "name_rd",length = 32)
    private String name;
    @Column(name = "user_name_rd",length = 32)
    private String username;
    @Column(name = "password_rd")
    private int password;

}
