package hu.unideb.inf.sudoku.mapper;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuMapper {
    public static SudokuDTO mapToSudokuDTO(SudokuEntity entity) {
        List<Integer> nums = new ArrayList<>();
        String[] board = entity.getBoard().split(",");
        for (int i = 0; i < board.length; i++) {
            try {
                nums.add(Integer.parseInt(board[i]));
            } catch (NumberFormatException nfe) {
                System.out.println("Not a number!");
            }
        }
        return new SudokuDTO(nums, entity.getId());
    }

    public static SudokuEntity mapToSudokuEntity(SudokuDTO dto) {
        String boardString = dto.board().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return new SudokuEntity(dto.id(), boardString);
    }

}
