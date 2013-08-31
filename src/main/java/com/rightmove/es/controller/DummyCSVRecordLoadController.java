package com.rightmove.es.controller;

import com.rightmove.es.repositories.DummyCSVRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyCSVRecordLoadController {
    @Autowired
    private DummyCSVRecordRepository dummyCSVRecordRepository;

    @RequestMapping("/dummy-load")
    public String doDummyLoad() {
        return "dummyLoad";
    }
}
