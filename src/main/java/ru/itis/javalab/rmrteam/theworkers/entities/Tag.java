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
@Table(name = "tag")
public class Tag implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<TeacherInfo> teachers;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<StudentInfo> students;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<CompanyInfo> companies;
}
