package com.icia.board.service;

import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.LikeDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.entity.CommentEntity;
import com.icia.board.entity.LikeEntity;
import com.icia.board.entity.MemberEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.repository.CommentRepository;
import com.icia.board.repository.LikeRepository;
import com.icia.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public Long save(CommentDTO commentDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(commentDTO.getCommentWriter()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = CommentEntity.toSaveEntity(memberEntity, boardEntity, commentDTO);
        return commentRepository.save(commentEntity).getId();
    }


    /**
     *  댓글 목록을 화면에 출력할 때
     *  로그인한 회원이 좋아요를 한 댓글에는 ♥
     *  좋아요를 하지 않은 댓글에는 ♡ 로 다르게 표시
     */
    @Transactional
    public List<CommentDTO> findAll(Long memberId, Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());

        // 해당 게시글의 댓글 목록
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);

        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());

        // 로그인 회원이 해당 게시글에 좋아요를 한 좋아요 목록
        List<LikeEntity> likeEntityList = likeRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity);

        // 댓글 DTO 변환시 로그인한 회원이 각 댓글에 좋아요를 했냐 안했냐를 따져야 함
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(comment -> {
            commentDTOList.add(CommentDTO.toDTO(comment, likeEntityList));
        });
        return commentDTOList;
    }

    // 좋아요 여부 체크
    public boolean likeCheck(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndCommentEntity(memberEntity, commentEntity);
        if (optionalLikeEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // 좋아요 처리
    public Long like(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        LikeEntity likeEntity = LikeEntity.toLikeEntity(memberEntity, boardEntity, commentEntity);
        return likeRepository.save(likeEntity).getId();
    }

    // 좋아요 취소 처리
    @Transactional
    public void unLike(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        likeRepository.deleteByMemberEntityAndCommentEntity(memberEntity, commentEntity);
    }
}
