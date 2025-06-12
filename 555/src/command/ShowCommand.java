package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда show : вывести все элементы коллекции.
 */
public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            collectionManager.getCollection().forEach(System.out::println);
        }
    }
}
