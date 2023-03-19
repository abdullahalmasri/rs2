package com.company.labeling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = ModelEntity.NOTE)
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass
public class NoteEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "note_sequence",sequenceName = "note_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "note_sequence")
    @Column(name = "note_id_rd", nullable = false, unique = true)
    private Long id;
    @Column(name = "title_rd",length = 32)
    private String title;
    @Column(name = "content_rd",length = 300)
    private String content;

    @ManyToMany(mappedBy = "noteEntities",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<LabelEntity> labelEntity;

    public NoteEntity(Long id,String title,String content){
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);

    }  public NoteEntity(String title,String content){
        this.setTitle(title);
        this.setContent(content);

    }
    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +

                '}';
    }
}
