package exception;

public class OverflowException extends RuntimeException {
    OverflowException(){
        super("Количество уникальный значений ID закончилось, уменьште количество рабочих!");
    }
}