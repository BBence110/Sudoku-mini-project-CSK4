import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
    createBrowserRouter,
    RouterProvider,
    redirect,
} from "react-router-dom";
import { Game } from './pages/game.page';
import "./index.css";
import { Start } from './pages/start.page';
import { Subscribe } from '@react-rxjs/core';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Start />
    },
    {
        path: "/game",
        element: <Game />
    },
    {
        path: "*",
        loader: () => redirect("/")
    }
])

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <Subscribe>
            <RouterProvider router={router} />
        </Subscribe>
    </StrictMode>,
)