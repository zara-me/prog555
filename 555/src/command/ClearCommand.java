package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда clear : очистить коллекцию.
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        collectionManager.clear();
        System.out.println("Коллекция очищена.");
    }
}
