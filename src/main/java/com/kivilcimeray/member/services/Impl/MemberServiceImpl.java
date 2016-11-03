package com.kivilcimeray.member.services.Impl;

import com.kivilcimeray.member.models.Member;
import com.kivilcimeray.member.repositories.MemberRepository;
import com.kivilcimeray.member.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anıl on 02.11.2016.
 */
@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository repository;

    @Override
    public List<Member> listAll() {
        return repository.findAll();
    }

    @Override
    public Member getById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Member saveOrUpdate(Member member) {
        return repository.save(member);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
