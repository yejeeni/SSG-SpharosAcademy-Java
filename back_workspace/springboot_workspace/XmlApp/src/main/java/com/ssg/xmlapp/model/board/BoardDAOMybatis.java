package com.ssg.xmlapp.model.board;

import com.ssg.xmlapp.domain.Board;
import com.ssg.xmlapp.exception.BoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAOMybatis implements BoardDAO{

    private final BoardMapper boardMapper;

    @Override
    public List<Board> selectAll() {
        return boardMapper.selectAll();
    }

    @Override
    public Board select(int boardId) {
        return boardMapper.select(boardId);
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
