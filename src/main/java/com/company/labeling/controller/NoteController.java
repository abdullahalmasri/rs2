package com.company.labeling.controller;

import com.company.labeling.data.NoteDto;
import com.company.labeling.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@Slf4j
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/createNote", method = RequestMethod.POST)
    @ResponseBody
    public NoteDto createNote(@RequestBody NoteDto noteDto) {
        noteService.createNote(noteDto);
        return noteDto;
    }

    @RequestMapping(value = "/EditNote", method = RequestMethod.POST)
    @ResponseBody
    public NoteDto editNote(@RequestBody NoteDto noteDto) {
        noteService.editNote(noteDto);
        return noteDto;
    }


    @RequestMapping(value = "/DeleteNote", method = RequestMethod.POST)
    @ResponseBody
    public void delNote(@RequestBody NoteDto noteDto) {
        noteService.deleteNote(noteDto.getId());
    }

    @RequestMapping(value = "/pageData", method = RequestMethod.GET)
    @ResponseBody
    public Page getPageData(
                            @RequestParam() int page,
                            @RequestParam() int size) {

        Pageable paging = PageRequest.of(page, size);

            return noteService.findPageData(paging);

    }


}
