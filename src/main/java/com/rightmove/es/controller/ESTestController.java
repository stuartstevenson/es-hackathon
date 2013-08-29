package com.rightmove.es.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ESTestController {

    private Logger log = Logger.getLogger(HomeController.class);

    @RequestMapping("/es-test")
    public String showESTestPage() {
        log.debug("estest");
        return "esTest";
    }
}
