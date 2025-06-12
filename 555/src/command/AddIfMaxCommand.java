package command;

import manager.CollectionManager;
import manager.InputManager;
import model.LabWork;

import java.util.Scanner;

/**
 * Команда add_if_max : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
 */
public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Добавьте новый элемент. Сейчас требуется ввод полей нового model.LabWork:");
        LabWork newLabWork = InputManager.readLabWork(scanner);

        boolean isAdded = collectionManager.addIfMax(newLabWork);

        if (isAdded) {
            System.out.println("Элемент успешно добавлен, так как он больше максимального элемента коллекции.");
        } else {
            System.out.println("Элемент не добавлен, так как он не больше максимального элемента коллекции.");
        }
    }
}