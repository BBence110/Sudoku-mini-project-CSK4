import { BoardDto } from "./board";

export interface SudokuDto {
    id: string;
    board: BoardDto;
    size: number;
}