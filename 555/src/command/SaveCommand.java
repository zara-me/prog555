package command;

import manager.CollectionManager;
import xml.XmlStorage;
import java.io.IOException;
import java.util.Scanner;

/**
 * Команда save : сохранить коллекцию в файл в формате XML.
 */
// SaveCommand.java
public class SaveCommand implements Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (mainFileName == null || mainFileName.trim().isEmpty()) {
            System.out.println("Ошибка: Не указано имя файла для сохранения.");
            return;
        }

        System.out.println("Сохраняю в файл: " + mainFileName);
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());

        try {
            // Используем XmlStorage вместо ObjectOutputStream
            XmlStorage.saveToFile(mainFileName, collectionManager);
            System.out.println("Коллекция успешно сохранена в файл: " + mainFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }
}