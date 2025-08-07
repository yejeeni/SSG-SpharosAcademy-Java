package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> selectAll();
    public Board selectById(int boardId);
    public void insert(Board board);
    public int update(Board board);
    public void delete(int boardId);
}
