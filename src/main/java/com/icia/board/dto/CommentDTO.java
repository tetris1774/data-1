package com.icia.board.dto;

import com.icia.board.entity.CommentEntity;
import com.icia.board.entity.LikeEntity;
import com.icia.board.util.UtilClass;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private String createdAt;
    private String updatedAt;
    private int isLike = 0;
    private int likeCount;

    public static CommentDTO toDTO(CommentEntity commentEntity, List<LikeEntity> likeEntityList) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCreatedAt(UtilClass.dateTimeFormat(commentEntity.getCreatedAt()));
        commentDTO.setUpdatedAt(UtilClass.dateTimeFormat(commentEntity.getUpdatedAt()));
        // 좋아요 수
        commentDTO.setLikeCount(commentEntity.getLikeEntityList().size());
        // 로그인 회원의 좋아요 여부(좋아요를 했다면 isLike 값을 1로)
        for (LikeEntity likeEntity: likeEntityList) {
            if (likeEntity.getCommentEntity().getId().equals(commentEntity.getId())) {
                commentDTO.setIsLike(1);
            }
        }
        return commentDTO;
    }
}
