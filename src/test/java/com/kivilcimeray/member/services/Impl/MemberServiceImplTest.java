package com.kivilcimeray.member.services.Impl;

import com.kivilcimeray.member.config.TestApplicationConfig;
import com.kivilcimeray.member.controllers.MemberController;
import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.repositories.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by anıl on 06.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplicationConfig.class)
public class MemberServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @InjectMocks
    MemberServiceImpl memberService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAll() throws Exception {
        List<Member> memberList = new ArrayList<>();
        Member sample1 = new Member();
        sample1.setName("Kivilcim");
        sample1.setSurname("ERAY");
        sample1.setPhoneNumber("5331234567");
        sample1.setEmail("kivi@kivi.com");
        memberList.add(sample1);
        memberList.add(new Member());
        memberList.add(new Member());
        memberList.add(new Member());

        when(memberRepository.findAll()).thenReturn(memberList);

        assert memberService.listAll() != null;
        assertEquals(4, memberService.listAll().size());
        assertEquals("Kivilcim", memberService.listAll().get(0).getName());
        assertEquals("ERAY", memberService.listAll().get(0).getSurname());
        assertEquals("5331234567", memberService.listAll().get(0).getPhoneNumber());
        assertEquals("kivi@kivi.com", memberService.listAll().get(0).getEmail());
    }

    @Test
    public void testGetById() throws Exception {
        String id = "123";
        Member sample1 = new Member();
        sample1.setId(id);

        when(memberRepository.findOne(id)).thenReturn(sample1);

        assert memberService.getById(id) != null;
        assertEquals(id, memberService.getById(id).getId());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Member returnMember = new Member();
        String id = "abcdefgh";
        String firstName = "Anıl";
        String lastName = "ERAY";
        String phoneNumber = "5431234567";
        String email = "kivilcim@kivilcim.com";

        returnMember.setId(id);
        returnMember.setName(firstName);
        returnMember.setSurname(lastName);
        returnMember.setEmail(email);
        returnMember.setPhoneNumber(phoneNumber);

        when(memberRepository.save(Matchers.<Member>any())).thenReturn(returnMember);

        assert memberService.saveOrUpdate(new Member()) != null;
        assertEquals(memberService.saveOrUpdate(new Member()).getEmail(), returnMember.getEmail());
        assertEquals(memberService.saveOrUpdate(new Member()).getId(), returnMember.getId());
        assertEquals(memberService.saveOrUpdate(new Member()).getName(), returnMember.getName());
        assertEquals(memberService.saveOrUpdate(new Member()).getPhoneNumber(), returnMember.getPhoneNumber());
        assertEquals(memberService.saveOrUpdate(new Member()).getSurname(), returnMember.getSurname());
    }

    @Test
    public void testDelete() throws Exception {
        String id = "123";
        Member deleteMember = new Member();

        memberService.delete(id);

        verify(memberRepository, times(1)).delete(id);
    }

}