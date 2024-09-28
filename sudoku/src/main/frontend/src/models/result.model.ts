interface Ok<OkType> {
    isOk: true;
    value: OkType;
}

interface Err<ErrType> {
    isOk: false;
    err: ErrType;
}

export type ResultType<OkType, ErrType> = Ok<OkType> | Err<ErrType>;

export class Result {
    public static Ok<OkType>(value: OkType): Ok<OkType> {
        return { isOk: true, value };
    }

    public static Err<ErrType>(err: ErrType): Err<ErrType> {
        return { isOk: false, err };
    }
}