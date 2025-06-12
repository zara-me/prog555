package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда filter_contains_name : вывести элементы, значение поля name которых содержит заданную подстроку.
 */
public class FilterContainsNameCommand implements Command {
    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Ошибка: Не указана подстрока для поиска.");
            return;
        }

        String substring = argument.trim();
        System.out.println("Элементы, содержащие подстроку '" + substring + "' в поле name:");

        collectionManager.getCollection().stream()
                .filter(lw -> lw.getName() != null && lw.getName().contains(substring))
                .forEach(System.out::println);
    }
}