import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;
import utility.Console;
import utility.Runner;
import utility.StandardConsole;

public class Main {
    public static void main(String[] args) {
        var console = new StandardConsole();
        var filePath = System.getenv("lab");

        if (filePath == null) {
            console.println("Введите имя загружаемого файла в переменную среды 'lab'");
            System.exit(1);
        }

        var dumpManager = new DumpManager(filePath, console);
        var collectionManager = new CollectionManager(dumpManager);
        if (!collectionManager.loadCollection()) {
            System.exit(1);
        }

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("add_if_max", new AddIfMax(console, collectionManager));
            register("remove_greater", new RemoveGreater(console, collectionManager));
            register("remove_lower", new RemoveLower(console, collectionManager));
            register("sum_of_minimal_point", new SumOfMinimalPoint(console, collectionManager));
            register("min_by_minimal_point", new MinByMinimalPoint(console, collectionManager));
            register("print_ascending", new PrintAscending(console, collectionManager));
        }};

        new Runner(console, commandManager).interactiveMode();
    }

}