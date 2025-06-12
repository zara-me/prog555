import command.Command;
import manager.CollectionManager;
import manager.CommandManager;
import xml.XmlStorage;
import java.io.IOException;
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Main class responsible for launching the application and handling command input.
 */
public class MainApp {
    private final CollectionManager collectionManager = new CollectionManager();
    private final CommandManager commandManager = new CommandManager(collectionManager);
    private final Scanner consoleScanner = new Scanner(System.in);
    private final Deque<Scanner> scannerStack = new LinkedList<>();

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run(args);
    }

    public void run(String[] args) {
        if (args.length < 1) {
            System.out.println("Необходимо передать имя XML-файла первым аргументом командной строки.");
            return;
        }
        String fileName = args[0];

        // Load collection from XML
        try {
            XmlStorage.loadFromFile(fileName, collectionManager);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге XML: " + e.getMessage());
        }

        scannerStack.push(consoleScanner); // Start with console scanner

        while (!scannerStack.isEmpty()) {
            Scanner currentScanner = scannerStack.peek();
            System.out.print("Введите команду: ");

            if (!currentScanner.hasNextLine()) {
                scannerStack.pop(); // Remove finished scanner
                continue;
            }

            String line = currentScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String commandName = parts[0];
            String argument = (parts.length > 1) ? parts[1].trim() : "";

            // Exit command
            if (commandName.equals("exit")) {
                System.out.println("Завершение программы без сохранения.");
                break;
            }

            // Get and execute the command
            Command command = commandManager.getCommand(commandName);
            if (command == null) {
                System.out.println("Неизвестная команда. Введите 'help' для списка доступных команд.");
                continue;
            }

            try {
                command.execute(argument, currentScanner, fileName);
            } catch (Exception e) {
                System.out.println("Ошибка при выполнении команды: " + e.getMessage());
            }
        }
    }

    // Method to allow ExecuteScriptCommand to push new scanners
    public Deque<Scanner> getScannerStack() {
        return scannerStack;
    }
}