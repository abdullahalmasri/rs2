package com.company.labeling.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ModelEntity.LABEL)
@Data
public class LabelEntity {
    @Id
    @SequenceGenerator(name = "label_sequence",sequenceName = "label_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "label_sequence")
    @Column(name = "label_id_rd", nullable = false, unique = true)
    private int id;
    @Column(name = "name_rd",length = 32)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "note_label",
            joinColumns = @JoinColumn(name = "label_id_rd"),
            inverseJoinColumns = @JoinColumn(name = "note_id_rd"))
    @JsonIgnore
    private List<NoteEntity> noteEntities;


}
