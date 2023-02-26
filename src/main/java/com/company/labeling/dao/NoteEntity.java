package com.company.labeling.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ModelEntity.NOTE)
@Data
public class NoteEntity {
    @Id
    @SequenceGenerator(name = "note_sequence",sequenceName = "note_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "note_sequence")
    @Column(name = "note_id_rd", nullable = false, unique = true)
    private Long id;
    @Column(name = "title_rd",length = 32)
    private String title;
    @Column(name = "content_rd",length = 300)
    private String content;

    @ManyToMany(mappedBy = "noteEntities",fetch = FetchType.EAGER)
    private List<LabelEntity> labelEntity;

}
