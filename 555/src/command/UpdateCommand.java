package command;

import manager.CollectionManager;
import manager.InputManager;
import model.LabWork;

import java.util.Scanner;

/**
 * Команда update id : обновить значение элемента коллекции с указанным id.
 */
public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (argument.isEmpty()) {
            System.out.println("Необходимо указать id.");
            return;
        }
        try {
            long id = Long.parseLong(argument.split("\\s+")[0]);
            LabWork found = collectionManager.findById(id);
            if (found == null) {
                System.out.println("Элемент с id=" + id + " не найден.");
                return;
            }
            System.out.println("Обновляем элемент с id=" + id);
            LabWork updated = InputManager.readLabWork(scanner);
            // сохраняем старые id и дату
            updated.setId(found.getId());
            updated.setCreationDate(found.getCreationDate());
            collectionManager.update(found, updated);
            System.out.println("Элемент успешно обновлён.");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id.");
        }
    }
}
