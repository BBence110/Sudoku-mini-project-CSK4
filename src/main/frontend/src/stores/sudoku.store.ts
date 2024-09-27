import { bind } from "@react-rxjs/core";
import { createSignal } from "@react-rxjs/utils";
import { Sudoku } from "../models/sudoku.model";
import { Board } from "../models/board.model";
import { map } from "rxjs";

export type SudokuState = {
    isLoading: boolean;
    error?: Error;
    initialBoard?: Board;
    sudoku?: Sudoku;
};

export const [sudokuStateChange$, setSudokuState] = createSignal<SudokuState>();

export const [useSudokuState, sudokuState$] = bind(sudokuStateChange$, { isLoading: true });
export const [useSudoku, sudoku$] = bind(sudokuState$.pipe(map(s => s.sudoku)), undefined);
