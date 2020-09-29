package com.ocheejeh.basicemployeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employees")
public class Employee
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "first name must be valid")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "last name must be valid")
    @Column(name = "last_name")
    private String lastName;

    final String regexEmailRFC5322Standard = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Pattern(regexp = regexEmailRFC5322Standard, message = "email address must be valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "position field is required")
    private String position;

    @NotBlank(message = "address field is required")
    private String address;

}
