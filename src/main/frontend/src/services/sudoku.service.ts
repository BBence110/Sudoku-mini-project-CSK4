import { debounceTime, filter, map, Observable } from "rxjs";
import { setSudokuState, sudokuState$ } from "../stores/sudoku.store";

export function getSudokuBoard() {
    setSudokuState({
        isLoading: true,
    });

    setTimeout(() =>
        setSudokuState({
            isLoading: false,
            initialBoard: [
                null, 2, null, null,
                null, null, 1, null,
                3, null, null, 4,
                null, null, null, null
            ],
            sudoku: { // TODO: Change fake data
                id: '1234',
                board: [
                    null, 2, null, null,
                    null, null, 1, null,
                    3, null, null, 4,
                    null, null, null, null
                ],
            }
        }),
        500
    );

}

const boardCheckDebounceInMillis = 500;

export function checkSudokuBoard(): Observable<boolean> {
    return sudokuState$.pipe(
        map((s) => s.sudoku?.board),
        filter(b => !!b),
        filter(b => b.every(cell => cell)),
        debounceTime(boardCheckDebounceInMillis),
        map(_board => {
            // TODO: check with server
            return false;
        })
    );
}