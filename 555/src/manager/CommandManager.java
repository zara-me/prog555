package manager;

import command.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, отвечающий за хранение и предоставление команд.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final CollectionManager collectionManager;

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        initCommands();
    }

    /**
     * Регистрируем все команды в map. Ключ - это название команды,
     * значение - экземпляр, реализующий интерфейс command.Command.
     */
    // CommandManager.java
    private void initCommands() {
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager, this));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager)); // Исправлено
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager)); // Исправлено
        commands.put("average_of_average_point", new AverageOfAveragePointCommand(collectionManager));
        commands.put("count_greater_than_difficulty", new CountGreaterThanDifficultyCommand(collectionManager));
        commands.put("filter_contains_name", new FilterContainsNameCommand(collectionManager));
    }

    /**
     * Получить команду по названию.
     */
    public Command getCommand(String name) {
        return commands.get(name);
    }
}