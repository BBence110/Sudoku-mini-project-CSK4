import { bind } from "@react-rxjs/core";
import { createSignal } from "@react-rxjs/utils";
import { Sudoku } from "../models/sudoku.model";
import { Board } from "../models/board.model";

export const [sudokuChange$, setSudoku] = createSignal<Sudoku>();
export const [useSudoku, sudoku$] = bind(sudokuChange$, undefined);

export const [initialBoardChange$, setInitialBoard] = createSignal<Board>();
export const [useInitialBoard, initialBoard$] = bind(initialBoardChange$, undefined);

export const [errorChange$, setError] = createSignal<Error>();
export const [useError, error$] = bind(errorChange$, undefined);

export const [isLoadingChange$, setIsLoading] = createSignal<boolean>();
export const [useIsLoading, isLoading$] = bind(isLoadingChange$, true);

export const [isWinnerChange$, setIsWinner] = createSignal<boolean>();
export const [useIsWinner, isWinner$] = bind(isWinnerChange$, false);

export const [timeChange$, setTime] = createSignal<string>();
export const [useTime, time$] = bind(timeChange$, "00:00:000");
