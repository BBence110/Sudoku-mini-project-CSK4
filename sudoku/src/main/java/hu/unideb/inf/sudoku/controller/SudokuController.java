package hu.unideb.inf.sudoku.controller;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.mapper.SudokuMapper;
import hu.unideb.inf.sudoku.service.SudokuService;
import hu.unideb.inf.sudoku.util.Solver;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SudokuController {
    private final SudokuService service;
    private Solver solver;

    private static final long ID_MAX = 3; //utananezni

    public SudokuController(SudokuService service, Solver solver) {
        this.service = service;
        this.solver = solver;
    }

    //TODO
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
    public boolean result(@RequestBody List<Integer> board) {
        return solver.isSolved(board);
    }
}
