package hu.unideb.inf.sudoku.repository;

import hu.unideb.inf.sudoku.entity.SudokuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SudokuRepository extends JpaRepository<SudokuEntity, Long> {
    long count();
}
