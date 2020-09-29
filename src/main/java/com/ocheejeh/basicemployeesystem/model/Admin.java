package com.ocheejeh.basicemployeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "admin")
public class Admin {
    @Id
    private Long id;
    private String userName;
    private String password;

}
