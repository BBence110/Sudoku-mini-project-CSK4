import { useTime } from "../stores/sudoku.store";


export function Timer() {
    const time = useTime();

    return <p className="text-xl">{time}</p>
}