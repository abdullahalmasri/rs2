package com.company.labeling.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDto implements Serializable {

    private Long id;
    private String name;
    private List<NoteDto> noteDtos;

    @Override
    public String toString() {
        return "LabelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes=" + noteDtos +
                '}';
    }
}
