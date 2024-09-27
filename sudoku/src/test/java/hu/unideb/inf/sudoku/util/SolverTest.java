package hu.unideb.inf.sudoku.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    List<Integer> nums = Arrays.asList(2,
            3,
            4,
            1,
            4,
            1,
            3,
            2,
            1,
            4,
            2,
            3,
            3,
            2,
            1,
            4);
    @Test
    void isSolved() {
        Solver solver = new Solver();
        assertTrue(solver.isSolved(nums));
    }
}