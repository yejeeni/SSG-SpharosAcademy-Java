package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;

import java.util.List;

public interface BoardService {
    public List<Board> selectAll();
    public Board selectById(int boardId);
    public void insert(Board board);
    public int update(Board board);
    public void delete(int boardId);
}
