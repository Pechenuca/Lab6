package exception;

public class FileArgsException extends RuntimeException{
    public FileArgsException(){
        super("Объекты хранимые в файле содержат недопустимые Аргументы!");
    }
}