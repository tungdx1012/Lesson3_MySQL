package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "horse")
public class Horse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "foaled")
    private Date foaled;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    private Trainer trainer;

    public Horse(int id, String name, Date foaled) {
        this.id = id;
        this.name = name;
        this.foaled = foaled;
    }
}
