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
@Table(name = "teacher_info")

public class TeacherInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String positionInUniversity;
    private String workingExperience;
    private String photoPath;
    private Boolean confirmed;

    @OneToOne
    @MapsId
    private User user;

    @ManyToMany
    @JoinTable(name = "teacher_to_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<StudentInfo> students;

    @ManyToMany
    @JoinTable(name = "teacher_to_tag",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Resume> resumes;
}
