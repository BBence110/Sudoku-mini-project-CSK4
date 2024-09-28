import { catchError, debounceTime, filter, map, of, Subscription, switchMap } from "rxjs";
import { setError, setInitialBoard, setIsLoading, setIsWinner, setSudoku, sudoku$ } from "../stores/sudoku.store";
import { ajax } from "rxjs/ajax";
import { SudokuDto } from "../models/dto/sudoku";
import { Sudoku } from "../models/sudoku.model";
import { Result } from "../models/result.model";

export function getSudokuBoard() {
    setIsLoading(true);

    ajax<SudokuDto>({
        method: "GET",
        url: "/api/getBoard",
        responseType: "json"
    }).pipe(
        map(sudokuResp => {
            const sudoku: Sudoku = {
                board: sudokuResp.response.board.map(n => n ? n : null),
                id: sudokuResp.response.id
            };
            return Result.Ok(sudoku);
        }),
        catchError(err => of(Result.Err(err)))
    ).subscribe((sudokuRes) => {
        setIsLoading(false);
        if (sudokuRes.isOk) {
            const sudoku = sudokuRes.value;
            setInitialBoard([...sudoku.board]);
            setSudoku(sudoku);
        } else {
            setError(new Error("Failed to load a board :("));
        }
    })
}

const BoardCheckDebounceInMillis = 100;

export function checkSudokuBoard(): Subscription {
    return sudoku$.pipe(
        filter(s => !!s?.board),
        filter(s => s!.board.every(cell => cell)),
        debounceTime(BoardCheckDebounceInMillis),
        switchMap(sudoku => {
            return ajax<boolean>({
                method: "POST",
                url: "/api/postResult",
                body: sudoku?.board
            }).pipe(map(resp => Result.Ok(resp.response)), catchError(err => {
                console.error(err);
                return of(Result.Err(err));
            }))
        })
    ).subscribe(isWinnerResp => {
        if (isWinnerResp.isOk) {
            setIsWinner(isWinnerResp.value);
        } else {
            setError(new Error("Failed to check if board is correct."))
        }
    });
}