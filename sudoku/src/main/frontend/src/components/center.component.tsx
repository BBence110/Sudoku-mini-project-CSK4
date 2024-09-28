import { ComponentPropsWithoutRef, PropsWithChildren } from "react";

export function Center({ children, className }: PropsWithChildren<ComponentPropsWithoutRef<"div">>) {
    return (<div className={["h-dvh w-dvw flex items-center justify-center flex-col", className ?? ""].join(" ")}>
        {children}
    </div>);
}