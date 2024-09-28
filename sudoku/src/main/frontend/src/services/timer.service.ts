import { Subscription, timer } from "rxjs";
import { setTime } from "../stores/sudoku.store";
import { millisToTimeString } from "../utils/time";

const TimerRefreshInMillisec = 100;

export function startTimer(): Subscription {
    const startTime = Date.now()
    return timer(0, TimerRefreshInMillisec).subscribe(() => {
        const elapsedTime = Date.now() - startTime;
        const time = millisToTimeString(elapsedTime);
        setTime(time);
    })
}