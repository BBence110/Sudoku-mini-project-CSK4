import { useEffect, useState } from "react";

function getTime(startTime: number): string {
    const timeElapsed = Date.now() - startTime;

    const sec = Math.floor(timeElapsed / 1000);
    const min = Math.floor(sec / 60);
    const minutes = (min % 60).toString().padStart(2, "0");
    const seconds = (sec % 60).toString().padStart(2, "0");
    const millis = (timeElapsed % 1000).toString().padStart(3, "0");

    return `${minutes}:${seconds}:${millis}`;
}

export function Timer() {
    const [startTime] = useState(Date.now());
    const [time, setTime] = useState("00:00:000");

    useEffect(() => {
        const interval = setInterval(() => setTime(getTime(startTime)), 100);

        return () => clearInterval(interval);
    }, []);

    return <p className="text-xl">{time}</p>
}