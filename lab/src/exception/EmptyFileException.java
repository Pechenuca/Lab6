package exception;

import java.io.IOException;

public class EmptyFileException extends IOException {
    /**
     * Конструктор - создает объект класса EmptyFileException
     * @param s - сообщение об исключении
     * @see EmptyFileException#EmptyFileException(s)
     */
    public EmptyFileException(String s) {
        super(s);
    }
}