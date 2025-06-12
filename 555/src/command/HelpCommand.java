package command;

import java.util.Scanner;

/**
 * Команда help : вывести справку по доступным командам.
 */
public class HelpCommand implements Command {

    @Override
    public void execute(String argument, Scanner scanner, String mainFileName) {
        System.out.println("Доступные команды:");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести информацию о коллекции");
        System.out.println("show : вывести все элементы коллекции");
        System.out.println("add : добавить новый элемент в коллекцию");
        System.out.println("update id : обновить значение элемента с указанным id");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : выполнить скрипт из файла");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("add_if_max : добавить новый элемент в коллекцию,если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_greater : удалить из коллекции все элементы, превышающие заданный");
        System.out.println("remove_lower : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("average_of_average_point : вывести среднее значение поля averagePoint для всех элементов коллекции");
        System.out.println("count_greater_than_difficulty difficulty : Посчитать элементы, где difficulty больше указанного значения.");
        System.out.println("filter_contains_name name : вывести элементы, где name содержит указанную часть текста");
    }
}
