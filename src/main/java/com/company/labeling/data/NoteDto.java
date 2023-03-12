package com.company.labeling.data;

import com.company.labeling.dao.LabelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private List<LabelEntity> labelEntity;

    @Override
    public String toString() {
        return "NoteDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labelEntity=" + labelEntity +
                '}';
    }
}
