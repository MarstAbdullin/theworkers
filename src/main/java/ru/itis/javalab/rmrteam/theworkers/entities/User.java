package ru.itis.javalab.rmrteam.theworkers.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_all")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String hash;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CompanyInfo company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentInfo student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TeacherInfo teacher;
}