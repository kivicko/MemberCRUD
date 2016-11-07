package com.kivilcimeray.member.integration;

import com.kivilcimeray.member.config.TestApplicationConfig;
import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.services.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by anıl on 07.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplicationConfig.class)
public class MemberIT {

    @Autowired
    MemberService memberService;

    @Test
    public void should_save_member() {
        String id = "123";
        String name = "samplename";
        String surname = "samplesurname";
        String phonenumber = "9999999999";
        String email = "email@email.com";

        Member sample1 = new Member();
        sample1.setId(id);
        sample1.setEmail(email);
        sample1.setName(name);
        sample1.setSurname(surname);
        sample1.setPhoneNumber(phonenumber);

        Member savedMember = memberService.saveOrUpdate(sample1);

        assert memberService.getById(id) != null;
        // It is 2 because i do not mock default service. and we got bootstrap user which uses this service before us.
        assert memberService.listAll().size() == 2;
        assertEquals(id, savedMember.getId());
        assertEquals(name, savedMember.getName());
        assertEquals(surname, savedMember.getSurname());
        assertEquals(email, savedMember.getEmail());
        assertEquals(phonenumber, savedMember.getPhoneNumber());
    }

    @Test
    public void should_remove_bootstrapped_sample_member() {
        String id = "123";
        Member sample1 = new Member();
        sample1.setId(id);

        // saving new member
        memberService.saveOrUpdate(sample1);
        assert memberService.listAll().size() != 1;
        assert memberService.getById(id) != null;

        // deleting member
        memberService.delete(id);
        assert memberService.listAll().size() == 1;
        assert memberService.getById(id) == null;
    }

    @Test
    public void should_update_member_email() {
        String id = "123";
        String firstEmail = "first@email.com";
        String lastEmail = "last@email.com";
        Member sample1 = new Member();
        sample1.setId(id);
        sample1.setEmail(firstEmail);

        //Saving member
        memberService.saveOrUpdate(sample1);
        assert memberService.listAll().size() != 1;
        assert memberService.getById(id) != null;
        assertEquals(firstEmail, memberService.getById(id).getEmail());

        //Updating member
        sample1.setEmail(lastEmail);
        memberService.saveOrUpdate(sample1);
        assert memberService.listAll().size() != 1;
        assert memberService.getById(id) != null;
        assertEquals(lastEmail, memberService.getById(id).getEmail());



    }
}
