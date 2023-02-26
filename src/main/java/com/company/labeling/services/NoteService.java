package com.company.labeling.services;

import com.company.labeling.data.NoteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    List<NoteDto> findAllNote();

    void saveListOfNote(List<NoteDto> noteDtos);

    void deleteNote(Long noteDto);

    NoteDto createNote(NoteDto noteDto);

    void editNote(NoteDto noteDto);

    Page<NoteDto> findPageData(Pageable pageable);

}
