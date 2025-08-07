package com.ssg.restapp.controller;

import com.ssg.restapp.domain.Board;
import com.ssg.restapp.model.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

    /**
     * 게시판 목록 요청 처리
     */
    @GetMapping("/boards")
    public List<String> selectAll(){
        log.debug("selectAll");
        List<String> list = new ArrayList();
        list.add("apple");
        list.add("banana");
        list.add("grape");
        return list;
    }

    /**
     * 글쓰기 요청
     */
    @PostMapping("/boards")
    public ResponseEntity<String> regist(@RequestBody Board board){ // json 문자열로 전송된 파라미터와 서버 모델을 자동 매칭
        boardService.insert(board);
        return ResponseEntity.ok("success");
    }

    /**
     * 글 단건 요청
     */
    @GetMapping("/boards/{boardId}")
    public Board select(@PathVariable int boardId){
        return boardService.selectById(boardId);
    }

    /**
     * 글 수정
     */
    @PutMapping("/boards/{boardId}")
    public ResponseEntity<String> update(@RequestBody Board board, @PathVariable int boardId){ // json 문자열로 전송된 파라미터와 서버 모델을 자동 매칭
        board.setBoardId(boardId);
        boardService.update(board);
        return ResponseEntity.ok("success");
    }

    /**
     * 글 삭제
     */
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<String> delete(@PathVariable int boardId){ // json 문자열로 전송된 파라미터와 서버 모델을 자동 매칭
        boardService.delete(boardId);
        return ResponseEntity.ok("success");
    }

}
