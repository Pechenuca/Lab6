import com.sun.org.slf4j.internal.LoggerFactory;

public class Client {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("My Logger");
        Factory factory = new Factory();
        CommandValidation commandValidation = new CommandValidation();
    }
}
