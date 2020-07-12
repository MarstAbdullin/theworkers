package ru.itis.javalab.rmrteam.theworkers.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToOne
    @MapsId
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "student_info")
    private List<TeacherInfo> teachers;

    @ManyToMany
    @JoinTable(name = "student_to_tag",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @OneToMany(mappedBy = "student_info", cascade = CascadeType.ALL)
    private List<Resume> resumes;

}
