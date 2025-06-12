package command;

import manager.CollectionManager;
import manager.CommandManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Команда execute_script: читает и выполняет команды из указанного файла.
 */
public class ExecuteScriptCommand implements Command {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final Deque<Scanner> scannerStack = new LinkedList<>(); // Стек для управления вложенными скриптами

    public ExecuteScriptCommand(CollectionManager collectionManager, CommandManager commandManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument, Scanner mainScanner, String mainFileName) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Ошибка: Не указано имя файла скрипта.");
            return;
        }

        File scriptFile = new File(argument.trim());
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Ошибка: Файл скрипта не существует или недоступен.");
            return;
        }

        try (Scanner scriptScanner = new Scanner(scriptFile)) {
            scannerStack.push(scriptScanner); // Добавляем новый сканер в стек

            System.out.println("Выполнение скрипта: " + argument);

            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                System.out.println("Выполняется команда: " + line);
                String[] parts = line.split(" ", 2);
                String cmdName = parts[0];
                String cmdArg = parts.length > 1 ? parts[1] : null;

                Command command = commandManager.getCommand(cmdName);
                if (command == null) {
                    System.out.println("Ошибка: Неизвестная команда '" + cmdName + "'.");
                    continue;
                }

                // Выполняем команду с использованием сканера файла
                command.execute(cmdArg, scriptScanner, mainFileName);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл скрипта не найден.");
        } finally {
            scannerStack.pop(); // Удаляем текущий сканер из стека
        }
    }
}