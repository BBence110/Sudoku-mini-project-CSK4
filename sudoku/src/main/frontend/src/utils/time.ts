export function millisToTimeString(time: number): string {
    const sec = Math.floor(time / 1000);
    const min = Math.floor(sec / 60);
    const minutes = (min % 60).toString().padStart(2, "0");
    const seconds = (sec % 60).toString().padStart(2, "0");
    const millis = (time % 1000).toString().padStart(3, "0");

    return `${minutes}:${seconds}:${millis}`;
}