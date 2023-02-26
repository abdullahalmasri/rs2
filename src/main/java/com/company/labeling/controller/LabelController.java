package com.company.labeling.controller;

import com.company.labeling.data.LabelDto;
import com.company.labeling.services.LabelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
public class LabelController {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @RequestMapping(value = "/createLabel", method = RequestMethod.POST)
    @ResponseBody
    public LabelDto createNote(@RequestBody LabelDto labelDto) {
        labelService.createLabel(labelDto);
        return labelDto;
    }

}
