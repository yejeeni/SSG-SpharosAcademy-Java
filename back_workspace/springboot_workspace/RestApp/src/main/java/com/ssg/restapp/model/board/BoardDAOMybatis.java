package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;
import com.ssg.restapp.exception.BoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@RequiredArgsConstructor
@Repository("boardDAOMybatis")
public class BoardDAOMybatis implements BoardDAO{

    private final BoardMapper boardMapper;

    @Override
    public List<Board> selectAll() {
        return boardMapper.selectAll();
    }

    @Override
    public Board selectById(int boardId) {
        return boardMapper.selectById(boardId);
    }

    @Override
    public void insert(Board board) throws BoardException {
        try {
            boardMapper.insert(board);
        } catch (DataAccessException e) {
            throw new BoardException("게시물 등록 실패", e);
        }
    }

    @Override
    public int update(Board board) throws BoardException {
        try {
            return boardMapper.update(board);
        } catch (DataAccessException e) {
            throw new BoardException("게시물 수정 실패", e);
        }
    }

    @Override
    public void delete(int boardId) throws BoardException {
        try {
            boardMapper.delete(boardId);
        } catch (DataAccessException e) {
            throw new BoardException("게시물 삭제 실패", e);
        }
    }
}
