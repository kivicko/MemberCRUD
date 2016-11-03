package com.kivilcimeray.member.bootstrap;

import com.kivilcimeray.member.controllers.MemberController;
import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by anıl on 04.11.2016.
 */
@Component
public class JpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(JpaBootstrap.class);

    @Autowired
    MemberService memberService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Inside onApplicationEvent()");
        Member sample1 = new Member();
        sample1.setName("Kivilcim");
        sample1.setSurname("ERAY");
        sample1.setPhoneNumber("05331234567");
        sample1.setEmail("kivi@kivi.com");

        memberService.saveOrUpdate(sample1);
    }
}
