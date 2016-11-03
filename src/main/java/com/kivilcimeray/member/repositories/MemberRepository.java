package com.kivilcimeray.member.repositories;

import com.kivilcimeray.member.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anıl on 03.11.2016.
 */
@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
}
