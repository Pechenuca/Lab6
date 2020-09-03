package main.command;

import main.CollectionManager;
import main.FileManager;

public interface ExecutionContext {
    CollectionManager collectionManager();
    FileManager fileManager();
    StringBuilder result();
}