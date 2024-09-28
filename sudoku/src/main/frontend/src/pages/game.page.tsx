import { useEffect } from "react";
import { checkSudokuBoard, getSudokuBoard } from "../services/sudoku.service";
import { Timer } from "../components/timer.component";
import { Center } from "../components/center.component";
import { isWinner$, setSudoku, time$, useError, useInitialBoard, useIsLoading, useIsWinner, useSudoku } from "../stores/sudoku.store";
import { filter, Subscription } from "rxjs";
import { startTimer } from "../services/timer.service";

function Winner() {
    return (<Center>
        <p className="text-4xl">Congrats! You've won the game in {time$.getValue()}!</p>
    </Center>);
}

function getBorderRight(i: number) {
    if (i % 4 == 1) return " border-r-4";
    if (i % 4 == 3) return "";
    return " border-r-2";
}

function getBorderBottom(i: number) {
    if (4 <= i && i <= 7) return " border-b-4";
    if (11 < i) return "";
    return " border-b-2";
}

export function Game() {
    const sudoku = useSudoku();
    const initialBoard = useInitialBoard();
    const isLoading = useIsLoading();
    const isWinner = useIsWinner();
    const error = useError();

    useEffect(() => {
        const subs = new Subscription;

        getSudokuBoard();
        subs.add(checkSudokuBoard());
        const timerSub = startTimer();
        subs.add(timerSub);
        subs.add(isWinner$.pipe(filter(w => w)).subscribe(timerSub.unsubscribe));

        return () => subs.unsubscribe();
    }, []);

    if (isLoading) {
        return <Center><p className="text-4xl">Loading...</p></Center>;
    }

    if (error || !sudoku || !initialBoard) {
        return (<Center>
            <p className="text-4xl">{error?.message ?? "Failed to load a board :("}</p>
        </Center>)
    }

    if (isWinner) {
        return <Winner />
    }

    const onChange = async (boardCell: number, value: string) => {
        let num: number | null;
        if (value === "" || value === "Backspace") {
            num = null
        } else {
            num = Number(value[value.length - 1])
            if (Number.isNaN(num) || num <= 0 || num > 4) num = sudoku.board[boardCell];
        }
        sudoku.board[boardCell] = initialBoard[boardCell] ?? num;
        setSudoku({ ...sudoku });
    }

    return (<Center className="gap-3">
        <div>
            <p className="text-xl">Your time:</p>
            <Timer />
        </div>
        <div className="aspect-square max-w-96 max-h-96 px-4">
            <div className="grid grid-cols-4 grid-rows-4 border-4 border-black dark:border-slate-600 rounded-3xl w-full h-full">
                {sudoku.board.map((value, i) =>
                    <input
                        key={i}
                        disabled={initialBoard[i] !== null}
                        onChange={() => { }}
                        onKeyUp={(ev) => { ev.preventDefault(); onChange(i, ev.key); }}
                        type="text" pattern="[0-9]" inputMode="numeric"
                        className={"border-black dark:border-slate-600 text-center text-4xl" + (initialBoard[i] !== null ? " bg-slate-300 dark:bg-slate-900" : " bg-transparent") + getBorderRight(i) + getBorderBottom(i)}
                        value={value ?? ""}
                    />
                )}
            </div>
        </div>
    </Center>);
}