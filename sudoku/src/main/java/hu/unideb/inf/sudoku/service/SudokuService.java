package hu.unideb.inf.sudoku.service;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;
import hu.unideb.inf.sudoku.mapper.SudokuMapper;
import hu.unideb.inf.sudoku.repository.SudokuRepository;
import org.springframework.stereotype.Service;


@Service
public class SudokuService {

    /**
     * This is a repository for accessing and managing data.
     */
    private final SudokuRepository repository;

    /**
     * Creates a new SudokuService with the given repository.
     *
     * @param repository This is used for data access.
     */
    public SudokuService(SudokuRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrives a SudokuEntity by its ID.
     *
     * @param sudId ID of the sudoku board.
     * @return the SudokuEntity if found, or null.
     */
    public SudokuEntity getSudokuBoardById(long sudId) {
        return repository.findById(sudId).orElse(null);
    }

    /**
     * Retrives a random Sudoku board as SudokuDTO.
     *
     * @return a randomly selected SudokuDTO, or null if the board not exist.
     */
    public SudokuDTO getRandomBoard() {
        long idMax = repository.count();
        long rand = (long) (Math.random() * idMax) + 1;
        return SudokuMapper.mapToSudokuDTO(this.getSudokuBoardById(rand));
    }
}
