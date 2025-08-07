package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;

import java.util.List;

/**
 * BOARD DAO 인터페이스
 */
public interface BoardDAO {
    public List<Board> selectAll();
    public Board selectById(int boardId);
    public void insert(Board board);
    public int update(Board board);
    public void delete(int boardId);
}
