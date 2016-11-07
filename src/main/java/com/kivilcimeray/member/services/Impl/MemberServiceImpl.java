package com.kivilcimeray.member.services.Impl;

import com.kivilcimeray.member.controllers.MemberController;
import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.repositories.MemberRepository;
import com.kivilcimeray.member.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anıl on 02.11.2016.
 */
@Service
public class MemberServiceImpl implements MemberService{

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    MemberRepository repository;

    @Override
    public List<Member> listAll() {
        logger.info("inside listAll() method");
        return repository.findAll();
    }

    @Override
    public Member getById(String id) {
        logger.info("inside getById() method id : " + id);
        return repository.findOne(id);
    }

    @Override
    public Member saveOrUpdate(Member member) {
        logger.info("inside saveOrUpdate() method member : " + member.toString());
        return repository.save(member);
    }

    @Override
    public void delete(String id) {
        logger.info("inside delete() method id : " + id);
        repository.delete(id);
    }
}
