package exception;

import java.io.ObjectInputStream;

public class OrgFormatException extends RuntimeException {
    public OrgFormatException() {
        super("Эта организация имеет проблемы с форматом");
    }
}
