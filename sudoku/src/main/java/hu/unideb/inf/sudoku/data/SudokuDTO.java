package hu.unideb.inf.sudoku.data;

import java.util.List;

/**
 * Record class for Data Transfer Objects.
 *
 * @param board State of the board.
 * @param id Board identifier.
 */
public record SudokuDTO(List<Integer> board, long id) {
}
