package com.company.labeling.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoteDto implements Serializable {
    private Long id;
    private String title;
    private String content;
    private List<LabelDto> labels;

    public NoteDto(Long id,String title,String content){
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
    }
    public NoteDto(String title,String content){
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labels=" + labels +
                '}';
    }
}
