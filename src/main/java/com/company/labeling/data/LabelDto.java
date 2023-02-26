package com.company.labeling.data;

import com.company.labeling.dao.NoteEntity;
import lombok.Data;

import java.util.List;

@Data
public class LabelDto {

    private int id;
    private String name;
    private List<NoteEntity> noteEntities;
}
