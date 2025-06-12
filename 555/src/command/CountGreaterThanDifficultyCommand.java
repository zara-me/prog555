package command;

import manager.CollectionManager;
import model.Difficulty;

import java.util.Scanner;

/**
 * Команда count_greater_than_difficulty : вывести количество элементов, значение поля difficulty которых больше заданного.
 */
public class CountGreaterThanDifficultyCommand implements Command {
    private final CollectionManager collectionManager;

    public CountGreaterThanDifficultyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Ошибка: Не указано значение difficulty.");
            return;
        }

        try {
            Difficulty difficulty = Difficulty.valueOf(argument.trim().toUpperCase()); // Преобразуем аргумент в Difficulty
            long count = collectionManager.countGreaterThanDifficulty(difficulty);
            System.out.println("Количество элементов с difficulty больше " + difficulty + ": " + count);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверное значение difficulty. Допустимые значения: EASY, INSANE, TERRIBLE.");
        }
    }
}