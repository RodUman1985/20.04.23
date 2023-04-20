package com.step.name.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ErrorPage")
public class ErorPageController {
    @PostMapping()
    public String getErorPage(){
        return  "Eror!!!Very big EROR!!!";
    }
}
