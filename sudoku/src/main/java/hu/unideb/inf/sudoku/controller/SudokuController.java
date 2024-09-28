package hu.unideb.inf.sudoku.controller;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.mapper.SudokuMapper;
import hu.unideb.inf.sudoku.service.SudokuService;
import hu.unideb.inf.sudoku.util.Solver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling Sudoku-related API requests.
 */
@RestController
public class SudokuController {
    private final SudokuService service;
    private final Solver solver;

    /**
     * Creates a new SudokuController.
     *
     * @param service SudokuService to retrieve and manage Sudoku boards.
     * @param solver  Solver to validate Sudoku solutions.
     */
    public SudokuController(SudokuService service, Solver solver) {
        this.service = service;
        this.solver = solver;
    }

    /**
     * Handles GET requests to /api/getBoard.
     *
     * @return A random sudoku board mapped to SudokuDTO.
     */
    @GetMapping("/api/getBoard")
    public SudokuDTO getRandomBoard() {
        return service.getRandomBoard();
    }

    /**
     * Handles GET requests to /api/getBoard/{id}.
     *
     * @param id The id of the board to send.
     * @return A sudoku board mapped to SudokuDTO.
     */
    @GetMapping("/api/getBoard/{id}")
    public SudokuDTO getBoardById(@PathVariable long id) {
        return SudokuMapper.mapToSudokuDTO(service.getSudokuBoardById(id));
    }

    /**
     * Handles POST requests to /api/postResult.
     * Validates whether the provided Sudoku board is correctly solved.
     *
     * @param board A list of integers representing the state of board.
     * @return true if the board is solved correctly, false otherwise.
     */
    @PostMapping("/api/postResult")
    public boolean result(@RequestBody List<Integer> board) {
        return solver.isSolved(board);
    }
}
