package hu.unideb.inf.sudoku.mapper;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuMapperTest {
    @Test
    void mapToSudokuDTO() {
        SudokuEntity input = new SudokuEntity(1L, "1,2,3,4,5,6,7,8");
        SudokuDTO expected = new SudokuDTO(List.of(1, 2, 3, 4, 5, 6, 7, 8), 1L);
        SudokuDTO actual = SudokuMapper.mapToSudokuDTO(input);
        assertEquals(expected, actual);
    }

    @Test
    void mapToSudokuEntity() {
        SudokuDTO input = new SudokuDTO(List.of(1, 2, 3, 4, 5, 6, 7, 8), 1L);
        SudokuEntity expected = new SudokuEntity(1L, "1,2,3,4,5,6,7,8");
        SudokuEntity actual = SudokuMapper.mapToSudokuEntity(input);
        assertEquals(expected, actual);
    }
}