package com.icia.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "like_table")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;

    public static LikeEntity toLikeEntity(MemberEntity memberEntity, BoardEntity boardEntity, CommentEntity commentEntity) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setMemberEntity(memberEntity);
        likeEntity.setBoardEntity(boardEntity);
        likeEntity.setCommentEntity(commentEntity);
        return likeEntity;
    }
}
