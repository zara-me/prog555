package command;

import manager.CollectionManager;
import manager.InputManager;
import model.LabWork;

import java.util.Scanner;

/**
 * Команда remove_greater : удалить из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreaterCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Добавьте элемент для сравнения. Сейчас требуется ввод полей нового model.StudyGroup:");
        LabWork compareGroup = InputManager.readLabWork(scanner);
        int removedCount = collectionManager.removeGreater(compareGroup);
        System.out.println("Удалено элементов: " + removedCount);
    }
}
