package com.icia.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@Table(name = "board_file_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // DB에 생성될 참조 컬럼의 이름
    private BoardEntity boardEntity; // 부모 엔티티 타입으로 정의

    public static BoardFileEntity toSaveBoardFile(BoardEntity savedEntity, String originalFilename, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFilename);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(savedEntity);
        return boardFileEntity;
    }
}
