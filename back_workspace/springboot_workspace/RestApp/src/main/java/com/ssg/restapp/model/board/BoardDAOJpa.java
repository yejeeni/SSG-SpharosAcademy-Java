package com.ssg.restapp.model.board;

import com.ssg.restapp.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

//@Repository("jpaBoardDAO")
@Slf4j
public class BoardDAOJpa implements BoardDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Board> selectAll() {
        return entityManager.createQuery("select b from Board b").getResultList();
    }

    @Override
    public Board selectById(int boardId) {
        return entityManager.find(Board.class, boardId);
    }

    @Override
    public void insert(Board board) {
        entityManager.persist(board);
    }

    @Override
    public int update(Board board) {
        return 0;
    }

//    @Override
//    public Board update(Board board) {
//        return entityManager.merge(board);
//    }

    @Override
    public void delete(int boardId) {
        Board board = entityManager.find(Board.class, boardId);
        if (board != null){
            entityManager.remove(board);
        }
    }
}
