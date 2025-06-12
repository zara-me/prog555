package command;

import manager.CollectionManager;
import java.util.Scanner;

/**
 * Команда remove_by_id : удалить элемент из коллекции по его id.
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Ошибка: Не указан id для удаления.");
            return;
        }

        try {
            long id = Long.parseLong(argument.trim()); // Преобразуем аргумент в long
            boolean isRemoved = collectionManager.removeById(id);

            if (isRemoved) {
                System.out.println("Элемент с id " + id + " успешно удалён.");
            } else {
                System.out.println("Элемент с id " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат id. Введите целое число.");
        }
    }
}