import { useEffect } from "react";
import { checkSudokuBoard, getSudokuBoard } from "../services/sudoku.service";
import { Timer } from "../components/timer.component";
import { Center } from "../components/center.component";
import { setSudokuState, useSudoku, useSudokuState } from "../stores/sudoku.store";

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
    const state = useSudokuState();
    const sudoku = useSudoku();

    useEffect(() => {

        getSudokuBoard();

        const sub = checkSudokuBoard().subscribe((isWinner) => {
            // do something when user is winner
            console.log(isWinner);
        });

        return () => sub.unsubscribe();
    }, []);

    if (state.isLoading) {
        return <Center><p className="text-4xl">Loading...</p></Center>;
    }

    if (state.error || !sudoku) {
        return (<Center>
            <p className="text-4xl">Failed to load a board :(</p>
        </Center>)
    }

    const onChange = async (boardCell: number, value: string) => {
        let num: number | null;
        if (value === "") {
            num = null
        } else {
            num = Number(value[value.length - 1])
            if (Number.isNaN(num) || num <= 0 || num > 4) num = sudoku.board[boardCell];
        }
        sudoku.board[boardCell] = state.initialBoard![boardCell] ?? num;
        state.sudoku = sudoku;
        setSudokuState({ ...state });
    }

    return (<Center className="gap-3">
        <div>
            <p className="text-xl">Your time:</p>
            <Timer />
        </div>
        <div className="aspect-square max-w-96 max-h-96 px-4">
            <div className="grid grid-cols-4 grid-rows-4 border-4 border-black rounded-3xl w-full h-full">
                {sudoku.board.map((value, i) =>
                    <input
                        key={i}
                        disabled={state.initialBoard![i] !== null}
                        onChange={(ev) => { ev.preventDefault(); onChange(i, ev.target.value); }}
                        type="text" pattern="[0-9]" inputMode="numeric"
                        className={"border-black text-center text-4xl" + (state.initialBoard![i] !== null ? " bg-slate-300" : " bg-transparent") + getBorderRight(i) + getBorderBottom(i)}
                        value={value ?? ""}
                    />
                )}
            </div>
        </div>
    </Center>);
}