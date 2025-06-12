package command;

import manager.CollectionManager;

import java.util.Scanner;

/**
 * Команда average_of_average_point : вывести среднее значение поля averagePoint для всех элементов коллекции.
 */
public class AverageOfAveragePointCommand implements Command {
    private final CollectionManager collectionManager;

    public AverageOfAveragePointCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        double average = collectionManager.averageOfAveragePoint();
        System.out.println("Среднее значение averagePoint: " + average);
    }
}