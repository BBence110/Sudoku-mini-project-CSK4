package hu.unideb.inf.sudoku.controller;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.mapper.SudokuMapper;
import hu.unideb.inf.sudoku.service.SudokuService;
import org.springframework.web.bind.annotation.*;


@RestController
public class SudokuController {
    private final SudokuService service;
    private static final long ID_MAX = 10; //utananezni

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @GetMapping("/getBoard")
    public SudokuDTO getRandomBoard() {
        long rand = (long) (Math.random() * ID_MAX) + 1;
        return SudokuMapper.mapToSudokuDTO(service.getSudokuBoardById(rand));
    }

    @GetMapping("/getBoard/{id}")
    public SudokuDTO getBoardById(@PathVariable long id) {
        return SudokuMapper.mapToSudokuDTO(service.getSudokuBoardById(id));
    }

    @PostMapping("/postResult")
    public SudokuDTO result(@RequestBody SudokuDTO dto) {
        //TODO
        return null;
    }
}
