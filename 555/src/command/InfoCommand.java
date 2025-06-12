package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда info : вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).
 */
public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getSimpleName());
        System.out.println("Дата инициализации: " + collectionManager.getCreationDate());
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
    }
}