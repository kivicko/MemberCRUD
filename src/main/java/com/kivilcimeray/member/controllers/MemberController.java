package com.kivilcimeray.member.controllers;

import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    MemberService service;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        logger.info("inside mainPage()");
        model.addAttribute("members", service.listAll());
        model.addAttribute("member", new Member());

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid Member member, BindingResult bindingResult, Model model) {
        logger.info("inside saveOrUpdate()");
        if (bindingResult.hasErrors()) {
            logger.warn("saveOrUpdate methods Member object has some errors." + bindingResult.toString());
            return "redirect:/";
        }
        service.saveOrUpdate(member);

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String updateMember(@PathVariable String id, Model model) {
        logger.info("inside displayMember()");
        model.addAttribute("member", service.getById(id));

        return "index :: display-member";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteMember(@PathVariable String id, Model model) {
        logger.info("inside deleteMember() id : " + id);
        service.delete(id);

        return "redirect:/";
    }
}
