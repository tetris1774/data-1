package com.icia.board.controller;

import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.LikeDTO;
import com.icia.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CommentDTO commentDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        try {
            commentService.save(commentDTO);
            List<CommentDTO> commentDTOList = commentService.findAll(memberId, commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/like")
    public ResponseEntity like(@RequestBody LikeDTO likeDTO) {
        boolean checkResult = commentService.likeCheck(likeDTO);
        if (checkResult)
            commentService.like(likeDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(likeDTO.getMemberId(), likeDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping("/unlike")
    public ResponseEntity unLike(@RequestBody LikeDTO likeDTO) {
        commentService.unLike(likeDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(likeDTO.getMemberId(), likeDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

}
