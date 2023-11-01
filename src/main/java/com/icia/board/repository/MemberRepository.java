package com.icia.board.repository;

import com.icia.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // select * from member_table where member_email = ?
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    // select * from member_table where member_email = ? and member_password = ?
    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);
}