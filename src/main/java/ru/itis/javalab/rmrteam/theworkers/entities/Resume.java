package ru.itis.javalab.rmrteam.theworkers.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resume")
public class Resume implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resumeName;
    private String age;
    private String university;
    private Boolean workingInProject;
    private Boolean workingFulltime;
    private String city;
    private Boolean readyToRelocation;
    private Boolean sex;
    private String citizenship;
    private String workingExperience;
    private String description;
    private String phoneNumber;
    private String email;
    private String careerObjective;
    private String languages;
    private Boolean confirmedByTeacher;
    private Long studentId;
    private Long teacherId;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<Certificate> certificates;
}
