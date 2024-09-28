package hu.unideb.inf.sudoku.service;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;
import hu.unideb.inf.sudoku.mapper.SudokuMapper;
import hu.unideb.inf.sudoku.repository.SudokuRepository;
import org.springframework.stereotype.Service;


@Service
public class SudokuService {

    private final SudokuRepository repository;

    public SudokuService(SudokuRepository repository) {
        this.repository = repository;
    }

    public SudokuEntity getSudokuBoardById(long sudId) {
        return repository.findById(sudId).orElse(null);
    }

    public SudokuDTO getRandomBoard() {
        long idMax = repository.count();
        long rand = (long) (Math.random() * idMax) + 1;
        return SudokuMapper.mapToSudokuDTO(this.getSudokuBoardById(rand));
    }
}
