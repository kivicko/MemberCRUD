package com.kivilcimeray.member.controllers;

import com.kivilcimeray.member.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anıl on 02.11.2016.
 */
@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

}
