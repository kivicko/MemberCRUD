package com.kivilcimeray.member.services.Impl;

import com.kivilcimeray.member.config.IntegrationConfig;
import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.services.MemberService;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by anıl on 06.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IntegrationConfig.class)
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;


    @Test
    public void testListAll() throws Exception {
        List<Member> memberList = (List<Member>) memberService.listAll();
        System.out.println(memberList.size());
        assert memberList.size() != 1;
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testSaveOrUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

}