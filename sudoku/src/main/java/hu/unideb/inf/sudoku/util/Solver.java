package hu.unideb.inf.sudoku.util;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;

/**
 * TODO: check for input errors
 */
@Component
public class Solver {
    private static final int BOARD_SIZE = 4;
    private static final int SUBGRID_SIZE = 2;
    private int[][] board;

    public boolean isSolved(List<Integer> nums) {
        initBoard(nums);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(!isValid(i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initBoard(List<Integer> nums) {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        int k = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = nums.get(k);
                System.out.println(nums.get(k));
                k++;
            }
        }
    }


    private boolean isValid(int row, int col, int num) {
        //Check in row
        for (int i = 0; i < BOARD_SIZE; i++) {
            if(board[i][col] == num && i != row) {
                System.out.println("row");
                return false;
            }
        }

        //Check in col
        for (int j = 0; j < BOARD_SIZE; j++) {
            if(board[row][j] == num && j != col) {
                System.out.println("col");
                return false;
            }
        }


        HashSet<Integer> uniqueNums = new HashSet<>();
        //Check in subgrid
        int startRow = (row / SUBGRID_SIZE) * SUBGRID_SIZE;
        int startCol = (col / SUBGRID_SIZE) * SUBGRID_SIZE;
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                uniqueNums.add(board[startRow + i][startCol + j]);
            }
        }
        if(uniqueNums.size() == 4) {
            return true;
        } else {
            return false;
        }
    }
}
