package com.company.labeling.data;

import com.company.labeling.dao.NoteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class LabelDto {

    private int id;
    private String name;
    private List<NoteEntity> noteEntities;

    @Override
    public String toString() {
        return "LabelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noteEntities=" + noteEntities +
                '}';
    }
}
