package com.ssg.xmlapp.model.board;

import com.ssg.xmlapp.domain.Board;

import java.util.List;

public interface BoardDAO {
    public List<Board> selectAll();
    public Board select(int boardId);
    public void insert(Board board);
    public int update(Board board);
    public void delete(int boardId);
}
