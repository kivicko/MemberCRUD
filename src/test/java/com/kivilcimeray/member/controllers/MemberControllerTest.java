package com.kivilcimeray.member.controllers;

import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.services.MemberService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by anıl on 06.11.2016.
 */
public class MemberControllerTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

    }

    @Test
    public void testMainPage() throws Exception {
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member());
        memberList.add(new Member());
        memberList.add(new Member());

        when(memberService.listAll()).thenReturn(memberList);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("members", hasSize(3)))
                .andExpect(model().attribute("member", instanceOf(Member.class)));
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

        when(memberService.saveOrUpdate(Matchers.<Member>any())).thenReturn(returnMember);

        mockMvc.perform(post("/")
                .param("id", id)
                .param("email", email)
                .param("name", firstName)
                .param("surname", lastName)
                .param("phoneNumber", phoneNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(model().attribute("member", instanceOf(Member.class)))
                .andExpect(model().attribute("member", hasProperty("id", is(id))))
                .andExpect(model().attribute("member", hasProperty("name", is(firstName))))
                .andExpect(model().attribute("member", hasProperty("surname", is(lastName))))
                .andExpect(model().attribute("member", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("member", hasProperty("email", is(email))));

    }

    @Test
    public void testUpdateMember() throws Exception {
        String id = "1";

        when(memberService.getById(Matchers.<String>any())).thenReturn(new Member());

        mockMvc.perform(get("/"+id))
                .andExpect(status().isOk())
                .andExpect(view().name("index :: display-member"))
                .andExpect(model().attribute("member",instanceOf(Member.class)));
    }

    @Test
    public void testDeleteMember() throws Exception {
        String id = "1";

        mockMvc.perform(post("/" + id + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(memberService, times(1)).delete(id);
    }

}