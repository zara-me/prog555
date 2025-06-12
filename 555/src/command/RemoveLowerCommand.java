package command;

import manager.CollectionManager;
import manager.InputManager;
import model.LabWork;

import java.util.Scanner;

/**
 * Команда remove_lower : удалить из коллекции все элементы, меньшие заданного.
 */
public class RemoveLowerCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Добавьте элемент для сравнения. Сейчас требуется ввод полей нового model.LabWork:");
        LabWork compareLabWork = InputManager.readLabWork(scanner);
        int removedCount = collectionManager.removeLower(compareLabWork);
        System.out.println("Удалено элементов: " + removedCount);
    }
}