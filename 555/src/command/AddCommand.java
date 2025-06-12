package command;

import manager.CollectionManager;
import manager.InputManager;
import model.LabWork;

import java.util.Scanner;

/**
 * Команда add : добавить новый элемент в коллекцию.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        LabWork labWork = InputManager.readLabWork(scanner);
        collectionManager.add(labWork);
        System.out.println("Элемент успешно добавлен.");
    }
}
