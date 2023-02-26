package com.company.labeling.data;

import com.company.labeling.dao.LabelEntity;
import lombok.Data;

import java.util.List;

@Data
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private List<LabelEntity> labelEntity;
}
