package hu.unideb.inf.sudoku.mapper;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuMapperTest {
    SudokuEntity se = new SudokuEntity(1L, "1,2,3,4,5,6,7,8");
    List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    SudokuDTO sd = new SudokuDTO(nums, 1L);
    @Test
    void mapToSudokuDTO() {
        assertEquals(sd, SudokuMapper.mapToSudokuDTO(se));
    }

    @Test
    void mapToSudokuEntity() {
        //assertEquals(se, SudokuMapper.mapToSudokuEntity(sd));
    }
}