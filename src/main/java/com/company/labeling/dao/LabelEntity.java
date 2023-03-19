package com.company.labeling.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
//@ToString(callSuper=true, includeFieldNames=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = ModelEntity.LABEL)
//@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class LabelEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "label_sequence",sequenceName = "label_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "label_sequence")
    @Column(name = "label_id_rd", nullable = false, unique = true)
    private Long id;
    @Column(name = "name_rd",length = 32,unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_label",
            joinColumns = @JoinColumn(name = "label_id_rd"),
            inverseJoinColumns = @JoinColumn(name = "note_id_rd"))
    @JsonIgnore
    private List<NoteEntity> noteEntities;

    public LabelEntity(Long id,String name){
        super();
        this.setId(id);
        this.setName(name);
    }
    public LabelEntity(String str){
        this.setName(str);
    }


    @Override
    public String toString() {
        return "LabelEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
