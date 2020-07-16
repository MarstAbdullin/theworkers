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
@Table(name = "student_info")

public class StudentInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    private Integer courseNumber;
    private String specialty;
    private String firstName;
    private String secondName;
    private String photoPath;
    private Boolean confirmed;

    @OneToOne
    @MapsId
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private Set<TeacherInfo> teachers;

    @ManyToMany
    @JoinTable(name = "student_to_tag",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Resume> resumes;

}
