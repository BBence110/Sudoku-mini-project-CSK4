package hu.unideb.inf.sudoku.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    @Test
    void isSolved() {
        List<Integer> input = Arrays.asList(2,3,4,1,4,1,3,2,1,4,2,3,3,2,1,4);
        Solver solver = new Solver();
        boolean actual = solver.isSolved(input);
        assertTrue(actual);
    }

    static Stream<List<Integer>> incorrectBoards() {
        return Stream.of(
                Arrays.asList(3,3,4,1,4,1,3,2,1,4,2,3,3,2,1,4), // 0th is same as in subgrid and row
                Arrays.asList(2,3,4,1,3,1,3,2,1,4,2,3,3,2,1,4), // 4th is same as in subgrid and row
                Arrays.asList(2,3,4,1,2,1,3,2,1,4,2,3,3,2,1,4)  // 4th is same as in subgrid and column
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectBoards")
    void isNotSolved(List<Integer> input) {
        Solver solver = new Solver();
        boolean actual = solver.isSolved(input);
        assertFalse(actual);
    }
}