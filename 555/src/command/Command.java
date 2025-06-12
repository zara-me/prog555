package command;

import java.util.Scanner;

/**
 * Общий интерфейс для всех команд.
 */
public interface Command {
    /**
     * Выполняет действие команды.
     * @param argument строка, переданная после названия команды
     * @param scanner Scanner (консоль или файл), откуда можно читать дополнительные данные
     * @param mainFileName имя основного XML-файла, если нужно сохранить или ещё что-то
     */
    void execute(String argument, Scanner scanner, String mainFileName);
}
