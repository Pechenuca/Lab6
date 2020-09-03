package command;

import managers.CollectionManager;
import managers.FileManager;

public interface ExecutionContext {
    CollectionManager collectionManager();
    FileManager fileManager();
    StringBuilder result();
}