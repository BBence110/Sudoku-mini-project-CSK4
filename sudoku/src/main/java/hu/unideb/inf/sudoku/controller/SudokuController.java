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
    private final Solver solver;

    public SudokuController(SudokuService service, Solver solver) {
        this.service = service;
        this.solver = solver;
    }

    @GetMapping("/api/getBoard")
    public SudokuDTO getRandomBoard() {
        return service.getRandomBoard();
    }

    @GetMapping("/api/getBoard/{id}")
    public SudokuDTO getBoardById(@PathVariable long id) {
        return SudokuMapper.mapToSudokuDTO(service.getSudokuBoardById(id));
    }

    @PostMapping("/api/postResult")
    public boolean result(@RequestBody List<Integer> board) {
        return solver.isSolved(board);
    }
}
