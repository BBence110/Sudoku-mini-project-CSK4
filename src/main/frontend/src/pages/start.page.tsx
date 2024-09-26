import { Link } from "react-router-dom";
import { Center } from "../components/center.component";

export function Start() {
    return (<Center className="gap-6">
        <h1 className="text-4xl">Sudoku</h1>
        <Link to={"/game"} className="text-4xl bg-black text-white py-1 px-11 rounded-full">Start</Link>
    </Center>);
}