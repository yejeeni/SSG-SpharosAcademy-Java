package com.ssg.xmlapp.controller;

import com.ssg.xmlapp.domain.Board;
import com.ssg.xmlapp.exception.BoardException;
import com.ssg.xmlapp.model.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getList(Model model) {
        List<Board> boards = boardService.selectAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/board/detail")
    public String getDetail(int boardId, Model model) {
        Board board = boardService.select(boardId);
        model.addAttribute("board", board);
        return "board/content";
    }

    @GetMapping("/board/registform")
    public String registForm(Model model){

        return "/board/write";
    }

    @PostMapping("/board/regist")
    public String regist(Board board) {
        boardService.insert(board);
        return "redirect:/board/list";
    }

    @PostMapping("/board/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:/board/detail?board_id=" + board.getBoardId();
    }

    @GetMapping("/board/delete")
    public String delete(int boardId) {
        boardService.delete(boardId);
        return "redirect:/board/list";
    }

    /**
     * 예외처리
     */
    @ExceptionHandler(BoardException.class)
    public ModelAndView handleException(BoardException e) {
        ModelAndView modelAndView = new ModelAndView("error/result");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}
