package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда exit : завершить программу (без сохранения в файл).
 */
public class ExitCommand implements Command {
    public ExitCommand(CollectionManager collectionManager) {
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Завершение программы.");
        System.exit(0); // Завершаем программу с кодом 0 (успешное завершение)
    }
}