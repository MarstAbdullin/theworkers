package ru.itis.javalab.rmrteam.theworkers.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "certificate")
public class Certificate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String certificateName;
    private String certificatePath;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
