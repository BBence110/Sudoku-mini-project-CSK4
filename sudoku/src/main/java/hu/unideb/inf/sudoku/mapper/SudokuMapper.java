package hu.unideb.inf.sudoku.mapper;

import hu.unideb.inf.sudoku.data.SudokuDTO;
import hu.unideb.inf.sudoku.entity.SudokuEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SudokuMapper {

    /**
     * Splits the entity's board string by commas, converts each value to an integer and handles invalid numbers.
     *
     * @param entity object containing the board data as a string
     * @return a new SudokuDTO with the list of integers and the entity ID
     */
    public static SudokuDTO mapToSudokuDTO(SudokuEntity entity) {
        List<Integer> nums = new ArrayList<>();
        String[] board = entity.getBoard().split(",");
        for (String s : board) {
            try {
                nums.add(Integer.parseInt(s));
            } catch (NumberFormatException nfe) {
                System.out.println("Not a number!");
            }
        }
        return new SudokuDTO(nums, entity.getId());
    }

    /**
     * Joins the list of integers from the DTO's board into a comma separated string.
     *
     * @param dto contains the Sudoku board as a list of integers and ID which represents the entity.
     * @return a SudokuEntity containing the ID and the comma separated string.
     */
    public static SudokuEntity mapToSudokuEntity(SudokuDTO dto) {
        String boardString = dto.board().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return new SudokuEntity(dto.id(), boardString);
    }

}
