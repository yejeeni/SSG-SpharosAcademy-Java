package com.ssg.xmlapp.model.board;

import com.ssg.xmlapp.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardDAO boardDAO;

    @Override
    public List<Board> selectAll() {
        return boardDAO.selectAll();
    }

    @Override
    public Board select(int boardId) {
        return boardDAO.select(boardId);
    }

    @Override
    public void insert(Board board) {
        boardDAO.insert(board);
    }

    @Override
    public int update(Board board) {
        return boardDAO.update(board);
    }

    @Override
    public void delete(int boardId) {
        boardDAO.delete(boardId);
    }
}
