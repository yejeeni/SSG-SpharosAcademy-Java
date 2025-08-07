package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;
import com.ssg.restapp.exception.BoardException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    public BoardServiceImpl(@Qualifier("boardDAOMybatis") BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<Board> selectAll() {
        return boardDAO.selectAll();
    }

    @Override
    public Board selectById(int boardId) {
        return boardDAO.selectById(boardId);
    }

    @Override
    public void insert(Board board) throws DataAccessException{
        try {
            boardDAO.insert(board);
        } catch (DataAccessException e) { // 데이터베이스에 중립적인 exception
            throw new BoardException("글 등록 실패", e);
        }
    }

    @Override
    public int update(Board board) throws DataAccessException {
        try {
            return boardDAO.update(board);
        } catch (DataAccessException e) {
            throw new BoardException("업데이트 실패", e);
        }
    }

    @Override
    public void delete(int boardId) throws DataAccessException{
        try {
            boardDAO.delete(boardId);
        } catch (DataAccessException e) {
            throw new BoardException("업데이트 실패", e);
        }
    }
}
